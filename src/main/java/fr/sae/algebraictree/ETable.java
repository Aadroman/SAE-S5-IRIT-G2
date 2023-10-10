package fr.sae.algebraictree;

import fr.irit.algebraictree.Table;
import fr.irit.module2.UnifiedView.Store;

import java.util.*;

public class ETable extends ETreeNode {
    //region ATTRIBUTES
    private String label;
    private Store store;
    //endregion
    //region CONSTRUCTOR
    /**
     * @param label
     */
    public ETable(String label){
        this.label = label;
    }
    //endregion
    //region GETTERS & SETTERS
    /**
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param store
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @return
     */
    @Override
    public Store getStore(){
        return store;
    }
    private Table correspondingTable ;

    public ETable ( Table t){
        this.correspondingTable = t ;
        this.label = t.getLabel();
        this.store = t.getStore();

    }

    //endregion
    //region METHODS
    /**
     * @return
     */
    @Override
    public String toString() {
        return this.label;
    }

    /**
     * @return
     */
    @Override
    public List<String> listIncludedTablesRecursive() {
        return new ArrayList<String>(List.of(this.label));
    }

    /**
     * A Table node can be the lowest node which contain tables searched in params if it's the only table searched
     * in params if it's the only table searched.
     * @param tableList List of table names searched
     * @return
     */
    @Override
    public ETreeNode findLowestNodeContainingTables(List<String> tableList) {
        return tableList.contains(this.label) && tableList.size() == 1 ? this : null;
    }

    /**
     * @return
     */
    @Override
    public Set<EDotNotation> listDistinctColumnsRecursive() {
        return new HashSet<>();
    }

    /**
     * @param children zero or more
     */
    @Override
    public void addChildren(ETreeNode... children) {
        throw new UnsupportedOperationException("addChildren method not supported in Table node");
    }

    /**
     * @param prefix
     */
    @Override
    public void print(String prefix) {
        if(this.getStore() != null){
            System.out.print(prefix + "└── " +this.toString() + " : " + this.getStore().toString() + "\n");
        } else {
            System.out.print(prefix + "└── " + this.toString() + "\n");
        }
    }

    /**
     * @param columnNamingMap
     */
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) {
        return;
    }
    //endregion
}
