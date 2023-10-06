package fxgraph.layout;

import fxgraph.graph.Graph;
import fxgraph.graph.ICell;
import org.abego.treelayout.Configuration.Location;
import org.abego.treelayout.NodeExtentProvider;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;


public class AbegoTreeLayout implements Layout {

    private DefaultConfiguration<ICell> configuration;

    public AbegoTreeLayout() {
        this(100, 45, Location.Top);
    }

    public AbegoTreeLayout(double gapBetweenLevels, double gapBetweenNodes, Location location) {
        this(new DefaultConfiguration<ICell>(gapBetweenLevels, gapBetweenNodes, location));
    }

    public AbegoTreeLayout(DefaultConfiguration<ICell> defaultConfiguration) {
        this.configuration = defaultConfiguration;
    }

    public void addRecursively(DefaultTreeForTreeLayout<ICell> layout, ICell node) {
        node.getCellChildren().forEach(cell -> {
            if (!layout.hasNode(cell)) {
                layout.addChild(node, cell);
                addRecursively(layout, cell);
            }
        });
    }

    @Override
    public void execute(Graph graph) {
        final DefaultTreeForTreeLayout<ICell> layout = new DefaultTreeForTreeLayout<>(graph.getModel().getRoot());
        addRecursively(layout, graph.getModel().getRoot());
        final NodeExtentProvider<ICell> nodeExtentProvider = new NodeExtentProvider<ICell>() {

            @Override
            public double getWidth(ICell tn) {
                if (tn == graph.getModel().getRoot()) {
                    return 0;
                }
                return graph.getGraphic(tn).getWidth();
            }

            @Override
            public double getHeight(ICell tn) {
                if (tn == graph.getModel().getRoot()) {
                    return 0;
                }
                return graph.getGraphic(tn).getHeight();
            }
        };
        final TreeLayout<ICell> treeLayout = new TreeLayout<>(layout, nodeExtentProvider, configuration);
        treeLayout.getNodeBounds().entrySet().stream().filter(entry -> entry.getKey() != graph.getModel().getRoot()).forEach(entry -> {
            graph.getGraphic(entry.getKey()).setLayoutX(entry.getValue().getX());
            // Adjust Y positioning to reduce overhead space
            graph.getGraphic(entry.getKey()).setLayoutY(entry.getValue().getY() - 100);
        });
    }
}
