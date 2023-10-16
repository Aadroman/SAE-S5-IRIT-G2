package fxgraph.graph;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class NodeGestures {

    final DragContext dragContext = new DragContext();
    final Graph graph;
    private List<ICell> cellList = new ArrayList<ICell>();

    public NodeGestures(Graph graph) {
        this.graph = graph;
        this.cellList = this.graph.getModel().getAllCells();
    }

    public void makeDraggable(final Node node) {
        node.setOnMouseClicked(onMouseClickedEventHandler);
        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnMouseDragged(onMouseDraggedEventHandler);
        node.setOnMouseReleased(onMouseReleasedEventHandler);
    }

    public void makeUndraggable(final Node node) {
        node.setOnMousePressed(null);
        node.setOnMouseDragged(null);
        node.setOnMouseReleased(null);
    }

    final EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            final Node node = (Node) event.getSource();

            final double scale = graph.getScale();

            dragContext.x = node.getBoundsInParent().getMinX() * scale - event.getScreenX();
            dragContext.y = node.getBoundsInParent().getMinY() * scale - event.getScreenY();
        }
    };

    final EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            if(!event.isSecondaryButtonDown()) {
                final Node node = (Node) event.getSource();

                double offsetX = event.getScreenX() + dragContext.x;
                double offsetY = event.getScreenY() + dragContext.y;

                // adjust the offset in case we are zoomed
                final double scale = graph.getScale();

                offsetX /= scale;
                offsetY /= scale;

                node.relocate(offsetX, offsetY);
            }
        }
    };

    final EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

        }
    };

    final EventHandler<MouseEvent> onMouseClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            final Node node = (Node) event.getSource();


            ICell cell = null;

            int i = 0;
            while (i < cellList.size()) {

                if (cellList.get(i).equals(node)) {
                    cell = cellList.get(i);
                    i = cellList.size();
                }
                i++;
            }

            if (cell != null) {
                if (cell.getSelected()) {
                    cell.setSelected(false);
                } else {
                    cell.setSelected(true);
                }
                System.out.println(cell.getSelected());
            }


        }

    };

    public static class DragContext {
        double x;
        double y;
    }
}