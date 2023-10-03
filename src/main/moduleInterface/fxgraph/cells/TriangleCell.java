package fxgraph.cells;


import fxgraph.graph.Graph;
import fxgraph.graph.IEdge;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Scale;

public class TriangleCell extends AbstractCell {

	private CellGestures cg = new CellGestures();

	public TriangleCell() {
	}

	@Override
	public Region getGraphic(Graph graph) {
		final double width = 50;
		final double height = 50;

		final Polygon view = new Polygon(width / 2, 0, width, height, 0, height);

		view.setStroke(Color.RED);
		view.setFill(Color.RED);

		final Pane pane = new Pane(view);
		pane.setPrefSize(50, 50);
		final Scale scale = new Scale(1, 1);
		view.getTransforms().add(scale);
		scale.xProperty().bind(pane.widthProperty().divide(50));
		scale.yProperty().bind(pane.heightProperty().divide(50));
		cg.makeResizable(pane);

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