package fr.sae.algebraictree;

import fr.irit.algebraictree.Selection;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class ESelection extends ETreeNode {

    //region ATTRIBUTES
    private Selection correspondingSelection ;
    private ETreeNode child;
    private List<EPredicate> predicates;
    //endregion
    //region CONSTRUCTOR
    public ESelection(List<EPredicate> predicates){
        this.predicates = predicates;
    }
    public ESelection(EPredicate predicate){
        this.predicates = new ArrayList<EPredicate>();
        this.predicates.add(predicate);
    }

    public ESelection (Selection s) {
        this.correspondingSelection = s;
        this.child = ETreeNode.createTree(s.getChild()) ;
    }

    //endregion
    //region GETTERS & SETTERS
    public ETreeNode getChild() { return child; }
    public List<EPredicate> getPredicates() {
        return this.predicates;
    }
    //endregion
    //region METHODS
    @Override
    public void addChildren(ETreeNode... children) {
        if(children.length == 1){
            this.child = children[0];
            this.child.setParent(this);
        } else {
            throw new  UnsupportedOperationException("A Selection node can only have 1 child");
        }
    }

    @Override
    public void print(String prefix) {
        if(this.getParent() instanceof EJoin){
            System.out.print(prefix + "├── σ " +this.toString() + "\n");
        } else {
            System.out.print(prefix + "└── σ " +this.toString() + "\n");
        }
        this.child.print(prefix + "    ");
    }

    @Override
    public void renameColumnsRecursive(Map<DotNotation, DotNotation> columnNamingMap) {
        for(EPredicate p : this.predicates){
            p.attribute = columnNamingMap.get(p.attribute);
        }
        this.child.renameColumnsRecursive(columnNamingMap);
    }

    @Override
    public String toString() {
        if(this.predicates.size() > 1) {
            String result = "(";
            for(int i=0; i < this.predicates.size(); i++){
                result += this.predicates.get(i);
                if(i < this.predicates.size() - 1) {
                    result += " OR ";
                }
            }
            return result + ")";
        } else {
            return predicates.get(0).toString();
        }
    }

    @Override
    public List<String> listIncludedTablesRecursive() {
        return this.child.listIncludedTablesRecursive();
    }

    /**
     * A Selection node cannot be the lowest node which contain 1 or n tables.
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
    public Set<DotNotation> listDistinctColumnsRecursive() {
        Set<DotNotation> includedColumns = new HashSet<DotNotation>();
        for(EPredicate p : ListUtils.emptyIfNull(this.predicates)){
            includedColumns.add(p.attribute);
        }
        includedColumns.addAll(this.child.listDistinctColumnsRecursive());
        return includedColumns;
    }
    //endregion
}
