package fr.sae.algebraictree;

import fr.irit.algebraictree.Transformation;
import fr.irit.module3.TransformationTransferAlgebraicTree;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ETransformation extends ETreeNode {
    private ETreeNode child;
    private String sourceDatabaseType;
    private String targetDatabaseType;

    private Transformation correspondingTransformation ;
    public ETransformation(String sourceDatabase, String targetDatabase){
        this.sourceDatabaseType = sourceDatabase;
        this.targetDatabaseType = targetDatabase;
    }

    public ETransformation(Transformation node) {
        this.correspondingTransformation = node;
        String[] split = this.correspondingTransformation.toString().split(" -> ");
        this.sourceDatabaseType = split[0];
        this.targetDatabaseType = split[1];


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
            System.out.print(prefix + "├── Transformation : " +this.toString() + "\n");
        } else {
            System.out.print(prefix + "└── Transformation : " +this.toString() + "\n");
        }
        this.child.print(prefix + "    ");
    }
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {
        this.child.renameColumnsRecursive(columnNamingMap);
    }
}