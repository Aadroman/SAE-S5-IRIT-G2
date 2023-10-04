package fr.sae.algebraictree;

import fr.irit.algebraictree.Table;
import fr.irit.module2.UnifiedView.Store;

import java.util.*;

public class ETable extends ETreeNode {

    //region ATTRIBUTES
    private final String label;

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
    public Store getStore(){
        return store;
    }

    /**
     * @param table
     */
    public ETable (Table table){
        this.label = table.getLabel();
        this.store = table.getStore();
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
        return new ArrayList<>(List.of(this.label));
    }

    /**
     * A Table node can be the lowest node which contain tables searched in params if it's the only table searched
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
            System.out.print(prefix + "└── " + this + " : " + this.getStore().toString() + "\n");
        } else {
            System.out.print(prefix + "└── " + this + "\n");
        }
    }

    /**
     * @param columnNamingMap
     */
    @Override
    public void renameColumnsRecursive(Map<EDotNotation, EDotNotation> columnNamingMap) { }
    //endregion
}
