package fxgraph.cells;

import fxgraph.graph.Graph;
import fxgraph.graph.IEdge;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.abs;

public class TransformCell extends AbstractCell{

    String leftTransformText;

    String rightTransformText;

    double sourceEdgeXPosition, sourceEdgeYPosition, targetEdgeXPosition, targetEdgeYPosition;

    StackPane transformGraph = new StackPane();

    public TransformCell() {
    }

    public TransformCell(String texte) {
        try {
            String[] separated = texte.split("->");
            this.leftTransformText = separated[0];
            this.rightTransformText = separated[1];

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tree_icons/transform.fxml")));
            Group transformGroup = (Group) root.lookup("#transformGroup");
            Label transformLeftLabel = (Label) transformGroup.lookup("#transformLeftLabel");
            Label transformRightLabel = (Label) transformGroup.lookup("#transformRightLabel");
            Label transformArrowSign = (Label) transformGroup.lookup("#transformArrowSign");
            Polygon transformBas = (Polygon) transformGroup.lookup("#transformBas");
            Polygon transformHaut = (Polygon) transformGroup.lookup("#transformHaut");
            ObservableList<Double> transformBasPoints = transformBas.getPoints();
            ObservableList<Double> transformHautPoints = transformHaut.getPoints();

            double transformLeftTextWidth = new Text(this.leftTransformText).getLayoutBounds().getWidth();

            transformLeftLabel.setText(this.leftTransformText);
            transformRightLabel.setText(this.rightTransformText);

            transformLeftLabel.setLayoutX(transformLeftLabel.getLayoutX()-transformLeftTextWidth);

            this.sourceEdgeXPosition = abs(transformLeftLabel.getLayoutX()) + abs(transformArrowSign.getLayoutX()) + 4;
            this.sourceEdgeYPosition = abs(transformHautPoints.get(1) - transformHautPoints.get(7)) / 2;
            this.targetEdgeXPosition = abs(transformLeftLabel.getLayoutX()) + abs(transformArrowSign.getLayoutX()) + 4;
            this.targetEdgeYPosition = abs(transformBasPoints.get(5) - transformHautPoints.get(7));


            String styles =
                    "-fx-font-weight: 700;" +
                            "-fx-text-fill: black;" ;
            transformLeftLabel.setStyle(styles);
            transformRightLabel.setStyle(styles);

            this.transformGraph.getChildren().add(transformGroup);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Region getGraphic(Graph graph) {
        return this.transformGraph;
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


}