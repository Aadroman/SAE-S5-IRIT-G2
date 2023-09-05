package fr.irit.module2.UnifiedView;


import java.util.List;

public class UVTable {
    public String label;
    public List<Store> stores;

    public UVTable(String label, List<Store> stores){
        this.label = label;
        this.stores = stores;
    }
    public String toString(){
        return label + " : " + stores.toString();
    }
}
