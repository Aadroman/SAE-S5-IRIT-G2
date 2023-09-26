package fr.sae.algebraictree;

import fr.irit.algebraictree.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ETreeNode {

    //region ATTRIBUTES
    private ETreeNode parent;

    public static ETreeNode createTree (TreeNode node) {
        if(node instanceof Projection) {
            return new EProjection((Projection)node);
        } else  if(node instanceof Selection) {
            return new ESelection((Selection)node);
        } else if(node instanceof Join) {
            return new EJoin((Join)node);
        }  else if(node instanceof Table) {
            return new ETable((Table) node);
        } else if(node instanceof Transfer) {
            return new ETransfer((Transfer) node);
        }

        return null;
    }


    //endregion
    //region GETTERS & SETTERS
    public ETreeNode getParent() { return this.parent; }
    public void setParent(ETreeNode parent) { this.parent = parent; }
    //endregion
    //region METHODS
    public boolean isRoot() {
        return this.parent == null;
    }

    public String toString() {
        return null;
    }

    /**
     * Return a list of tables included in the node and its children
     * This method is called recursively on its child nodes
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
    public Set<DotNotation> listDistinctColumnsRecursive() {

        return null;
    }

    public void addChildren(ETreeNode... children) {
    }

    public void print(String prefix) {

    }

    public void renameColumnsRecursive(Map<DotNotation, DotNotation> columnNamingMap) {

    }
    //TODO rename table also ?
    //endregion
}
