package fr.irit.module2.UnifiedView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnifiedView {
    public String type;
    public List<UVTable> uvTables;

    public String toString(){
        return (type + " : " + uvTables.toString());
    }
    public UnifiedView(String type, List<UVTable> uvTables){
        this.type = type;
        this.uvTables = uvTables;
    }
    public UVTable getUVTable(String tableName){
        for(UVTable uvtable : uvTables){
            if (uvtable.label.equals(tableName)){
                return uvtable;
            }
        }
        return null;
    }
    public void print(boolean physicalImplementation){
        if(physicalImplementation){
            for(UVTable table : uvTables){
                System.out.print("\n"+ table.label + " : ");
                for (Store store : table.stores){
                    System.out.print("\n    " + store.name + " (" + store.type + ") : ");
                    System.out.print(store.columns.toString());
                }
            }
        } else {
            for(UVTable table : uvTables){
                Set columnList = new HashSet<String>();
                for (Store store : table.stores){
                    for (Column column : store.columns){
                        columnList.add(column.columnUV);
                    }
                }
                System.out.print("\n"+ table.label + " : " + columnList);
            }
        }
    }

}

