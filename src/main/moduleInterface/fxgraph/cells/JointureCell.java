package fxgraph.cells;

import fxgraph.graph.Graph;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class JointureCell extends AbstractCell {

    Label text = new Label();

    public JointureCell() {
    }

    public JointureCell(String texte) {
        this.text.setText(texte);
    }

    @Override
    public Region getGraphic(Graph graph) {
        final Polygon view = new Polygon();
        view.getPoints().addAll(new Double[]{
                -10.0, 0.0,
                -10.0, 50.0,
                60.0, 0.0,
                60.0, 50.0});

        view.setStroke(Color.DODGERBLUE);
        view.setFill(Color.DODGERBLUE);

        Group group = new Group(view, text);

        text.setLayoutX(65);
        text.setLayoutY(15);

        final Pane pane = new Pane(group);
        pane.setPrefSize(50, 50);



        return pane;
    }

    @Override
    public void setSelected(boolean select) {
        // TODO Auto-generated method stub

    }

}
