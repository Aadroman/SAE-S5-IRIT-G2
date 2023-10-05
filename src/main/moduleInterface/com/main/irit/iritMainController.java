package com.main.irit;

import fr.irit.algebraictree.TreeNode;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import fr.irit.module3.TransformationTransferAlgebraicTree;
import fr.sae.algebraictree.*;
import fxgraph.cells.*;
import fxgraph.edges.CorneredEdge;
import fxgraph.edges.Edge;
import fxgraph.graph.Graph;
import fxgraph.graph.ICell;
import fxgraph.graph.Model;
import fxgraph.layout.AbegoTreeLayout;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class iritMainController implements Initializable {
    @SuppressWarnings("FieldCanBeLocal")
    private  iritMainApplication app;
    private Stage primaryStage;

    @FXML
    private TextField requestTextField;

    @FXML
    private Pane globalPane;

    public void initContext(Stage mainStage, iritMainApplication iritMainApplication) {
        this.primaryStage = mainStage;
        this.app = iritMainApplication;
    }

    public void displayDialog() { this.primaryStage.show(); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllTablesDB();
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

    @FXML
    private void interactWithPolystore() {
        List<String> tablesnames = getAllTablesDB();
        boolean allTablesFound = false;
        try {
            // Get value from requestTextField
            String queryString = this.requestTextField.getText();

            List<String> foundTables = this.getFoundTablesFromInput(queryString);

            // Check if the found tables are in the predefined table names list
            allTablesFound = true;
            for (String tableName : foundTables) {
                if (!tablesnames.contains(tableName)) {
                    allTablesFound = false;
                    // Ends loop if condition is met, for performance
                    break;
                }
            }

            Pane childPane;
            Graph graph = new Graph();
            // Add content to graph

            final Model model = graph.getModel();
            graph.beginUpdate();

            Query queryParsed = QueryParserUtils.parse(queryString);

            GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);
            System.out.println("Algebraic Tree : ");
            TreeNode global = globalAlgebraicTree.getRootNode();
            ETreeNode globalTree = ETreeNode.createTree(global);
            globalTree.print("");

            makeTree(globalTree, model, null);

            MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);
            TreeNode multi = mat.getMultistoreAlgebraicTree();
            ETreeNode multiTree = ETreeNode.createTree(multi);
            makeTree(multiTree, model, null);

            System.out.println("");
            System.out.println("Algebraic Multi-stores Tree : ");
            mat.getMultistoreAlgebraicTree().print("");

            TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
            TreeNode transform = ttat.getTransformationTransferAlgebraicTree();
            ETreeNode transformTree = ETreeNode.createTree(transform);
            makeTree(transformTree, model, null);

            System.out.println("");
            System.out.println("Algebraic Multi-stores Tree : ");
            ttat.getTransformationTransferAlgebraicTree().print("");

            graph.endUpdate();

            // Layout nodes
            AbegoTreeLayout layout = new AbegoTreeLayout(100, 200, Configuration.Location.Top);
            graph.layout(layout);
            childPane = graph.getCanvas();

            // Adds generated graphs to pane
            globalPane.getChildren().add(childPane);

        } catch (Exception e) {
            if (requestTextField.getText().isEmpty()) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Requête vide");
                warning.setHeaderText("Votre requête est vide !");
                warning.setContentText(e.toString());
                warning.showAndWait();
            } else if (!allTablesFound) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Erreur requête");
                warning.setHeaderText("Mettez une majuscule à votre table");
                warning.setContentText(e.toString());
                warning.showAndWait();
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Erreur requête");
                warning.setHeaderText("Votre requête est incorrecte");
                warning.setContentText(e.toString());
                warning.showAndWait();
            }
        }
    }

    public void makeTree(ETreeNode child, Model model, ICell lastCell) {
        //On crée la base de l'arbre si le treeNode n'a pas de parent
        if (lastCell == null) {
            ProjectionCell projection = new ProjectionCell("π "+ child.toString());

            //On l'ajoute au model deja crée précédemment
            model.addCell(projection);

            //On verifie si il a des enfants et on réexecute la methode
            if(child.getChild().length>0) {
                makeTree(child.getChild()[0], model, projection);
            }
        } else if (child.getClass().equals(EJoin.class)) {

            JointureCell jointure = new JointureCell("⨝ "+child.toString());

            model.addCell(jointure);
            model.addEdge(jointure, lastCell);

            makeTree(((EJoin) child).getLeftChild(), model, jointure);
            makeTree(((EJoin) child).getRightChild(), model, jointure);

        } else if (child.getClass().equals(ESelection.class)) {
            SelectionCell selection = new SelectionCell("σ "+ child.toString());

            model.addCell(selection);
            model.addEdge(selection, lastCell);

            if(child.getChild().length>0) {
                makeTree(child.getChild()[0], model, selection);
            }
        } else if (child.getClass().equals(ETransfer.class)) {
            TransferCell transfer = new TransferCell(child.toString());

            model.addCell(transfer);
            model.addEdge(transfer, lastCell);

            if(child.getChild().length>0){
                makeTree(child.getChild()[0],model,transfer);
            }
        } else if (child.getClass().equals(ETransformation.class)) {
            LabelCell transform = new LabelCell(child.toString());

            model.addCell(transform);
            model.addEdge(transform, lastCell);

            if(child.getChild().length>0){
                makeTree(child.getChild()[0],model,transform);
            }
        } else {
            LabelCell label = new LabelCell(child.toString());

            model.addCell(label);
            model.addEdge(label, lastCell);

            if(child.getChild().length>0) {
                makeTree(child.getChild()[0], model, label);
            }
        }

    }


    /***
     * Return le nom de toutes les tables dans une List de Nom
     * */
    protected List<String> getAllTablesDB(){
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
                if (!tablesNames.contains(label))
                    tablesNames.add(label);
            }
            for (JsonNode node : relationalUVTablesNode) {
                String label = node.get("label").asText();
                if (!tablesNames.contains(label))
                    tablesNames.add(label);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tablesNames;
    }

