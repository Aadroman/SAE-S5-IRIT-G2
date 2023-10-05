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

    Label iconLabel = new Label();

    Polygon iconPolygon = new Polygon();

    public ProjectionCell(String text) {
        this.iconLabel.setText(text);
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
            iconPolygon = (Polygon) root.lookup("#projectionIcon");

            ObservableList<Double> polygonePoints = iconPolygon.getPoints();
            double iconPolygoneWidth = polygonePoints.get(polygonePoints.size()-2) - polygonePoints.get(0);
            double textWidth = new Text(this.iconLabel.getText()).getLayoutBounds().getWidth();
            double widthDelta = iconPolygoneWidth - textWidth ;
            double iconLabelWidth;

            if (widthDelta < 0) {
                // Max iconPolygon and iconLabel width
                if (widthDelta < -100) {
                    widthDelta = -100;
                }
                iconLabelWidth = abs(widthDelta) + iconPolygoneWidth;
                double finalWidthDelta = widthDelta;
                // Increase iconPolygon width to fit with text
                IntStream.range(0, 4).forEachOrdered(index -> {
                    if (index % 2 == 0) {
                        polygonePoints.set(index, polygonePoints.get(index) + finalWidthDelta);
                    }
                });
            } else {
                iconLabelWidth = iconPolygoneWidth;
            }

            this.iconLabel.setMaxWidth(iconLabelWidth);
            this.iconLabel.setAlignment(Pos.CENTER);
            String styles =
                "-fx-font-weight: 700;" +
                "-fx-text-fill: white;" ;
            this.iconLabel.setStyle(styles);

            final Tooltip tooltip = new Tooltip();
            tooltip.setText(this.iconLabel.getText());
            tooltip.setShowDelay(Duration.seconds(0.5));
            this.iconLabel.setTooltip(tooltip);

            final StackPane pane = new StackPane();
            pane.getChildren().add(this.iconPolygon);
            pane.getChildren().add(this.iconLabel);

            return pane;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setSelected(boolean select) {
        // TODO Auto-generated method stub
    }

}
