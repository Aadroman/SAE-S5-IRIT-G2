package fr.irit.algebraictree;

import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class Projection extends TreeNode{
    //region ATTRIBUTES
    private TreeNode child;
    /**
     * Attributes is null in case of wildcard (*)
     */
    private List<DotNotation> attributes;
    //endregion
    //region CONSTRUCTORS
    public Projection() {}
    public Projection(List<DotNotation> attributes) {
        this.attributes = attributes;
    }
    //endregion
    //region GETTERS & SETTERS
    public TreeNode getChild() {
        return child;
    }
    public List<DotNotation> getAttributes() { return attributes; }
    //endregion
    //region METHODS
    @Override
    public void addChildren(TreeNode... children) {
        if(children.length == 1){
            this.child = children[0];
            this.child.setParent(this);
        } else {
            throw new UnsupportedOperationException("A Projection node can only have 1 child");
        }
    }

    @Override
    public void print(String prefix) {
        System.out.print("π " + this.toString() + "\n");
        this.child.print(prefix);
    }

    @Override
    public void renameColumnsRecursive(Map<DotNotation, DotNotation> columnNamingMap) {
        if(this.attributes != null){
            List<DotNotation> attributeListRenamed = new ArrayList<>();
            for(DotNotation attribute : this.attributes){
                attributeListRenamed.add(columnNamingMap.get(attribute));
            }
            this.attributes = attributeListRenamed;
        }
        this.child.renameColumnsRecursive(columnNamingMap);
    }

    @Override
    public String toString() {
        if(attributes == null) {
            return "*";
        }
        return attributes.toString().replace("[", ""). replace("]", "");
    }

    @Override
    public List<String> listIncludedTablesRecursive() {
        return this.child.listIncludedTablesRecursive();
    }

    /**
     * A Projection node cannot be the lowest node which contain 1 or n tables.
     * @return null or result of the same method on its child
     */
    @Override
    public TreeNode findLowestNodeContainingTables(List<String> tableList) {
        if(!this.listIncludedTablesRecursive().containsAll(tableList)){
            return null;
        } else {
            return this.child.findLowestNodeContainingTables(tableList);
        }
    }

    @Override
    public Set<DotNotation> listDistinctColumnsRecursive() {
        Set<DotNotation> includedColumns = new HashSet<DotNotation>();
        for(DotNotation attribute : ListUtils.emptyIfNull(attributes)){
            includedColumns.add(attribute);
        }
        includedColumns.addAll(child.listDistinctColumnsRecursive());
        return includedColumns;
    }
    //endregion
}
