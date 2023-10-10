package fr.sae.algebraictree;

import fr.irit.algebraictree.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ETransformation extends ETreeNode {
    private ETreeNode child;
    private String sourceDatabaseType;
    private String targetDatabaseType;
    private List<String> list = new ArrayList<>();

    private Transformation correspondingTransformation;

    public ETransformation(String sourceDatabase, String targetDatabase) {
        this.sourceDatabaseType = sourceDatabase;
        this.targetDatabaseType = targetDatabase;
    }

    /**
     * @param node
     */
    public ETransformation(Transformation node) {
        this.correspondingTransformation = node;
        String[] split = this.correspondingTransformation.toString().split(" -> ");
        this.sourceDatabaseType = split[0];
        this.targetDatabaseType = split[1];
        list = node.listIncludedTablesRecursive();
        this.child = new ETreeNode().createTree(node.findLowestNodeContainingTables(list));
    }

    @Override
    public ETreeNode[] getChild() {
        ETreeNode[] childs = new ETreeNode[1];
        childs[0] = child;
        return childs;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.sourceDatabaseType + " -> " + this.targetDatabaseType;
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
        if (!this.listIncludedTablesRecursive().containsAll(tableList)) {
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
        if (children.length == 1) {
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
        if (this.getParent() instanceof EJoin) {
            System.out.print(prefix + "├── Transformation : " + this.toString() + "\n");
        } else {
            System.out.print(prefix + "└── Transformation : " + this.toString() + "\n");
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