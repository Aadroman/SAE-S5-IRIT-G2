package fr.sae.algebraictree;

import fr.irit.algebraictree.DotNotation;
import fr.irit.algebraictree.Join;

import java.util.*;

public class EJoin extends ETreeNode {

    //region ATTRIBUTES
    private ETreeNode leftChild;

    private ETreeNode rightChild;

    private final EDotNotation[] condition;
    //endregion

    //region CONSTRUCTOR
    /**
     * @param join
     */
    public EJoin (Join join) {
        DotNotation leftChild = join.getCondition()[0];
        DotNotation rightChild = join.getCondition()[1];
        this.condition = new EDotNotation[]{new EDotNotation(leftChild), new EDotNotation(rightChild)};
        this.leftChild = ETreeNode.createTree(join.getLeftChild());
        this.rightChild = ETreeNode.createTree(join.getRightChild());
    }
    //endregion

    //region GETTERS & SETTERS
    public ETreeNode getLeftChild() { return leftChild; }
    public ETreeNode getRightChild() {
        return rightChild;
    }
    public EDotNotation[] getCondition() {
        return condition;
    }
    //endregion

    //region METHODS
    /**
     * @param children zero or more
     */
    @Override
    public void addChildren(ETreeNode... children) {
        if(children.length == 2){
            this.leftChild = children[0];
            this.rightChild = children[1];
            leftChild.setParent(this);
            rightChild.setParent(this);
        } else {
            throw new  UnsupportedOperationException("A Projection node should have 2 children");
        }
    }

    /**
     * @param prefix
     */
    @Override
    public void print(String prefix) {
        System.out.print(prefix + "└── ⨝ " + this + "\n");
        this.rightChild.print(prefix + "    ");
        this.leftChild.print(prefix + "    ");
    }

    /**
     * Don't rename because Join columns name should be the same on unified view and real implementation
     *
     * @param columnNamingMap
     */
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {
        this.leftChild.renameColumnsRecursive(columnNamingMap);
        this.rightChild.renameColumnsRecursive(columnNamingMap);
    }

    /**
     * @return
     */
    @Override
    public String toString(){
        return this.condition[0].toString() + " = " + this.condition[1].toString();
    }

    /**
     * @return
     */
    @Override
    public List<String> listIncludedTablesRecursive() {
        List<String> tmpList =  this.leftChild.listIncludedTablesRecursive();
        tmpList.addAll(this.rightChild.listIncludedTablesRecursive());
        return tmpList;
    }

    /**
     * A Join node can be the lowest node which contain n tables.
     * Or one of its children may be the lowest
     *
     * @return null or itself or result of the same method on one of its children
     */
    @Override
    public ETreeNode findLowestNodeContainingTables(List<String> tableList) {
        if(!new HashSet<>(this.listIncludedTablesRecursive()).containsAll(tableList)){
            return null;
        } else if(new HashSet<>(this.leftChild.listIncludedTablesRecursive()).containsAll(tableList) && new HashSet<>(this.rightChild.listIncludedTablesRecursive()).containsAll(tableList)){
            return this; // case at module 2 when tables are duplicated
        } else if(new HashSet<>(this.leftChild.listIncludedTablesRecursive()).containsAll(tableList)){
            return this.leftChild.findLowestNodeContainingTables(tableList);
        } else if(new HashSet<>(this.rightChild.listIncludedTablesRecursive()).containsAll(tableList)){
            return this.rightChild.findLowestNodeContainingTables(tableList);
        } else {
            return this;
        }
    }

    /**
     * @return
     */
    @Override
    public Set<EDotNotation> listDistinctColumnsRecursive() {
        Set<EDotNotation> includedColumns = new HashSet<>();
        Collections.addAll(includedColumns, this.condition);
        if(leftChild.listDistinctColumnsRecursive() != null) {
            includedColumns.addAll(leftChild.listDistinctColumnsRecursive());
        }
        if (rightChild.listDistinctColumnsRecursive() != null) {
            includedColumns.addAll(rightChild.listDistinctColumnsRecursive());
        }
        return includedColumns;
    }
    //endregion
}
