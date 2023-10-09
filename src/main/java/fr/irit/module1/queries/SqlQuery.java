package fr.irit.module1.queries;

import fr.irit.algebraictree.*;
import fr.irit.module1.antlr4.SqlQueryParser;

import java.util.ArrayList;
import java.util.List;

/**
 * SqlQuery is a concrete class of Query for sql queries
 * It contain methods to create the algebraic tree
 */
public class SqlQuery extends Query<SqlQueryParser.QueryContext> {

    public SqlQuery(SqlQueryParser.QueryContext sqlParsedQuery) {
        setParsedQuery(sqlParsedQuery);
    }

    @Override
    public List<String> getTablesNames() {
        List<String> tableNames = new ArrayList<String>();
        for (SqlQueryParser.FromItemContext fromItem : getParsedQuery().fromClause().fromItem()) {
            tableNames.add(fromItem.IDENTIFIER().getText());
        }
        return tableNames;
    }

    @Override
    public List<Selection> createSelectionNodes() {
        List<Selection> selectionList = new ArrayList<Selection>();
        // Search OR conditions
        if (!getParsedQuery().condition().subCondition().isEmpty()) {
            for (fr.irit.module1.antlr4.SqlQueryParser.SubConditionContext subCondition : getParsedQuery().condition().subCondition()) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                for (fr.irit.module1.antlr4.SqlQueryParser.PredicateContext predicate : subCondition.predicate()) {
                    DotNotation d = new DotNotation(predicate.dotNotation().table.getText(), predicate.dotNotation().column.getText());
                    predicateList.add(new Predicate(d, predicate.comparisonOperator().getText(), predicate.value.getText()));
                }
                Selection s = new Selection(predicateList);
                selectionList.add(s);
            }
        }
        // Search AND conditions
        if (!getParsedQuery().condition().predicate().isEmpty()) {
            for (fr.irit.module1.antlr4.SqlQueryParser.PredicateContext predicate : getParsedQuery().condition().predicate()) {
                DotNotation d = new DotNotation(predicate.dotNotation().table.getText(), predicate.dotNotation().column.getText());
                Predicate p = new Predicate(d, predicate.comparisonOperator().getText(), predicate.value.getText());
                Selection s = new Selection(p);
                selectionList.add(s);
            }
        }
        return selectionList;
    }

    @Override
    public List<Join> createJoinNodes() {
        List<Join> joinList = new ArrayList<Join>();
        for (fr.irit.module1.antlr4.SqlQueryParser.JoinContext joinItem : getParsedQuery().condition().join()) {
            DotNotation leftCondition = new DotNotation(joinItem.leftCondition.table.getText(), joinItem.leftCondition.column.getText());
            DotNotation rightCondition = new DotNotation(joinItem.rightCondition.table.getText(), joinItem.rightCondition.column.getText());
            Join j = new Join(leftCondition, rightCondition);
            joinList.add(j);
        }
        return joinList;
    }

    @Override
    public Projection createProjectionNode() {
        List<DotNotation> attributesList = new ArrayList<DotNotation>();
        var itemsInSelect = getParsedQuery().selectClause().selectItem();
        // Handle sql wildcard (*) case
        if (itemsInSelect.isEmpty()) {
            return new Projection();
        }
        for (fr.irit.module1.antlr4.SqlQueryParser.SelectItemContext item : itemsInSelect) {
            attributesList.add(new DotNotation(item.dotNotation().table.getText(), item.dotNotation().column.getText()));
        }
        return new Projection(attributesList);
    }

    @Override
    public boolean hasCondition() {
        return getParsedQuery().condition() != null;
    }
}
