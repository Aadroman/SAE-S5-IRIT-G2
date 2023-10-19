package com.main.irit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.irit.treeviewhelper.TreeViewHelper;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.sae.Application;
import fr.sae.algebraictree.EJoin;
import fr.sae.algebraictree.EProjection;
import fr.sae.algebraictree.ESelection;
import fr.sae.algebraictree.ETreeNode;
import fr.sae.querybuilder.QueryBuilder;
import fxgraph.cells.*;
import fxgraph.graph.Graph;
import fxgraph.graph.ICell;
import fxgraph.graph.Model;
import fxgraph.layout.AbegoTreeLayout;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static com.main.irit.treeviewhelper.TreeViewHelper.*;

public class iritMainController implements Initializable {

    private iritMainApplication app;

    private final iritHelpPopup helpPopup = new iritHelpPopup();

    private Stage primaryStage;

    @FXML
    private TextArea requestTextField;

    @FXML
    private TabPane tabPane;

    @FXML
    private TreeView<Object> tvNode;
    @FXML
    private TextArea txtStructure;
    @FXML
    private Button subRequestButton;


    private Tab selectedTab = null;

    private ETreeNode[] computedTrees = null;

    private final HashMap<String, String> branchQueries = new HashMap<>();

    // HashMap qui stocke les feuilles de l'arbre tel que clé = le nom de la base, valeur = tableau de feuilles
    private final HashMap<String, ArrayList<ETreeNode>> dbLeaves = new HashMap<>();

    //arraylist des tables temporaires
    private ArrayList<String> tableTemp;
    private int nb=1; //numérotation des bases de données temporaires

    public void initContext(Stage mainStage, iritMainApplication iritMainApplication) {
        this.primaryStage = mainStage;
        this.app = iritMainApplication;
    }

