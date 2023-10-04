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

    private final DotNotation dot;

    /**
     * @param dotNotation
     */
    public EDotNotation(DotNotation dotNotation) {
        this.dot = dotNotation;
        this.table = dotNotation.table;
        this.column = dotNotation.column;
    }

    /**
     * @return
     */
    public DotNotation getDot() {
        return dot;
    }

    /**
     * @return
     */
    public String toString() {
        return table + '.' + column;
    }

    /**
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EDotNotation that = (EDotNotation) object;
        return Objects.equals(table, that.table) && Objects.equals(column, that.column);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(table, column);
    }
}
