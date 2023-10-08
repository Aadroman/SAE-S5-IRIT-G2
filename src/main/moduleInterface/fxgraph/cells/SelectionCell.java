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

import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.abs;

public class SelectionCell extends AbstractCell {

    String selectionText;

    StackPane selectionGraph = new StackPane();

    double sourceEdgeXPosition, sourceEdgeYPosition, targetEdgeXPosition, targetEdgeYPosition;

    public SelectionCell(String text) {
        try {
            this.selectionText = text;
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tree_icons/selection.fxml")));
            Group selectionGroup = (Group) root.lookup("#selectionGroup");
            Label selectionLabel = (Label) selectionGroup.lookup("#selectionLabel");
            Polygon selectionPolygon = (Polygon) selectionGroup.lookup("#selectionPolygon");
            ObservableList<Double> projectionPolygonPoints = selectionPolygon.getPoints();
            this.targetEdgeYPosition = abs(projectionPolygonPoints.get(2) - projectionPolygonPoints.get(4));

            selectionLabel.setText(this.selectionText);

            String styles =
                "-fx-font-weight: 700;" +
                "-fx-text-fill: black;" ;
            selectionLabel.setStyle(styles);

            this.selectionGraph.getChildren().add(selectionGroup);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Region getGraphic(Graph graph) {
        return this.selectionGraph;
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
        return targetEdgeXPosition;
    }

    @Override
    public double getTargetEdgeYPosition() {
        return targetEdgeYPosition;
    }

    @Override
    public void setSelected(boolean select) {
        // TODO Auto-generated method stub
    }

}
