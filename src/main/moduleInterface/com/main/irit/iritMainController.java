package com.main.irit;

import fr.irit.algebraictree.Projection;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import fr.irit.module3.TransformationTransferAlgebraicTree;
import fxgraph.cells.JointureCell;
import fxgraph.cells.LabelCell;
import fxgraph.cells.ProjectionCell;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class iritMainController implements Initializable {

    private iritMainApplication app;
    private Stage primaryStage;

    @FXML
    private TextField welcomeText;

    @FXML
    private TitledPane paneGraph;

    @FXML
    private Button requestOK;

    @FXML
    protected void onOkButtonClick() throws IOException {
//        Graph graph = new Graph();
//        // Add content to graph
//        populateGraph(graph);
//        // Layout nodes
//        AbegoTreeLayout layout = new AbegoTreeLayout(200, 200, Configuration.Location.Bottom);
//        graph.layout(layout);
        paneGraph = updatePane(paneGraph);
//        this.app.graph();
    }

    public void initContext(Stage mainStage, iritMainApplication iritMainApplication) {
        this.primaryStage = mainStage;
        this.app = iritMainApplication;
    }

    public void displayDialog() {this.primaryStage.show();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllTablesDB();
    }

    @FXML
    private void interactWithPolystore() {
        List<String> tablesnames = getAllTablesDB();
        boolean allTablesFound = false;
        try {
            //faire verif que textfiel est jamais vide + alerte quand texte est vide
            String query = this.welcomeText.getText();
            // Define a regex pattern to match table names in the SQL query
            Pattern pattern = Pattern.compile("from\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(query);
            List<String> foundTables = new ArrayList<>();
            // Find and collect all table names mentioned in the SQL query
            while (matcher.find()) {
                foundTables.add(matcher.group(1));
            }
            // Check if the found tables are in the predefined table names list
            allTablesFound = true;
            for (String tableName : foundTables) {
                if (!tablesnames.contains(tableName)) {
                    allTablesFound = false;
                }
            }

            Query queryParsed = QueryParserUtils.parse(query);
            GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);

            System.out.println("Algebraic Tree : ");
            globalAlgebraicTree.getRootNode().print("");

            Projection proj = (Projection) globalAlgebraicTree.getRootNode();

            createGraph(proj);

            MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);

            System.out.println("");
            System.out.println("Algebraic Multi-stores Tree : ");
            mat.getMultistoreAlgebraicTree().print("");

            TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
            System.out.println("");
            System.out.println("Algebraic Multi-stores Tree : ");
            ttat.getTransformationTransferAlgebraicTree().print("");
        } catch (Exception e) {
            if (welcomeText.getText().isEmpty()) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Requête vide");
                warning.setHeaderText("Votre requête est vide !");
                warning.setContentText(e.toString());
                warning.showAndWait();
            } else if (allTablesFound==false) {
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

    //temp method to test the generation of a globalAlgebraicTree
    private void createGraph(Projection proj){
        Pane childPane;
        Graph graph = new Graph();
        // Add content to graph

        final Model model = graph.getModel();
        graph.beginUpdate();

        final ProjectionCell projectionGlobal = new ProjectionCell("π "+proj.toString());
        String child = proj.getChild().toString();
        final ICell labelGlobal = new LabelCell(child);

        model.addCell(projectionGlobal);
        model.addCell(labelGlobal);

        model.addEdge(projectionGlobal, labelGlobal);

        graph.endUpdate();

        // Layout nodes
        AbegoTreeLayout layout = new AbegoTreeLayout(100, 500, Configuration.Location.Bottom);
        graph.layout(layout);
        childPane = graph.getCanvas();
        paneGraph.setContent(childPane);
    }

    protected List<String> getAllTablesDB(){
        List<String> tablesNames = new ArrayList<>();
        // Specify the path to your JSON file
        String documentJSON = "src/main/java/fr/irit/module2/UnifiedView/documentUnifiedView.json";
        String relationalJSON = "src/main/java/fr/irit/module2/UnifiedView/relationalUnifiedView.json";

        try{
            // Initialize the ObjectMapper (Jackson library)
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file and parse it into a JsonNode
            JsonNode documentNode = objectMapper.readTree(new File(documentJSON));
            JsonNode relationalNode = objectMapper.readTree(new File(relationalJSON));

            // Get the "uvTables" array
            JsonNode documentUVTablesNode = documentNode.get("uvTables");
            JsonNode relationalUVTablesNode = relationalNode.get("uvTables");

            // Create a List to store the "label" values
            List<String> labelList = new ArrayList<>();

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
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tablesNames;
    }

    private TitledPane updatePane(TitledPane pane) {
        Pane childPane;
        Graph graph = new Graph();
        // Add content to graph
        populateGraph(graph);
        // Layout nodes
        AbegoTreeLayout layout = new AbegoTreeLayout(100, 500, Configuration.Location.Bottom);
        graph.layout(layout);
        childPane = graph.getCanvas();
        pane.setContent(childPane);
        return pane;
    }

    private void populateGraph(Graph graph) {
        final Model model = graph.getModel();
        graph.beginUpdate();


        //les cellules en commentaires sont remplacées par des cellules de projection, selection ou jointure
        //elles sont mises de coté pour l'implementation de différents types de cells autre que des labelCell

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
