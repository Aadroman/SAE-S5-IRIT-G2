package com.main.irit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.irit.algebraictree.TreeNode;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import fr.irit.module3.TransformationTransferAlgebraicTree;
import fr.sae.algebraictree.EJoin;
import fr.sae.algebraictree.ETreeNode;
import fxgraph.cells.*;
import fxgraph.graph.Graph;
import fxgraph.graph.ICell;
import fxgraph.graph.Model;
import fxgraph.layout.AbegoTreeLayout;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class iritMainController implements Initializable {
    private iritMainApplication app;
    private Stage primaryStage;
    @FXML
    private TextField requestTextField;
    @FXML
    private HBox globalTreePane;
    @FXML
    private HBox multiStoreTreePane;
    @FXML
    private HBox transferTreePane;

    public void initContext(Stage mainStage, iritMainApplication iritMainApplication) {
        this.primaryStage = mainStage;
        this.app = iritMainApplication;
    }

    public void displayDialog() {
        this.primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllTablesDB();
    }

    public iritMainApplication getApp() {
        return app;
    }

    public void setApp(iritMainApplication app) {
        this.app = app;
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
     * Function to
     */
    private GlobalAlgebraicTree createAndDisplayGlobalTreeGraph(Query query) {
        Graph globalTreeGraph = new Graph();
        // Add content to graph

        final Model model = globalTreeGraph.getModel();
        globalTreeGraph.beginUpdate();

        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(query);
        System.out.println("Algebraic Tree : ");
        TreeNode global = globalAlgebraicTree.getRootNode();
        ETreeNode globalTree = ETreeNode.createTree(global);

        assert globalTree != null;
        globalTree.print("");

        makeTree(globalTree, model, null);

        globalTreeGraph.endUpdate();

        // Create tree layout based on fxGraph
        AbegoTreeLayout layout = new AbegoTreeLayout(100, 200, Configuration.Location.Top);
        globalTreeGraph.layout(layout);

        // Initialize a childpane to add globalTreeGraph to
        Pane childPane;
        childPane = globalTreeGraph.getCanvas();

        this.globalTreePane.getChildren().clear();

        // Adds generated graphs to pane
        this.globalTreePane.getChildren().add(childPane);

        return globalAlgebraicTree;
    }

    private MultistoreAlgebraicTree createAndDisplayMultistoreAlgebraicTree(
            GlobalAlgebraicTree globalAlgebraicTree
    ) {
        Graph multiStoreTreeGraph = new Graph();

        final Model model = multiStoreTreeGraph.getModel();

        multiStoreTreeGraph.beginUpdate();

        MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);
        TreeNode multi = mat.getMultistoreAlgebraicTree();
        ETreeNode multiTree = ETreeNode.createTree(multi);
        makeTree(multiTree, model, null);
        System.out.println();
        System.out.println("Algebraic Multi-stores Tree : ");
        mat.getMultistoreAlgebraicTree().print("");

        multiStoreTreeGraph.endUpdate();

        // Create tree layout based on fxGraph
        AbegoTreeLayout layout = new AbegoTreeLayout(100, 200, Configuration.Location.Top);
        multiStoreTreeGraph.layout(layout);

        // Initialize a childpane to add globalTreeGraph to
        Pane childPane;
        childPane = multiStoreTreeGraph.getCanvas();

        this.multiStoreTreePane.getChildren().clear();
        // Adds generated graphs to pane
        this.multiStoreTreePane.getChildren().add(childPane);

        return mat;
    }

    private TransformationTransferAlgebraicTree createAndDisplayTTATreeGraph(MultistoreAlgebraicTree mat) {
        Graph transferTreeGraph = new Graph();

        final Model model = transferTreeGraph.getModel();
        transferTreeGraph.beginUpdate();
        TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
        TreeNode transform = ttat.getTransformationTransferAlgebraicTree();
        ETreeNode transformTree = ETreeNode.createTree(transform);
        makeTree(transformTree, model, null);

        System.out.println();
        System.out.println("Algebraic Multi-stores Transfer Tree : ");
        ttat.getTransformationTransferAlgebraicTree().print("");

        transferTreeGraph.endUpdate();

        // Create tree layout based on fxGraph
        AbegoTreeLayout layout = new AbegoTreeLayout(100, 200, Configuration.Location.Top);
        transferTreeGraph.layout(layout);

        // Initialize a childpane to add globalTreeGraph to
        Pane childPane;
        childPane = transferTreeGraph.getCanvas();
        this.transferTreePane.getChildren().clear();
        // Adds generated graphs to pane
        this.transferTreePane.getChildren().add(childPane);

        return ttat;
    }

    /**
     *
     */
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

            // Generation and rendering of all algebraic trees
            GlobalAlgebraicTree globalAlgebraicTree = this.createAndDisplayGlobalTreeGraph(queryParsed);
            MultistoreAlgebraicTree mat = this.createAndDisplayMultistoreAlgebraicTree(globalAlgebraicTree);
            TransformationTransferAlgebraicTree ttat = this.createAndDisplayTTATreeGraph(mat);
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
                    JointureCell jointure = new JointureCell("⨝ " + child);
                    addCellAndEdge(model, jointure, previousCell);
                    makeTree(((EJoin) child).getLeftChild(), model, jointure);
                    makeTree(((EJoin) child).getRightChild(), model, jointure);
                    break;
                case "ESelection":
                    SelectionCell selection = new SelectionCell("σ " + child);
                    addCellAndEdge(model, selection, previousCell);
                    if (child.getChild().length > 0) {
                        makeTree(child.getChild()[0], model, selection);
                    }
                    break;
                case "ETransfer":
                    TransferCell transfer = new TransferCell(child.toString());
                    addCellAndEdge(model, transfer, previousCell);
                    if (child.getChild().length > 0) {
                        makeTree(child.getChild()[0], model, transfer);
                    }
                    break;
                default:
                    LabelCell label = new LabelCell(child.toString());
                    addCellAndEdge(model, label, previousCell);
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
