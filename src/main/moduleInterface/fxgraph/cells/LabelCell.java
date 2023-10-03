package fxgraph.cells;

import fxgraph.graph.Graph;
import fxgraph.graph.IEdge;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.w3c.dom.*;

public class LabelCell extends AbstractCell{
    String labelview;
    public LabelCell(String cellId) {
//        super(cellId);
        labelview = cellId;
//        setView(view);
    }

    @Override
    public Region getGraphic(Graph graph) {
        final Label view = new Label(labelview);

        final Pane pane = new Pane(view);
        pane.setPrefSize(view.getWidth(),view.getHeight());

//        CellGestures.makeResizable(pane);

        return pane;
    }


    @Override
    public DoubleBinding getXAnchor(Graph graph, IEdge edge) {
        return super.getXAnchor(graph, edge);
    }

    @Override
    public DoubleBinding getYAnchor(Graph graph, IEdge edge) {
        return super.getYAnchor(graph, edge);
    }
}
