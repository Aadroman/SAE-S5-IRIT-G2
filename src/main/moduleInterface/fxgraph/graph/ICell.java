package fxgraph.graph;

import java.util.List;
import java.util.Set;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Region;

public interface ICell extends IGraphNode {

    public void addCellChild(ICell cell);

    public List<ICell> getCellChildren();

    public void addCellParent(ICell cell);

    public List<ICell> getCellParents();

    public void removeCellChild(ICell cell);

    public void setSelected(boolean select);

    public boolean getSelected();

    @Override
    public boolean equals(Object obj);

    default DoubleBinding getXAnchor(Graph graph, IEdge edge) {
        final Region graphic = graph.getGraphic(this);
        return graphic.layoutXProperty().add(graphic.widthProperty().divide(2));
    }

	default DoubleBinding getXAnchor(Graph graph, IEdge edge, double xPosition) {
		final Region graphic = graph.getGraphic(this);
		return graphic.layoutXProperty().add(xPosition);
	}

    default DoubleBinding getYAnchor(Graph graph, IEdge edge) {
        final Region graphic = graph.getGraphic(this);
        return graphic.layoutYProperty().add(graphic.heightProperty().divide(2));
    }

	default DoubleBinding getYAnchor(Graph graph, IEdge edge, double yPosition) {
		final Region graphic = graph.getGraphic(this);
		return graphic.layoutYProperty().add(yPosition);
	}

}
