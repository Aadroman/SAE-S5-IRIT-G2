package fr.sae.algebraictree;

import fr.irit.algebraictree.DotNotation;
import fr.irit.algebraictree.Projection;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class EProjection extends ETreeNode {

    //region ATTRIBUTES
    private ETreeNode child;

    /**
     * Attributes is null in case of wildcard (*)
     */
    private List<EDotNotation> attributes = new ArrayList<>();
    //endregion

    //region CONSTRUCTORS
    /**
     * @param attributes
     */
    public EProjection(List<EDotNotation> attributes) {this.attributes = attributes;}

    /**
     * @param projection
     */
    public EProjection (Projection projection) {
        for(DotNotation dt : projection.getAttributes()){
            this.attributes.add(new EDotNotation(dt));
        }
        this.child = ETreeNode.createTree(projection.getChild()) ;
    }
    //endregion

    /**
     * @return child
     */
    //region GETTERS & SETTERS
    public ETreeNode getChild() {
        return child;
    }

    /**
     * @return attribute
     */
    public List<EDotNotation> getAttributes() { return attributes; }
    //endregion

    //region METHODS
    /**
     * @param children zero or more
     */
    @Override
    public void addChildren(ETreeNode... children) {
        if(children.length == 1){
            this.child = children[0];
            this.child.setParent(this);
        } else {
            throw new UnsupportedOperationException("A Projection node can only have 1 child");
        }
    }

    /**
     * @param prefix
     */
    @Override
    public void print(String prefix) {
        System.out.print("π " + this + "\n");
        this.child.print(prefix);
    }

    /**
     * @param columnNamingMap
     */
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {
        if(this.attributes != null){
            List<EDotNotation> attributeListRenamed = new ArrayList<>();
            for(EDotNotation attribute : this.attributes){
                attributeListRenamed.add(columnNamingMap.get(attribute));
            }
            this.attributes = attributeListRenamed;
        }
        this.child.renameColumnsRecursive(columnNamingMap);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        if(attributes == null) {
            return "*";
        }
        return attributes.toString().replace("[", ""). replace("]", "");
    }

    /**
     * @return
     */
    @Override
    public List<String> listIncludedTablesRecursive() {
        return this.child.listIncludedTablesRecursive();
    }

    /**
     * A Projection node cannot be the lowest node which contain 1 or n tables.
     *
     * @param tableList List of table names searched
     * @return null or result of the same method on its child
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
        Set<EDotNotation> includedColumns = new HashSet<>();
        includedColumns.addAll(ListUtils.emptyIfNull(attributes));
        includedColumns.addAll(child.listDistinctColumnsRecursive());
        return includedColumns;
    }
    //endregion
}
