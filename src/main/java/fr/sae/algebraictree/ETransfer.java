package fr.sae.algebraictree;

import fr.irit.algebraictree.Transfer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ETransfer extends ETreeNode {
    private ETreeNode child;
    private Transfer correspondingTransfer;
    private String sourceDatabase;
    private String targetDatabase;
    public ETransfer(String sourceDatabase, String targetDatabase){
        this.sourceDatabase = sourceDatabase;
        this.targetDatabase = targetDatabase;
    }

    public ETransfer(Transfer node) {
        this.correspondingTransfer = node ;
        this.child = ETreeNode.createTree(node.getChild()) ;
    }

    @Override
    public String toString() {
        return this.sourceDatabase + " -> " + this.targetDatabase;
    }
    @Override
    public List<String> listIncludedTablesRecursive() {
        return this.child.listIncludedTablesRecursive();
    }
    @Override
    public ETreeNode findLowestNodeContainingTables(List<String> tableList) {
        if(!this.listIncludedTablesRecursive().containsAll(tableList)){
            return null;
        } else {
            return this.child.findLowestNodeContainingTables(tableList);
        }
    }
    @Override
    public Set<EDotNotation> listDistinctColumnsRecursive() {
        return child.listDistinctColumnsRecursive();
    }
    @Override
    public void addChildren(ETreeNode... children) {
        if(children.length == 1){
            this.child = children[0];
            this.child.setParent(this);
        } else {
            throw new UnsupportedOperationException("A Transfer node can only have 1 child");
        }
    }
    @Override
    public void print(String prefix) {
        if(this.getParent() instanceof EJoin){
            System.out.print(prefix + "├── Transfer : " +this.toString() + "\n");
        } else {
            System.out.print(prefix + "└── Transfer : " +this.toString() + "\n");
        }
        this.child.print(prefix + "    ");
    }
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {
        this.child.renameColumnsRecursive(columnNamingMap);
    }
}
