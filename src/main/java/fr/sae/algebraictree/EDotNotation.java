package fr.sae.algebraictree;

import fr.irit.algebraictree.DotNotation;

import java.util.Objects;

/**
 * DotNotation decompose a basic database string "table.column"
 * in an object with 2 attributes : table & column
 */
public class EDotNotation {
    public String table;
    public String column;
    private DotNotation dot;
    public EDotNotation(DotNotation dotNotation) {
        this.dot = dotNotation;
        this.table = dotNotation.table;
        this.column = dotNotation.column;
    }

    public DotNotation getDot() {
        return dot;
    }

    public String toString() {
        return table + '.' + column;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EDotNotation that = (EDotNotation) o;
        return Objects.equals(table, that.table) && Objects.equals(column, that.column);
    }
    @Override
    public int hashCode() {
        return Objects.hash(table, column);
    }
}
