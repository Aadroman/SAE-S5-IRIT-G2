package fr.sae.algebraictree;

import fr.irit.algebraictree.DotNotation;
import fr.irit.algebraictree.Projection;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class EProjection extends ETreeNode {
    //region ATTRIBUTES
    private ETreeNode child;
    private Projection correspondingProjection ;
    /**
     * Attributes is null in case of wildcard (*)
     */
    private List<EDotNotation> attributes = new ArrayList<>();
    //endregion
    //region CONSTRUCTORS
    public EProjection() {}
    public EProjection(List<EDotNotation> attributes) {this.attributes = attributes;}

    public EProjection (Projection p) {
        this.correspondingProjection = p;
        for(DotNotation dt : p.getAttributes()){
            this.attributes.add(new EDotNotation(dt));
        }
        this.child = ETreeNode.createTree(p.getChild()) ;
    }

    //endregion
    //region GETTERS & SETTERS
    @Override
    public ETreeNode[] getChild() {
        ETreeNode[] childs = new ETreeNode[1];
        childs[0] = child;
        return childs;
    }
    public List<EDotNotation> getAttributes() { return attributes; }
    //endregion
    //region METHODS
    @Override
    public void addChildren(ETreeNode... children) {
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
    public ETreeNode findLowestNodeContainingTables(List<String> tableList) {
        if(!this.listIncludedTablesRecursive().containsAll(tableList)){
            return null;
        } else {
            return this.child.findLowestNodeContainingTables(tableList);
        }
    }

    @Override
    public Set<EDotNotation> listDistinctColumnsRecursive() {
        Set<EDotNotation> includedColumns = new HashSet<EDotNotation>();
        for(EDotNotation attribute : ListUtils.emptyIfNull(attributes)){
            includedColumns.add(attribute);
        }
        includedColumns.addAll(child.listDistinctColumnsRecursive());
        return includedColumns;
    }
    //endregion
}
