package com.main.irit;

import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import fr.irit.module3.TransformationTransferAlgebraicTree;
import fxgraph.cells.LabelCell;
import fxgraph.edges.CorneredEdge;
import fxgraph.graph.Graph;
import fxgraph.graph.ICell;
import fxgraph.graph.Model;
import fxgraph.layout.AbegoTreeLayout;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    }

    private void interactWithPolystore(){
        //faire verif que textfiel est jamais vide + alerte quand texte est vide
        String query = welcomeText.getText();
//        String query = "SELECT Customers.customer_id, Orders.order_id, Orders.total_price, Products.brand " +
//                "FROM Reviews, Orders, Products, Customers " +
//                "WHERE (Orders.total_price > 10000 OR Customers.zipcode = 31000) " +
//                "AND Products.brand = nike AND Products.p_price > 100 " +
//                "AND Reviews.order_id = Orders.order_id " +
//                "AND Reviews.product_id = Products.product_id " +
//                "AND Customers.customer_id = Orders.customer_id ";
//
        Query queryParsed = QueryParserUtils.parse(query);
        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);

        System.out.println("Algebraic Tree : ");
        globalAlgebraicTree.getRootNode().print("");

        MultistoreAlgebraicTree mat = new MultistoreAlgebraicTree(globalAlgebraicTree);

        System.out.println("");
        System.out.println("Algebraic Multi-stores Tree : ");
        mat.getMultistoreAlgebraicTree().print("");

        TransformationTransferAlgebraicTree ttat = new TransformationTransferAlgebraicTree(mat);
        System.out.println("");
        System.out.println("Algebraic Multi-stores Tree : ");
        ttat.getTransformationTransferAlgebraicTree().print("");
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
        final ICell cellA = new LabelCell("π *");
        final ICell cellB = new LabelCell(" Orders");
        final ICell cellC = new LabelCell("π *");
        final ICell cellD = new LabelCell("⨝ Orders.order_id = Orders.order_id");
        final ICell cellE = new LabelCell("Orders : DB3-DOCUMENT \n [Orders.order_id <-> Orders.order_id, Orders.total_price <-> Orders.total_price]");
        final ICell cellF = new LabelCell("Orders : DB2-RELATIONAL \n [Orders.order_id <-> Orders.order_id, Orders.customer_id <-> Orders.customer_id]");
        final ICell cellG = new LabelCell("π *");
        final ICell cellH = new LabelCell("⨝ Orders.order_id = Orders.order_id");
//        final ICell cellI = new LabelCell("Transfer : DB3 -> DB1");
//        final ICell cellJ = new LabelCell("Transfer : DB2 -> DB1");
//        final ICell cellK = new LabelCell("Transformation : DOCUMENT -> RELATIONAL");
//        final ICell cellL = new LabelCell("Orders : DB3-DOCUMENT [Orders.order_id <-> Orders.order_id, Orders.total_price <-> Orders.total_price]");
//        final ICell cellM = new LabelCell("Orders : DB2-RELATIONAL [Orders.order_id <-> Orders.order_id, Orders.customer_id <-> Orders.customer_id]");

        model.addCell(cellA);
        model.addCell(cellB);
        model.addCell(cellC);
        model.addCell(cellD);
        model.addCell(cellE);
        model.addCell(cellF);
//        model.addCell(cellG);
//        model.addCell(cellH);
//        model.addCell(cellI);
//        model.addCell(cellJ);
//        model.addCell(cellK);
//        model.addCell(cellL);
//        model.addCell(cellM);

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
//        final Edge edgeAlgebricMultiStoresWTransferAndTransformationTreeProjection = new Edge(cellH, cellG);
//        model.addEdge(edgeAlgebricMultiStoresWTransferAndTransformationTreeProjection);
//
//        final CorneredEdge edgeMultiStoreTransfer1 = new CorneredEdge(cellI, cellH, Orientation.HORIZONTAL);
//        model.addEdge(edgeMultiStoreTransfer1);
//        final CorneredEdge edgeMultiStoreTransfer2 = new CorneredEdge(cellJ, cellH, Orientation.HORIZONTAL);
//        model.addEdge(edgeMultiStoreTransfer2);
//
//        final CorneredEdge edgeMultiStoreTransformation1 = new CorneredEdge(cellK, cellI, Orientation.HORIZONTAL);
//        model.addEdge(edgeMultiStoreTransformation1);
//
//        final CorneredEdge edgeMultiStoresWTransferAndTransformationDataType1 = new CorneredEdge(cellL, cellK, Orientation.HORIZONTAL);
//        model.addEdge(edgeMultiStoresWTransferAndTransformationDataType1);
//        final CorneredEdge edgeMultiStoresWTransferAndTransformationDataType2 = new CorneredEdge(cellM, cellJ, Orientation.HORIZONTAL);
//        model.addEdge(edgeMultiStoresWTransferAndTransformationDataType2);

        graph.endUpdate();
    }
}
