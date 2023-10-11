package com.main.irit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.sae.Application;
import fr.sae.algebraictree.*;
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
import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class iritMainController implements Initializable {
    private iritMainApplication app;
    private Stage primaryStage;
    @FXML
    private TextArea requestTextField;
    @FXML
    private TabPane tabPane;
    @FXML
    private TreeView tvNode;
    @FXML
    private Button subRequestButton;

    private Tab selectedTab = null;

    private ETreeNode[] computedTrees = null;

    private final HashMap<String, String> branchQueries = new HashMap<>();

    private final HashMap<String, ArrayList<ETreeNode>> dbLeaves = new HashMap<>();

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
        // Create listener for tab change
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (null != this.computedTrees) {
                renderTabTreeView(newTab);
            }
        });
    }

    public iritMainApplication getApp() {
        return app;
    }

    public void setApp(iritMainApplication app) {
        this.app = app;
    }

    @FXML
    private void onSubRequestButtonClick() {
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

    private void renderTabTreeView(Tab newTab) {
        // Save selected Tab for further user
        this.selectedTab = newTab;

        // Re-création de la TreeView en fonction du panneau selectionné
        switch (newTab.getId()) {
            case "globalTreeTab":
                this.renderTreeView(this.computedTrees[0], false);
                break;
            case "multiStoreTreeTab":
                this.renderTreeView(this.computedTrees[1], false);
                break;
            case "transferTreeTab":
                this.renderTreeView(this.computedTrees[2], true);
                break;
        }
    }

    /**
     * Function that gets the text field SQL query, and returns all tables found within it.
     *
     * @return A list of tables names
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
     * Display a warning dialog with a custom message.
     * Also logs exception message for debugging purpose.
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
     * Crée le layout d'un tree sur un graph
     */
    private void renderTree(Graph treeGraph, HBox treePane) {
        AbegoTreeLayout layout = new AbegoTreeLayout(100, 200, Configuration.Location.Top);
        // Create tree layout based on fxGraph
        treeGraph.layout(layout);

        // Initialize a childpane to add globalTreeGraph to
        Pane childPane;
        childPane = treeGraph.getCanvas();

        treePane.getChildren().clear();
        // Adds generated graphs to pane
        treePane.getChildren().add(childPane);
    }

    /**
     * Création d'une treeView avec comme paramètre node ainsi que la TreeItem parente
     *
     * @param node       noeud de l'arbre a partir duquel est crée un TreeItem
     * @param treeParent TreeItem parent sur lequel on va add les enfants
     */
    private void populateTreeView(ETreeNode node, TreeItem<ETreeNode> treeParent) {
        if (node.getClass().equals(EProjection.class)) {
            //Boucle sur le nombre d'enfant de la projection initial
            int nbChild = node.getChild().length;
            for (int i = 0; i < nbChild; i++) {
                TreeItem<ETreeNode> child = new TreeItem<>(node.getChild()[i]);
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
        // Reset children
        this.tvNode.setRoot(null);

        //Creation du root du TreeView qui va servir de racine à l'arbo
        TreeItem<ETreeNode> rootTree = new TreeItem<>(node);

        // On ajoute l'arbo crée au composants tree view
        this.tvNode.setRoot(rootTree);

        if (separateDB) {
            HashMap<String, ArrayList<String>> dbTables = new HashMap<>();
            this.populateTransferTreeview(node, dbTables);

            List<TreeItem<String>> reversedItems = new ArrayList<>(this.tvNode.getRoot().getChildren());
            Collections.reverse(reversedItems);
            this.tvNode.getRoot().getChildren().setAll(reversedItems);
        } else {
            this.populateTreeView(node, rootTree);
            // Déplie par défault l'arbre
            rootTree.setExpanded(true);

            this.tvNode.setShowRoot(false);
        }
    }

    /**
     * Ajoute récursivement les noeuds adéquats à la TreeView de transfert, en séparant l'arborescence de chaque BDD.
     */
    private void populateTransferTreeview(ETreeNode node, HashMap<String, ArrayList<String>> dbTables) {
        switch (node.getClass().getSimpleName()) {
            case "EProjection":
                Arrays.stream(node.getChild()).forEach(item -> this.populateTransferTreeview(item, dbTables));
                break;
            case "EJoin":
                populateTransferTreeview(((EJoin) node).getLeftChild(), dbTables);
                populateTransferTreeview(((EJoin) node).getRightChild(), dbTables);
                break;
            case "ESelection":
                populateTransferTreeview(node.getChild()[0], dbTables);
                break;
            case "ETransfer", "ETransformation":
                if (node.getChild().length > 0) {
                    this.populateTransferTreeview(node.getChild()[0], dbTables);
                }
                break;
            case "ETable":
                if (node.getStore() != null) {
                    String storeName = node.getStore().name;
                    // Vérifie si la clé existe dans le dictionnaire, sinon la crée
                    dbTables.computeIfAbsent(storeName, k -> new ArrayList<>());
                    ArrayList<String> tableList = dbTables.get(storeName);
                    tableList.add(node.toString());

                    // Le noeud racine correspondant au nom de la DBB (ex: DB1, DB2, ...)
                    TreeItem dbRoot = addValueToTree(storeName);
                    // Si la racine est déjà crée, alors on peut commencer à y ajouter des TreeItems
                    if (null != dbRoot) {
                        TreeItem text = new TreeItem(node);
                        dbRoot.getChildren().add(this.bottomToTopBranchFill(text, node.getParent()));
                        this.branchQueries.put(storeName, QueryBuilder.buildQueryFromTree(node, tableList));
                    }
                } else if (((ETable) node).getCorrespondingTable() != null) {
                    String storeName = ((ETable) node).getCorrespondingTable().getStore().name;
                    // Vérifie si la clé existe dans le dictionnaire, sinon la crée
                    dbTables.computeIfAbsent(storeName, k -> new ArrayList<>());
                    dbTables.get(storeName).add(node.toString());

                    this.dbLeaves.computeIfAbsent(storeName, k -> new ArrayList<>());
                    dbLeaves.get(storeName).add(node);

                    ArrayList<String> tableList = dbTables.get(storeName);
                    tableList.add(node.toString());

                    TreeItem dbRoot = addValueToTree(storeName);
                    if (null != dbRoot) {
                        // Ajoute l'arborescence de la branhe à la racine
                        dbRoot.getChildren().add(
                                this.bottomToTopBranchFill(new TreeItem(node), node.getParent())
                        );

                        // Re-crée et ajoute la requête SQL à la hashmap
                        this.branchQueries.put(storeName, QueryBuilder.buildQueryFromTree(node, tableList));
                    }
                }
                break;
        }
    }

    /**
     * Remplie l'arborescence de la TreeView en partant d'une feuille, vers la racine.
     */
    private TreeItem<Object> bottomToTopBranchFill(TreeItem<Object> child, ETreeNode parent) {
        if (null == parent) {
            return child;
        } else {
            TreeItem<Object> parentItem = new TreeItem<>(parent);
            parentItem.getChildren().add(child);

            return this.bottomToTopBranchFill(parentItem, parent.getParent());
        }
    }

    // Helper method to add a value to the tree if it doesn't already exist
    private TreeItem<Object> addValueToTree(String valueToAdd) {
        if (!isValueExists(valueToAdd)) {
            TreeItem<Object> dbRoot = new TreeItem<Object>(valueToAdd);
            tvNode.getRoot().getChildren().add(dbRoot);

            return dbRoot;
        }

        return this.findTreeItem(this.tvNode.getRoot(), valueToAdd);
    }

    // Helper method to check if the value already exists in the tree
    private boolean isValueExists(String value) {
        return tvNode.getRoot().getChildren().stream()
                .anyMatch(item -> ((TreeItem<?>) item).getValue().equals(value));
    }

    /**
     * Recherche et retourne un TreeItem en fonction de sa valeur.
     */
    public TreeItem<Object> findTreeItem(TreeItem<Object> root, String value) {
        if (root == null) {
            return null;
        }

        if (root.getValue().equals(value)) {
            return root;
        }

        for (TreeItem<Object> child : root.getChildren()) {
            TreeItem<Object> result = findTreeItem(child, value);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    @FXML
    private void interactWithPolystore() {
        List<String> tablesnames = getAllTablesDB();
        boolean allTablesFound = false;

        try {
            // Get value from requestTextField
            String queryString = this.requestTextField.getText();

            List<String> foundTables = this.getFoundTablesFromInput(queryString);

            // Check if the found tables are in the predefined table names list (HashSet is to improve performance)
            allTablesFound = new HashSet<>(tablesnames).containsAll(foundTables);

            Query queryParsed = QueryParserUtils.parse(queryString);

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
            ProjectionCell projection = new ProjectionCell("π " + child.toString());
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
                        IntStream.range(1, charArray.length-1).forEachOrdered(index -> {
                            text.updateAndGet(v -> v + charArray[index]);
                        });
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

                    this.addCellAndEdge(model, transfer, previousCell);

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
                    LabelCell label = new LabelCell(child.toString().toUpperCase());

                    this.addCellAndEdge(model, label, previousCell);

                    if (child.getChild().length > 0) {
                        makeTree(child.getChild()[0], model, label);
                    }
                    break;
            }
        }
    }

    private void addCellAndEdge(Model model, ICell cell, ICell previousCell) {
        model.addCell(cell);
        model.addEdge(cell, previousCell);
    }

    /***
     * Return le nom de toutes les tables dans une List de Nom
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
}
