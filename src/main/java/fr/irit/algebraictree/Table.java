package fr.irit.algebraictree;

import fr.irit.module2.UnifiedView.Store;

import java.util.*;

public class Table extends TreeNode{
    //region ATTRIBUTES
    private String label;
    private Store store;
    //endregion
    //region CONSTRUCTOR
    public Table(String label){
        this.label = label;
    }
    //endregion
    //region GETTERS & SETTERS
    public String getLabel() {
        return label;
    }

    public void setStore(Store store) {
        this.store = store;
    }
    public Store getStore(){
        return store;
    }

    //endregion
    //region METHODS
    @Override
    public String toString() {
        return this.label;
    }

    @Override
    public List<String> listIncludedTablesRecursive() {
        return new ArrayList<String>(List.of(this.label));
    }

    /**
     * A Table node can be the lowest node which contain tables searched
     * in params if it's the only table searched.
     */
    @Override
    public TreeNode findLowestNodeContainingTables(List<String> tableList) {
        return tableList.contains(this.label) && tableList.size() == 1 ? this : null;
    }
    @Override
    public Set<DotNotation> listDistinctColumnsRecursive() {
        return new HashSet<>();
    }

    @Override
    public void addChildren(TreeNode... children) {
        throw new UnsupportedOperationException("addChildren method not supported in Table node");
    }

    @Override
    public void print(String prefix) {
        if(this.getStore() != null){
            System.out.print(prefix + "└── " +this.toString() + " : " + this.getStore().toString() + "\n");
        } else {
            System.out.print(prefix + "└── " + this.toString() + "\n");
        }
    }

    @Override
    public void renameColumnsRecursive(Map<DotNotation, DotNotation> columnNamingMap) {
        return;
    }
    //endregion
}
