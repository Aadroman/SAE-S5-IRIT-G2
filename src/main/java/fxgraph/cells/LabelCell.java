package fxgraph.cells;

import fxgraph.graph.Graph;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LabelCell extends AbstractCell{

    Pane labelPane = new Pane();

    double sourceEdgeXPosition, sourceEdgeYPosition, targetEdgeXPosition, targetEdgeYPosition;

    public LabelCell(String text, String tooltipText) {
        Label labelView = new Label(text);
        double sourceEdgeXPosition = new Text(text).getLayoutBounds().getWidth();

        if (tooltipText != null){
            final Tooltip tooltip = new Tooltip();
            tooltip.setText(tooltipText);
            tooltip.setShowDelay(Duration.seconds(0.5));
            tooltip.setStyle("-fx-font-size: 14");
            labelView.setTooltip(tooltip);
        }

        this.sourceEdgeXPosition = sourceEdgeXPosition/2;
        this.targetEdgeXPosition = this.sourceEdgeXPosition;
        this.targetEdgeYPosition = 19;

        String styles =
            "-fx-font-weight: 700;" +
            "-fx-text-fill: black;" ;
        labelView.setStyle(styles);
        this.labelPane.getChildren().add(labelView);
    }

    @Override
    public Region getGraphic(Graph graph) {
        return this.labelPane;
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
}
