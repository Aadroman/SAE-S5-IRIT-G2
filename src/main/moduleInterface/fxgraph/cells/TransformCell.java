package fxgraph.cells;

import fxgraph.graph.Graph;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TransformCell extends AbstractCell{

    Label text = new Label();

    public TransformCell() {
    }

    public TransformCell(String texte) {
        this.text.setText(texte);
    }

    @Override
    public Region getGraphic(Graph graph) {
        final Polygon view = new Polygon();
        view.getPoints().addAll(new Double[]{
                0.0, 30.0,
                0.0, 50.0,
                50.0, 50.0,
                50.0, 60.0,
                70.0, 40.0,
                50.0, 20.0,
                50.0, 30.0
        });

        final Polygon view2 = new Polygon();
        view2.getPoints().addAll(new Double[]{
                45.0, 25.0,
                45.0, 5.0,
                -5.0, 5.0,
                -5.0, -5.0,
                -25.0, 15.0,
                -5.0, 35.0,
                -5.0, 25.0
        });

        Group group = new Group(view);

        view.setStroke(Color.ORANGE);
        view.setFill(Color.ORANGE);
        view2.setStroke(Color.RED);
        view2.setFill(Color.RED);

        final Pane pane = new Pane(view, view2);
        pane.setPrefSize(50, 50);

        return pane;
    }
}