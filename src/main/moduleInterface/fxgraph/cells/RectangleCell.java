package fxgraph.cells;

import fxgraph.graph.Graph;
import fxgraph.graph.IEdge;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.*;

public class RectangleCell extends AbstractCell {

	public RectangleCell() {
	}

	@Override
	public Region getGraphic(Graph graph) {
		final Rectangle view = new Rectangle(50, 50);

		view.setStroke(Color.DODGERBLUE);
		view.setFill(Color.DODGERBLUE);

		final Pane pane = new Pane(view);
		pane.setPrefSize(50, 50);
		view.widthProperty().bind(pane.prefWidthProperty());
		view.heightProperty().bind(pane.prefHeightProperty());
		//CellGestures.makeResizable(pane);

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

	@Override
	public DoubleBinding getXAnchor(Graph graph, IEdge edge) {
		return super.getXAnchor(graph, edge);
	}

	@Override
	public DoubleBinding getYAnchor(Graph graph, IEdge edge) {
		return super.getYAnchor(graph, edge);
	}

}
