package fr.sae.algebraictree;

import fr.irit.algebraictree.*;
import fr.irit.module2.UnifiedView.Store;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ETreeNode {

    public Store getStore() {
        return null;
    }

    //region ATTRIBUTES
    private ETreeNode parent;

    /**
     * @param node
     * @return
     */
    public static ETreeNode createTree(TreeNode node) {
        if (node instanceof Projection) {
            return new EProjection((Projection) node);
        } else if (node instanceof Selection) {
            return new ESelection((Selection) node);
        } else if (node instanceof Join) {
            return new EJoin((Join) node);
        } else if (node instanceof Table) {
            return new ETable((Table) node);
        } else if (node instanceof Transfer) {
            return new ETransfer((Transfer) node);
        } else if (node instanceof Transformation) {
            return new ETransformation((Transformation) node);
        }

        return null;
    }

    //endregion
    //region GETTERS & SETTERS
    /**
     * @return
     */
    public ETreeNode getParent() {
        return this.parent;
    }
    /**
     * @param parent
     */
    public void setParent(ETreeNode parent) {
        this.parent = parent;
    }

    //endregion
    //region METHODS

    /**
     * @return
     */
    public boolean isRoot() {
        return this.parent == null;
    }

    /**
     * @return
     */
    public String toString() {
        return null;
    }

    /**
     * Return a list of tables included in the node and its children
     * This method is called recursively on its child nodes
     *
     * @return
     */
    public List<String> listIncludedTablesRecursive() {
        return null;
    }

    /**
     * @param tableList List of table names searched
     * @return Lowest node which containing all tables specified in param
     */
    public ETreeNode findLowestNodeContainingTables(List<String> tableList) {
        return null;
    }

    /**
     * @return List of columns ('table.column') included in the node and its children
     */
    public Set<EDotNotation> listDistinctColumnsRecursive() {

        return null;
    }

    /**
     * @param children zero or more
     */
    public void addChildren(ETreeNode... children) { }

    /**
     * @param prefix
     */
    public void print(String prefix) {

    }

    /**
     * @param columnNamingMap
     */
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {

    }

    public ETreeNode[] getChild() {
        return new ETreeNode[0];
    }
    //TODO rename table also ?
    //endregion
}
