package fr.sae.algebraictree;

import fr.irit.algebraictree.Transfer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ETransfer extends ETreeNode {

    private ETreeNode child;

    private final String sourceDatabase;

    private final String targetDatabase;

    /**
     * @param sourceDatabase
     * @param targetDatabase
     */
    public ETransfer(String sourceDatabase, String targetDatabase){
        this.sourceDatabase = sourceDatabase;
        this.targetDatabase = targetDatabase;
    }

    /**
     * @param node
     */
    public ETransfer(Transfer node) {
        this.child = ETreeNode.createTree(node.getChild()) ;
        String[] split = node.toString().split(" -> ");
        this.sourceDatabase = split[0];
        this.targetDatabase = split[1];
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.sourceDatabase + " -> " + this.targetDatabase;
    }

    /**
     * @return
     */
    @Override
    public List<String> listIncludedTablesRecursive() {
        return this.child.listIncludedTablesRecursive();
    }

    /**
     * @param tableList List of table names searched
     * @return
     */
    @Override
    public ETreeNode findLowestNodeContainingTables(List<String> tableList) {
        if(!new HashSet<>(this.listIncludedTablesRecursive()).containsAll(tableList)){
            return null;
        } else {
            return this.child.findLowestNodeContainingTables(tableList);
        }
    }

    /**
     * @return
     */
    @Override
    public Set<EDotNotation> listDistinctColumnsRecursive() {
        return child.listDistinctColumnsRecursive();
    }

    /**
     * @param children zero or more
     */
    @Override
    public void addChildren(ETreeNode... children) {
        if(children.length == 1){
            this.child = children[0];
            this.child.setParent(this);
        } else {
            throw new UnsupportedOperationException("A Transfer node can only have 1 child");
        }
    }

    /**
     * @param prefix
     */
    @Override
    public void print(String prefix) {
        if(this.getParent() instanceof EJoin){
            System.out.print(prefix + "├── Transfer : " + this + "\n");
        } else {
            System.out.print(prefix + "└── Transfer : " + this + "\n");
        }
        this.child.print(prefix + "    ");
    }

    /**
     * @param columnNamingMap
     */
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {
        this.child.renameColumnsRecursive(columnNamingMap);
    }
}
