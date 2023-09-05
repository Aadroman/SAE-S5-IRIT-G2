package fr.irit.module1.queries;

import fr.irit.algebraictree.*;

import java.util.List;

/**
 * Query class abstract query language type of the query (sql, mongo, ...)
 * It contain methods to create the algebraic tree
 */
public abstract class Query<T> {
    private T parsedQuery;
    protected void setParsedQuery(T parsedQuery) {
        this.parsedQuery = parsedQuery;
    }
    protected T getParsedQuery() {
        return this.parsedQuery;
    }

    public abstract List<String> getTablesNames();
    public abstract List<Selection> createSelectionNodes();
    public abstract List<Join> createJoinNodes();
    public abstract Projection createProjectionNode();
    public abstract boolean hasCondition();
}
