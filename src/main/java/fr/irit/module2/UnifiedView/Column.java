package fr.irit.module2.UnifiedView;

import fr.irit.algebraictree.DotNotation;

public class Column {
    public String columnUV;
    public String columnLinked;
    public boolean isKey;
    public String toString(){
        return columnUV + " <-> " + columnLinked;
    }
    public DotNotation columnUVtoDotNotation(){
        String[] split = columnUV.split("\\.", 2);
        return new DotNotation(split[0], split[1]);
    }
    public DotNotation columnLinkedtoDotNotation(){
        String[] split = columnLinked.split("\\.", 2);
        return new DotNotation(split[0], split[1]);
    }
}
