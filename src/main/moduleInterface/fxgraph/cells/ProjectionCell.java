package fxgraph.cells;

import fxgraph.graph.Graph;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class ProjectionCell extends AbstractCell {

    Label projectionLabel = new Label();

    StackPane projectionGraph = new StackPane();

    public ProjectionCell(String text) {
        this.projectionLabel.setText(text);
    }

    /**
     * Return a javaFx StackPane with projection polygon and label
     *
     * @param graph
     * @return pane
     */
    @Override
    public Region getGraphic(Graph graph) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tree_icons/projection.fxml")));
            Polygon projectionPolygon = (Polygon) root.lookup("#projectionPolygon");

            ObservableList<Double> projectionPolygonPoints = projectionPolygon.getPoints();
            double projectionPolygoneWidth = projectionPolygonPoints.get(projectionPolygonPoints.size()-2) - projectionPolygonPoints.get(0);
            double projectionTextWidth = new Text(this.projectionLabel.getText()).getLayoutBounds().getWidth();
            double widthDelta = projectionPolygoneWidth - projectionTextWidth ;
            double projectionLabelWidth;

            if (widthDelta < 0) {
                // Max iconPolygon and iconLabel width
                if (widthDelta < -100) {
                    widthDelta = -100;
                    final Tooltip tooltip = new Tooltip();
                    tooltip.setText(this.projectionLabel.getText());
                    tooltip.setShowDelay(Duration.seconds(0.5));
                    this.projectionLabel.setTooltip(tooltip);
                }
                projectionLabelWidth = abs(widthDelta) + projectionPolygoneWidth;
                double finalWidthDelta = widthDelta;
                // Increase projectionPolygon width to fit with text
                IntStream.range(0, 4).forEachOrdered(index -> {
                    if (index % 2 == 0) {
                        projectionPolygonPoints.set(index, projectionPolygonPoints.get(index) + finalWidthDelta);
                    }
                });
            } else {
                projectionLabelWidth = projectionPolygoneWidth;
            }

            this.projectionLabel.setMaxWidth(projectionLabelWidth);
            this.projectionLabel.setAlignment(Pos.CENTER);
            String styles =
                "-fx-font-weight: 700;" +
                "-fx-text-fill: black;" ;
            this.projectionLabel.setStyle(styles);

            this.projectionGraph.getChildren().add(projectionPolygon);
            this.projectionGraph.getChildren().add(this.projectionLabel);

            return this.projectionGraph;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setSelected(boolean select) {
        // TODO Auto-generated method stub
    }

}
