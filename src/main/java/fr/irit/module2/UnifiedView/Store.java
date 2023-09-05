package fr.irit.module2.UnifiedView;

import fr.irit.algebraictree.DotNotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Store {
    public String name;
    public String type;
    public List<Column> columns;
    public String toString(){
        return name + "-" + type + " " + columns.toString();
    }
    public Store(String name, String type, List<Column> columns){
        this.name = name;
        this.columns = columns;
        this.type = type;
    }

    public DotNotation getStoreKey(){
        Column columnKey = columns.stream().filter(column -> column.isKey).findFirst().get();
        if(columnKey != null){
            return columnKey.columnUVtoDotNotation();
        } else return null;
    }
    public List<Column> getMatchingColumns(Set<DotNotation> matchingList){
        List<Column> filteredColumns = new ArrayList<>();
        for(DotNotation columnName : matchingList){
            for(Column column : columns){
                if(column.columnUV.equals(columnName.toString())){
                    filteredColumns.add(column);
                }
            }
        }
        return filteredColumns;
    }
}

