package fxgraph.cells;

import fxgraph.graph.Graph;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TransferCell extends AbstractCell {

    Label text = new Label();

    public TransferCell() {
    }

    public TransferCell(String texte) {
        this.text.setText(texte);
    }

    @Override
    public Region getGraphic(Graph graph) {
        final Polygon view = new Polygon();
        view.getPoints().addAll(new Double[]{
                -10.0, 15.0,
                -10.0, 35.0,
                40.0, 35.0,
                40.0, 50.0,
                70.0, 25.0,
                40.0, 0.0,
                40.0, 15.0
        });

        view.setStroke(Color.DODGERBLUE);
        view.setFill(Color.DODGERBLUE);

        Group group = new Group(view, text);

        text.setLayoutX(75);
        text.setLayoutY(15);

        final Pane pane = new Pane(group);
        pane.setPrefSize(50, 50);



        return pane;
    }

    @Override
    public double getSourceEdgeXPosition() {
        return 0;
    }

    @Override
    public double getSourceEdgeYPosition() {
        return 0;
    }

    @Override
    public double getTargetEdgeXPosition() {
        return 0;
    }

    @Override
    public double getTargetEdgeYPosition() {
        return 0;
    }

    @Override
    public void setSelected(boolean select) {
        // TODO Auto-generated method stub

    }

}
