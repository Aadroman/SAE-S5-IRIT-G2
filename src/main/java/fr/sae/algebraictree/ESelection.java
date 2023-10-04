package fr.sae.algebraictree;

import fr.irit.algebraictree.Predicate;
import fr.irit.algebraictree.Selection;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class ESelection extends ETreeNode {

    private ETreeNode child;

    private final List<EPredicate> predicates;
    //endregion

    //region CONSTRUCTOR
    /**
     * @param predicates
     */
    public ESelection(List<EPredicate> predicates){
        this.predicates = predicates;
    }

    /**
     * @param predicate
     */
    public ESelection(EPredicate predicate){
        this.predicates = new ArrayList<>();
        this.predicates.add(predicate);
    }

    /**
     * @param selection
     */
    public ESelection (Selection selection) {
        //region ATTRIBUTES
        this.predicates = new ArrayList<>();
        for (EPredicate ep : getPredicates(selection)){
            this.predicates.add(new EPredicate(new EDotNotation(ep.getCorrespondingPredicate().attribute), ep.getCorrespondingPredicate().operator, ep.getCorrespondingPredicate().value));
        }
        this.child = ETreeNode.createTree(selection.getChild()) ;
    }

    /**
     * @param selection
     * @return predicates List
     */
    protected List<EPredicate> getPredicates(Selection selection){
        List<EPredicate> result = new ArrayList<>();
        List<Predicate> source = selection.getPredicates();
        for(Predicate predicate : source){
            result.add(new EPredicate(predicate));
        }
        return result;
    }

    //endregion

    //region GETTERS & SETTERS
    public ETreeNode getChild() { return child; }

    public List<EPredicate> getPredicates() {
        return this.predicates;
    }
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
            throw new  UnsupportedOperationException("A Selection node can only have 1 child");
        }
    }

    /**
     * @param prefix
     */
    @Override
    public void print(String prefix) {
        if(this.getParent() instanceof EJoin){
            System.out.print(prefix + "├── σ " + this + "\n");
        } else {
            System.out.print(prefix + "└── σ " + this + "\n");
        }
        this.child.print(prefix + "    ");
    }

    /**
     * @param columnNamingMap
     */
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {
        for(EPredicate p : this.predicates){
            p.attribute = columnNamingMap.get(p.attribute);
        }
        this.child.renameColumnsRecursive(columnNamingMap);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        if(this.predicates.size() > 1) {
            StringBuilder result = new StringBuilder("(");
            for(int i=0; i < this.predicates.size(); i++){
                result.append(this.predicates.get(i));
                if(i < this.predicates.size() - 1) {
                    result.append(" OR ");
                }
            }
            return result + ")";
        } else {
            return predicates.get(0).toString();
        }
    }

    /**
     * @return
     */
    @Override
    public List<String> listIncludedTablesRecursive() {
        return this.child.listIncludedTablesRecursive();
    }

    /**
     * A Selection node cannot be the lowest node which contain 1 or n tables.
     *
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
        Set<EDotNotation> includedColumns = new HashSet<>();
        for(EPredicate p : ListUtils.emptyIfNull(this.predicates)){
            includedColumns.add(p.attribute);
        }
        includedColumns.addAll(this.child.listDistinctColumnsRecursive());
        return includedColumns;
    }
    //endregion
}