//    private TitledPane updatePane(TitledPane pane) {
//        Pane childPane;
//        Graph graph = new Graph();
//        // Add content to graph
//        populateGraph(graph);
//        // Layout nodes
//        AbegoTreeLayout layout = new AbegoTreeLayout(100, 500, Configuration.Location.Bottom);
//        graph.layout(layout);
//        childPane = graph.getCanvas();
//        pane.setContent(childPane);
//        return pane;
//    }

    private void populateGraph(Graph graph) {
        final Model model = graph.getModel();
        graph.beginUpdate();


        // Les cellules en commentaires sont remplacées par des cellules de projection, selection ou jointure,
        // elles sont mises de coté pour l'implementation de différents types de cells autre que des labelCell

        //final ICell cellA = new LabelCell("π *");
        final ProjectionCell cellA = new ProjectionCell("π *");
        final ICell cellB = new LabelCell(" Orders");
        //final ICell cellC = new LabelCell("π *");
        final ProjectionCell cellC = new ProjectionCell("π *");
        //final ICell cellD = new LabelCell("⨝ Orders.order_id = Orders.order_id");
        final JointureCell cellD = new JointureCell("⨝ Orders.order_id = Orders.order_id");
        final ICell cellE = new LabelCell("Orders : DB3-DOCUMENT \n [Orders.order_id <-> Orders.order_id, Orders.total_price <-> Orders.total_price]");
        final ICell cellF = new LabelCell("Orders : DB2-RELATIONAL \n [Orders.order_id <-> Orders.order_id, Orders.customer_id <-> Orders.customer_id]");
        //final ICell cellG = new LabelCell("π *");
        final ProjectionCell cellG = new ProjectionCell("π *");
        //final ICell cellH = new LabelCell("⨝ Orders.order_id = Orders.order_id");
        final JointureCell cellH = new JointureCell("⨝ Orders.order_id = Orders.order_id");
        final ICell cellI = new LabelCell("Transfer : DB3 -> DB1");
        final ICell cellJ = new LabelCell("Transfer : DB2 -> DB1");
        final ICell cellK = new LabelCell("Transformation : DOCUMENT -> RELATIONAL");
        final ICell cellL = new LabelCell("Orders : DB3-DOCUMENT [Orders.order_id <-> Orders.order_id, Orders.total_price <-> Orders.total_price]");
        final ICell cellM = new LabelCell("Orders : DB2-RELATIONAL [Orders.order_id <-> Orders.order_id, Orders.customer_id <-> Orders.customer_id]");

        model.addCell(cellA);
        model.addCell(cellB);
        model.addCell(cellC);
        model.addCell(cellD);
        model.addCell(cellE);
        model.addCell(cellF);
        model.addCell(cellG);
        model.addCell(cellH);
        model.addCell(cellI);
        model.addCell(cellJ);
        model.addCell(cellK);
        model.addCell(cellL);
        model.addCell(cellM);

        //algebric tree
        final CorneredEdge edgeAlgebricTreeProjection = new CorneredEdge(cellA, cellB, Orientation.VERTICAL);
        model.addEdge(edgeAlgebricTreeProjection);

        //algebric multi stores tree
        final CorneredEdge edgeAlgebricMultiStoresTreeProjection = new CorneredEdge(cellC, cellD, Orientation.VERTICAL);
        model.addEdge(edgeAlgebricMultiStoresTreeProjection);

        final CorneredEdge edgeMultiStoreDataType1 = new CorneredEdge(cellD, cellE, Orientation.VERTICAL);
        model.addEdge(edgeMultiStoreDataType1);
        final CorneredEdge edgeMultiStoreDataType2 = new CorneredEdge(cellD, cellF, Orientation.VERTICAL);
        model.addEdge(edgeMultiStoreDataType2);

        //algebric multi stores tree with transformation
        final Edge edgeAlgebricMultiStoresWTransferAndTransformationTreeProjection = new Edge(cellH, cellG);
        model.addEdge(edgeAlgebricMultiStoresWTransferAndTransformationTreeProjection);

        final CorneredEdge edgeMultiStoreTransfer1 = new CorneredEdge(cellI, cellH, Orientation.HORIZONTAL);
        model.addEdge(edgeMultiStoreTransfer1);
        final CorneredEdge edgeMultiStoreTransfer2 = new CorneredEdge(cellJ, cellH, Orientation.HORIZONTAL);
        model.addEdge(edgeMultiStoreTransfer2);

        final CorneredEdge edgeMultiStoreTransformation1 = new CorneredEdge(cellK, cellI, Orientation.HORIZONTAL);
        model.addEdge(edgeMultiStoreTransformation1);

        final CorneredEdge edgeMultiStoresWTransferAndTransformationDataType1 = new CorneredEdge(cellL, cellK, Orientation.HORIZONTAL);
        model.addEdge(edgeMultiStoresWTransferAndTransformationDataType1);
        final CorneredEdge edgeMultiStoresWTransferAndTransformationDataType2 = new CorneredEdge(cellM, cellJ, Orientation.HORIZONTAL);
        model.addEdge(edgeMultiStoresWTransferAndTransformationDataType2);

        graph.endUpdate();
    }
}
