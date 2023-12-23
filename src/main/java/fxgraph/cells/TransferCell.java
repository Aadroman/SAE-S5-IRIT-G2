package fxgraph.cells;

import fxgraph.graph.Graph;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.abs;

public class TransferCell extends AbstractCell {

    String leftTransferText;

    String rightTransferText;

    StackPane transferGraph = new StackPane();

    double sourceEdgeXPosition, sourceEdgeYPosition, targetEdgeXPosition, targetEdgeYPosition;

    public TransferCell(String text) {
        try {
            String[] separated = text.split("->");
            this.leftTransferText = separated[0];
            this.rightTransferText = separated[1];

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tree_icons/transfer.fxml")));
            Group transferGroup = (Group) root.lookup("#transferGroup");
            Label transferLeftLabel = (Label) transferGroup.lookup("#transferLeftLabel");
            Label transferRightLabel = (Label) transferGroup.lookup("#transferRightLabel");
            Polygon transferPolygon = (Polygon) transferGroup.lookup("#transferPolygon");
            ObservableList<Double> transferPolygonPoints = transferPolygon.getPoints();

            double jointureLeftTextWidth = new Text(this.leftTransferText).getLayoutBounds().getWidth();

            transferLeftLabel.setText(this.leftTransferText);
            transferRightLabel.setText(this.rightTransferText);

            transferLeftLabel.setLayoutX(transferLeftLabel.getLayoutX()-jointureLeftTextWidth);

            this.sourceEdgeXPosition = abs(transferPolygonPoints.get(0) - transferPolygonPoints.get(4)) + 10;
            this.sourceEdgeYPosition = transferPolygonPoints.get(1);
            this.targetEdgeXPosition = this.sourceEdgeXPosition;
            this.targetEdgeYPosition = transferPolygonPoints.get(3);

            String styles =
                "-fx-font-weight: 700;" +
                "-fx-text-fill: black;" ;
            transferLeftLabel.setStyle(styles);
            transferRightLabel.setStyle(styles);

            this.transferGraph.getChildren().add(transferGroup);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Region getGraphic(Graph graph) {
        return this.transferGraph;
    }

    @Override
    public double getSourceEdgeXPosition() {
        return this.sourceEdgeXPosition;
    }

    @Override
    public double getSourceEdgeYPosition() {
        return this.sourceEdgeYPosition;
    }

    @Override
    public double getTargetEdgeXPosition() {
        return this.targetEdgeXPosition;
    }

    @Override
    public double getTargetEdgeYPosition() {
        return this.targetEdgeYPosition;
    }

    @Override
    public void setSelected(boolean select) {
        // TODO Auto-generated method stub

    }

}
