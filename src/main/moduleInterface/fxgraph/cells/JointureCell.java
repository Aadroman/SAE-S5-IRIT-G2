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

public class JointureCell extends AbstractCell {

    String leftJointureText;

    String rightJointureText;

    StackPane jointureGraph = new StackPane();

    boolean firstCall = true;

    double sourceEdgeXPosition, sourceEdgeYPosition, targetEdgeYPosition,rightTargetEdgeXPosition, leftTargetEdgeXPosition;

    public JointureCell(String text) {
        try {
            String[] separated = text.split("=");
            this.leftJointureText = separated[0];
            this.rightJointureText = separated[1];

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tree_icons/jointure.fxml")));
            Group jointureGroup = (Group) root.lookup("#jointureGroup");
            Label jointureLeftLabel = (Label) jointureGroup.lookup("#jointureLeftLabel");
            Label jointureRightLabel = (Label) jointureGroup.lookup("#jointureRightLabel");
            Label jointureEqualSign = (Label) jointureGroup.lookup("#jointureEqualSign");
            Polygon jointurePolygon = (Polygon) jointureGroup.lookup("#jointurePolygon");
            ObservableList<Double> projectionPolygonPoints = jointurePolygon.getPoints();

            double jointureLeftTextWidth = new Text(this.leftJointureText).getLayoutBounds().getWidth();

            jointureLeftLabel.setText(this.leftJointureText);
            jointureRightLabel.setText(this.rightJointureText);

            jointureLeftLabel.setLayoutX(jointureLeftLabel.getLayoutX()-jointureLeftTextWidth);

            this.sourceEdgeXPosition = abs(jointureLeftLabel.getLayoutX()) + abs(jointureEqualSign.getLayoutX()) + 4;
            this.sourceEdgeYPosition = abs(projectionPolygonPoints.get(1) - projectionPolygonPoints.get(7)) / 2;
            this.targetEdgeYPosition = abs(projectionPolygonPoints.get(1) - projectionPolygonPoints.get(3)) - 1;
            this.leftTargetEdgeXPosition = abs(jointureLeftLabel.getLayoutX()) - 10;
            this.rightTargetEdgeXPosition = abs(jointureLeftLabel.getLayoutX()) + abs(jointureRightLabel.getLayoutX()) - 7.5;

            String styles =
                "-fx-font-weight: 700;" +
                "-fx-text-fill: black;" ;
            jointureLeftLabel.setStyle(styles);
            jointureRightLabel.setStyle(styles);

            this.jointureGraph.getChildren().add(jointureGroup);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Region getGraphic(Graph graph) {
        return this.jointureGraph;
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
        if (this.firstCall) {
            firstCall = false;
            return leftTargetEdgeXPosition;
        } else {
            return rightTargetEdgeXPosition;
        }
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
