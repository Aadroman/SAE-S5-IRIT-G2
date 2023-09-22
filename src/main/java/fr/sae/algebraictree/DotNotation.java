package fr.sae.algebraictree;

import java.util.Objects;

/**
 * DotNotation decompose a basic database string "table.column"
 * in an object with 2 attributes : table & column
 */
public class DotNotation {
    public String table;
    public String column;
    public DotNotation(String table, String column) {
        this.table = table;
        this.column = column;
    }
    public String toString() {
        return table + '.' + column;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DotNotation that = (DotNotation) o;
        return Objects.equals(table, that.table) && Objects.equals(column, that.column);
    }
    @Override
    public int hashCode() {
        return Objects.hash(table, column);
    }
}
