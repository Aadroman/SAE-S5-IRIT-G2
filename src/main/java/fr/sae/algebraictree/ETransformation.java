package fr.sae.algebraictree;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ETransformation extends ETreeNode {
    private ETreeNode child;
    private String sourceDatabaseType;
    private String targetDatabaseType;
    public ETransformation(String sourceDatabase, String targetDatabase){
        this.sourceDatabaseType = sourceDatabase;
        this.targetDatabaseType = targetDatabase;
    }

    @Override
    public String toString() {
        return this.sourceDatabaseType + " -> " + this.targetDatabaseType;
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
    public Set<DotNotation> listDistinctColumnsRecursive() {
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
            System.out.print(prefix + "├── Transformation : " +this.toString() + "\n");
        } else {
            System.out.print(prefix + "└── Transformation : " +this.toString() + "\n");
        }
        this.child.print(prefix + "    ");
    }
    @Override
    public void renameColumnsRecursive(Map<DotNotation, DotNotation> columnNamingMap) {
        this.child.renameColumnsRecursive(columnNamingMap);
    }
}