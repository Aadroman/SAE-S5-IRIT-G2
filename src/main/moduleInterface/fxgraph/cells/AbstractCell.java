package fxgraph.cells;

import fxgraph.graph.Graph;
import fxgraph.graph.ICell;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCell implements ICell {

    private final List<ICell> children = new ArrayList<>();
    private final List<ICell> parents = new ArrayList<>();
    private boolean selected = false;

    @Override
    public void addCellChild(ICell cell) {
        children.add(cell);
    }

    @Override
    public List<ICell> getCellChildren() {
        return children;
    }

    @Override
    public void addCellParent(ICell cell) {
        parents.add(cell);
    }

    @Override
    public List<ICell> getCellParents() {
        return parents;
    }

    @Override
    public void removeCellChild(ICell cell) {
        children.remove(cell);
    }

    @Override
    public void setSelected(boolean select) {
        this.selected = select;
    }

    @Override
    public boolean getSelected() {
        return this.selected;
    }

    public abstract Region getGraphic(Graph graph);

	public abstract double getSourceEdgeXPosition();

	public abstract double getSourceEdgeYPosition();

	public abstract double getTargetEdgeXPosition();

	public abstract double getTargetEdgeYPosition();
}