    public void displayDialog() {
        this.primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fait en sorte que la TreeView prenne toujours le maximum de place
        VBox.setVgrow(this.tvNode, Priority.ALWAYS);
        getAllTablesDB();
        generateStructure();
        // Create listener for tab change
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> renderTabTreeView(newTab));
    }

    public iritMainApplication getApp() {
        return app;
    }

    public void setApp(iritMainApplication app) {
        this.app = app;
    }

    /**
     * Event handler qui s'executé lors de l'appuie du bouton "générer les requêtes".
     *
     */
    @FXML
    private void onSubRequestButtonClick() {
        this.renderTreeView(this.computedTrees[2], true);

        // Clause de garde afin d'éviter d'afficher une dialog inutilement
        if (this.branchQueries.isEmpty()) return;

        // Création de la modale
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sous-requêtes");
        alert.setHeaderText("Sous-requêtes: ");

        // Création du panneau de contenu de la modale
        VBox modalPane = new VBox();
        modalPane.setAlignment(Pos.CENTER_LEFT);
        modalPane.setSpacing(16);

        // Boucle sur le dictionnaire contenant toutes les requêtes
        this.branchQueries.forEach((key, value) -> {
            VBox subVbox = new VBox();

            // Creation du label de la textArea
            Text label = new Text(key);
            subVbox.getChildren().add(label);

            // Création/paramétrage de la textArea affichant la requête
            ArrayList<String> al = new ArrayList<String>();
            for ( String val : value.split(" ")) {
                al.add(val);
            }
            if ( al.get(1).equals("")){
                al.set(1,"*");
                value = "";
                for( String val : al){
                    value += val;
                    value += " ";
                }
            }
            TextArea request = new TextArea(value);

            request.setWrapText(true);
            request.setEditable(false);

            subVbox.getChildren().add(request);

            modalPane.getChildren().add(subVbox);
        });

        alert.getDialogPane().setExpandableContent(modalPane);
        alert.getDialogPane().setExpanded(true);
        alert.showAndWait();
    }

    /**
     * Recrée la TreeView en fonction de l'arbre affiché sur le panneau selectionné.
     *
     * @param newTab Le panneau selectionné
     */
    private void renderTabTreeView(Tab newTab) {
        // Sauvegarde le panneau selectionné pour utilisations utlérieures
        this.selectedTab = newTab;
        // Re-création de la TreeView en fonction de l'arbre correspondant au panneau selectionné
        if (this.computedTrees != null){
        switch (newTab.getId()) {
            case "globalTreeTab":
                this.subRequestButton.setDisable(true);
                this.renderTreeView(this.computedTrees[0], false);
                break;
            case "multiStoreTreeTab":
                this.subRequestButton.setDisable(true);
                this.renderTreeView(this.computedTrees[1], false);
                break;
            case "transferTreeTab":
                this.subRequestButton.setDisable(false);
                this.renderTreeView(this.computedTrees[2], true);
                break;
            case "structureTab":
                this.subRequestButton.setDisable(true);
                this.tvNode.setRoot(null);
                break;
            }
        }
    }

    /**
     * Récupère la requête SQL renseignée dans le champ de texte, et retourne toutes les tables contenues dans cette
     * dernière.
     *
     * @return Une liste de noms de tables
     */
    private List<String> getFoundTablesFromInput(String queryString) {
        // Define a regex pattern to match table names in the SQL query
        Pattern pattern = Pattern.compile("from\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(queryString);

        List<String> foundTables = new ArrayList<>();

        // Find and collect all table names mentioned in the SQL query
        while (matcher.find()) {
            foundTables.add(matcher.group(1));
        }

        return foundTables;
    }

    /**
     * Genère et affiche la structure des BDD dans le 4ème onglet de l'application.
     */
    private void generateStructure(){
        // Va gerer la conversion JSON grace à la librairie JACKSON
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            File path = new File("src/main/java/fr/irit/module2/UnifiedView/relationalUnifiedView.json");
            JsonNode jsonNode = objectMapper.readTree(path);


            // On recupere les tables present dans la json
            JsonNode uvTables = jsonNode.get("uvTables");

            ArrayList<String> arrayTablesNames = new ArrayList<>();
            ArrayList<JsonNode> arrayDB = new ArrayList<>();
            // On parcours toutes les tables et on les ajoute au tableau
            for(JsonNode node : uvTables){
                findNode(arrayDB,arrayTablesNames, node);
            }

            StringBuilder affichage = new StringBuilder();
            int i = 0;

            // On parcourt la liste de toutes les tables
            for (JsonNode node : arrayDB){
                affichage.append(String.format(
                        "- %s ( %s %s ",
                        arrayTablesNames.get(i), node.get("name"), node.get("type")
                ));

                // On recupere toutes les colonnes lié à la table
                for (int c = 0 ; c < node.get("columns").size(); c++){
                        // Si c'est la dernière colonne on ne mets pas de virgule à la fin
                    if (c < ((node.get("columns").size()) - 1)) {
                        affichage.append(node.get("columns").get(c).get("columnUV")).append(", ");
                    } else {
                        affichage.append(node.get("columns").get(c).get("columnUV"));
                    }

                }
                affichage.append(") \n");
            i++;
            }

            this.txtStructure.setEditable(false);
            this.txtStructure.setText(affichage.toString());


        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Récupère les nœuds JSON correspondant à des tables de données à partir d'une structure JSON complexe.
     *
     * Cette méthode récursive parcourt la structure JSON en profondeur pour identifier les nœuds
     * correspondant à des tables de données. Les nœuds identifiés sont stockés dans la liste
     * arrayNode, et les noms des tables sont ajoutés à la liste arrayTablesNames.
     *
     * @param arrayNode       Liste dans laquelle les nœuds correspondant aux tables de données seront stockés.
     * @param arrayTablesNames Liste dans laquelle les noms des tables de données seront stockés.
     * @param children        Le nœud JSON à analyser.
     */
    private void findNode(ArrayList<JsonNode> arrayNode , ArrayList<String> arrayTablesNames , JsonNode children){
        String paramName = "name";
        String paramType = "type";
        String paramColumn = "columns";
        String paramStores = "stores";

        if((children.get(paramName) != null) && (children.get(paramType)!= null) && (children.get(paramColumn)!= null)){
            arrayNode.add(children);
        }else if (children.get(paramStores) != null){
            findNode(arrayNode,arrayTablesNames,children.get("stores"));
            // Recupere le nom de la table et l'ajoute dans l'array correspondant
            arrayTablesNames.add(children.get("label").asText());
        } else {
            findNode(arrayNode, arrayTablesNames ,children.get(0));
        }
    }

    /**
     * Affiche une modale d'avertissement.
     * Log aussi l'exception dans la console.
     *
     * @param description La description de l'avertissement
     * @param exception   L'erreur à log
     */
    private void displayWarningDialog(String description, Exception exception) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Requête incorrecte");
        warning.setContentText(description);

        // Log error message for debugging purpose
        System.out.println(exception.toString());
        warning.showAndWait();
    }

    /**
     * Crée le layout d'un tree sur un graph.
     *
     * @param treeGraph Le FXGraph sur lequel appliquer le layout
     * @param treePane  Le panneau sur lequel afficher le graphe
     */
    private void renderTree(Graph treeGraph, HBox treePane) {
        AbegoTreeLayout layout = new AbegoTreeLayout(100, 200, Configuration.Location.Top);
        // Crée le layout avec le fxgraph
        treeGraph.layout(layout);

        // Initialise un panneau sur lequel afficher le graphe
        Pane childPane;
        childPane = treeGraph.getCanvas();

        treePane.getChildren().clear();
        // Ajoute le panneau avec le graphe au panneau parent
        treePane.getChildren().add(childPane);
    }

    /**
     * Création d'une treeView avec comme paramètre node ainsi que la TreeItem parente
     *
     * @param node       Noeud de l'arbre a partir duquel est crée un TreeItem
     * @param treeParent TreeItem parent sur lequel on va add les enfants
     */
    private void populateTreeView(ETreeNode node, TreeItem<ETreeNode> treeParent) {
        if (node.getClass().equals(EProjection.class)) {
            // Boucle sur le nombre d'enfant de la projection initial
            int nbChild = node.getChild().length;
            for (int i = 0; i < nbChild; i++) {
                TreeItem<ETreeNode> child = new TreeItem<>(node);
                child.setExpanded(true);
                treeParent.getChildren().add((child));
                this.populateTreeView(node.getChild()[i], child);
            }
        } else if (node.getClass().equals(EJoin.class)) {
            // Si le node est de type EJoin il a un left et un right child qu'on va créer en tant que TreeItem et ajouter au parent
            TreeItem<ETreeNode> childLeft = new TreeItem<>(((EJoin) node).getLeftChild());
            childLeft.setExpanded(true);
            TreeItem<ETreeNode> childRight = new TreeItem<>(((EJoin) node).getRightChild());
            childRight.setExpanded(true);
            treeParent.getChildren().addAll(childLeft, childRight);

            populateTreeView(((EJoin) node).getRightChild(), childRight);
            populateTreeView(((EJoin) node).getLeftChild(), childLeft);
        } else if (node.getClass().equals((ESelection.class))) {
            // Si le node est de type ESelection il n'aura qu'un enfant
            TreeItem<ETreeNode> child = new TreeItem<>(node.getChild()[0]);
            child.setExpanded(true);
            treeParent.getChildren().add(child);

            populateTreeView(node.getChild()[0], child);
        }
    }

    /**
     * Fonction qui affiche une TreeView générée sur la scène actuelle.
     *
     * @param separateDB True si les arborescences doivent êtres séparées par BDD
     */
    private void renderTreeView(ETreeNode node, Boolean separateDB) {
        // Reinitialise la TreeView
        this.tvNode.setRoot(null);

        // Creation du root du TreeView qui va servir de racine à l'arbo
        TreeItem<?> rootTree = new TreeItem<>(node);

        // On ajoute l'arbo crée au composants tree view
        this.tvNode.setRoot((TreeItem<Object>) rootTree);

        if (separateDB) {
            // Enlève valeurs des requêtes précédentes
            this.dbLeaves.clear();
            this.branchQueries.clear();

            this.populateTransferTreeview(node);
            this.dbLeaves.forEach((key, value) -> this.branchQueries.put(key, QueryBuilder.buildQueryFromTree(value)));

            // Reverse l'ordre des TreeItems
            List<TreeItem<Object>> reversedItems = new ArrayList<>(this.tvNode.getRoot().getChildren());
            Collections.reverse(reversedItems);

            this.tvNode.getRoot().getChildren().setAll(reversedItems);
        } else {
            this.populateTreeView(node, (TreeItem<ETreeNode>) rootTree);
            // Déplie par défault l'arbre
            rootTree.setExpanded(true);

            this.tvNode.setShowRoot(false);
        }
    }

    /**
     * Ajoute récursivement les noeuds adéquats à la TreeView de transfert, en séparant l'arborescence de chaque BDD.
     *
     * @param node Le noeud actuel dans la récursion
     */
    private void populateTransferTreeview(ETreeNode node) {
        switch (node.getClass().getSimpleName()) {
            case "EProjection":
                Arrays.stream(node.getChild()).forEach(this::populateTransferTreeview);
                break;
            case "EJoin":
                populateTransferTreeview(((EJoin) node).getLeftChild());
                populateTransferTreeview(((EJoin) node).getRightChild());
                break;
            case "ESelection":
                populateTransferTreeview(node.getChild()[0]);
                break;
            case "ETransfer", "ETransformation":
                if (node.getChild().length > 0) {
                    this.populateTransferTreeview(node.getChild()[0]);
                }
                break;
            case "ETable":
                if (node.getStore() != null) {
                    String storeName = node.getStore().name;
                    // Vérifie si la clé existe dans le dictionnaire, sinon la crée
                    dbLeaves.computeIfAbsent(storeName, k -> new ArrayList<>());
                    dbLeaves.get(storeName).add(node);

                    // Le noeud racine correspondant au nom de la DBB (ex: DB1, DB2, ...)
                    TreeItem<Object> dbRoot = addValueToTree(this.tvNode, storeName);
                    // Si la racine est déjà crée, alors on peut commencer à y ajouter des TreeItems
                    if (null != dbRoot) {
                        TreeItem<Object> text = new TreeItem<>(node);
                        dbRoot.getChildren().add(TreeViewHelper.bottomToTopBranchFill(text, node.getParent()));
                    }
                }
                break;
        }
    }



    @FXML
    public void interactWithPolystore() {
        this.tableTemp = new ArrayList<>();
        List<String> tablesnames = getAllTablesDB();
        boolean allTablesFound = false;

        try {
            // Get value from requestTextField
            String queryString = this.requestTextField.getText();

            List<String> foundTables = this.getFoundTablesFromInput(queryString);

            // Check if the found tables are in the predefined table names list (HashSet is to improve performance)
            allTablesFound = new HashSet<>(tablesnames).containsAll(foundTables);

            Query<?> queryParsed = QueryParserUtils.parse(queryString);

            // Sauvegarde des arbres pour utilisations ultérieures
            this.computedTrees = Application.getTreeFromQuery(queryParsed);

            for (int i = 0; i < this.computedTrees.length; i++) {
                Graph graph = new Graph();

                // Add content to graph
                Model model = graph.getModel();
                graph.beginUpdate();

                this.makeTree(this.computedTrees[i], model, null);

                // Génération de la treeview pour l'arbre global
                if (null == this.selectedTab && i == 0) {
                    this.renderTreeView(this.computedTrees[i], false);
                } else if (null != this.selectedTab) {
                    this.renderTabTreeView(this.selectedTab);
                }

                graph.endUpdate();

                if (tabPane.getTabs().get(i).getContent() instanceof VBox) {
                    this.renderTree(
                            graph,
                            (HBox) ((VBox) tabPane.getTabs().get(i).getContent()).getChildren().get(0)
                    );
                }
            }
        } catch (Exception e) {
            if (requestTextField.getText().isEmpty()) {
                this.displayWarningDialog("Votre requête est vide !", e);
            } else if (!allTablesFound) {
                this.displayWarningDialog("Mettez une majuscule à votre table", e);
            } else {
                this.displayWarningDialog("Votre requête est incorrecte", e);
            }
        }
    }

    /**
     * Function that recursively creates and populates a fx-graph representing the given algebraic tree.
     *
     * @param child        The current tree node
     * @param model        The graph model to add cells and edges to
     * @param previousCell The previously treated cell (e.g: the parent node's cell)
     */
    private void makeTree(ETreeNode child, Model model, ICell previousCell) {
        if (previousCell == null) {
            ProjectionCell projection = new ProjectionCell(child.toString());
            // On l'ajoute au model deja crée précédemment
            model.addCell(projection);
            // On verifie si il a des enfants et on réexecute la methode
            if (child.getChild().length > 0) {
                makeTree(child.getChild()[0], model, projection);
            }
        } else {
            switch (child.getClass().getSimpleName()) {
                case "EJoin":
                    JointureCell jointure = new JointureCell(child.toString());

                    this.addCellAndEdge(model, jointure, previousCell);

                    makeTree(((EJoin) child).getLeftChild(), model, jointure);
                    makeTree(((EJoin) child).getRightChild(), model, jointure);
                    break;
                case "ESelection":
                    char[] charArray = child.toString().toCharArray();
                    AtomicReference<String> text = new AtomicReference<>("");
                    if (charArray[0] == '(' && charArray[charArray.length-1] == ')'){
                        IntStream.range(1, charArray.length-1).forEachOrdered(index -> text.updateAndGet(v -> v + charArray[index]));
                    } else {
                        text.updateAndGet(v -> child.toString());
                    }
                    SelectionCell selection = new SelectionCell(String.valueOf(text));

                    this.addCellAndEdge(model, selection, previousCell);

                    if (child.getChild().length > 0) {
                        makeTree(child.getChild()[0], model, selection);
                    }
                    break;
                case "ETransfer":
                    TransferCell transfer = new TransferCell(child.toString());

                    //SOLUCE TEMPORAIRE POUR LES TABLES TEMPO
                    //PEUT ETRE CREE CLASSE ETABLETEMP A METTRE EN LIEN AVEC LES AUTRES
                    TableTempCell nameDbTemp = null;
                    if(tableTemp.isEmpty()){
                        this.nb = 1; //retour de la variable à sa valeur initiale
                        String name = "Tabletemp"+nb;
                        tableTemp.add(name);
                        nameDbTemp = new TableTempCell(tableTemp.get(0),null);
                        this.nb++;
                    }else {
                        for(int i=0; i<tableTemp.size();i++){
                            String name = "Tabletemp"+nb;
                            if(tableTemp.get(i).equals(name)){
                                this.nb++;
                                break;
                            }
                            tableTemp.add(name);
                        }
                        nameDbTemp = new TableTempCell(tableTemp.get(tableTemp.size()-1),null);
                    }
//                    DBTempCell nameDbTemp = new DBTempCell(dbTemp.get(dbTemp.size()-1),null);

                    previousCell.addCellChild(nameDbTemp);
                    transfer.addCellParent(nameDbTemp);
                    this.addCellAndEdge(model,nameDbTemp, previousCell);
                    this.addCellAndEdge(model,transfer, nameDbTemp);
//                    this.addCellAndEdge(model, transfer, previousCell);

                    if (child.getChild().length > 0) {
                        makeTree(child.getChild()[0], model, transfer);
                    }
                    break;
                case "ETransformation":
                    TransformCell transform = new TransformCell(child.toString());

                    this.addCellAndEdge(model, transform, previousCell);

                    if (child.getChild().length > 0) {
                        makeTree(child.getChild()[0], model, transform);
                    }
                    break;
                default:
                    LabelCell label;
                    if (child.getStore() == null){
                        label = new LabelCell(child.toString().toUpperCase(), null);
                    }else {
                        label = new LabelCell(child.toString().toUpperCase(), child.getStore().name+"\n"+child.getStore().columns);
                    }

                    this.addCellAndEdge(model, label, previousCell);

                    if (child.getChild().length > 0) {
                        makeTree(child.getChild()[0], model, label);
                    }
                    break;
            }
        }
    }

    /**
     * Ajoute une cellule, et un lien depuis celle-ci vers son parent dans un graphe.
     *
     * @param model        Le modèle du graphe
     * @param cell         La cellule à ajouter
     * @param previousCell La cellule parent
     */
    private void addCellAndEdge(Model model, ICell cell, ICell previousCell) {
        model.addCell(cell);
        model.addEdge(cell, previousCell);
    }

    /***
     * Return le nom de toutes les tables dans une List de Nom
     *
     * @return Liste de toutes les tables
     * */
    private List<String> getAllTablesDB() {
        List<String> tablesNames = new ArrayList<>();
        // Specify the path to your JSON file
        String documentJSON = "src/main/java/fr/irit/module2/UnifiedView/documentUnifiedView.json";
        String relationalJSON = "src/main/java/fr/irit/module2/UnifiedView/relationalUnifiedView.json";

        try {
            // Initialize the ObjectMapper (Jackson library)
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file and parse it into a JsonNode
            JsonNode documentNode = objectMapper.readTree(new File(documentJSON));
            JsonNode relationalNode = objectMapper.readTree(new File(relationalJSON));

            // Get the "uvTables" array
            JsonNode documentUVTablesNode = documentNode.get("uvTables");
            JsonNode relationalUVTablesNode = relationalNode.get("uvTables");


            // Iterate through the JSON array and extract "label" values
            for (JsonNode node : documentUVTablesNode) {
                String label = node.get("label").asText();
                if (!tablesNames.contains(label)) tablesNames.add(label);
            }
            for (JsonNode node : relationalUVTablesNode) {
                String label = node.get("label").asText();
                if (!tablesNames.contains(label)) tablesNames.add(label);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tablesNames;
    }

    @FXML
    private void openHelpPopup() throws Exception {
        this.helpPopup.start(this.primaryStage);
    }
}
