package fr.irit.module1.queries;

import fr.irit.algebraictree.*;
import fr.irit.module1.antlr4.MongoQueryParser;
import fr.irit.module1.antlr4.SqlQueryParser;

import java.util.ArrayList;
import java.util.List;

public class MongoQuery extends Query<MongoQueryParser.QueryContext> {

    public MongoQuery(MongoQueryParser.QueryContext mongoParsedQuery) {
        setParsedQuery(mongoParsedQuery);
    }
    @Override
    public List<String> getTablesNames() {
        List<String> tableNames = new ArrayList<String>();
        tableNames.add(getParsedQuery().collection.getText());
        if(this.hasCondition()){
            for (MongoQueryParser.LookupStageContext lookup : getParsedQuery().lookupStage()){
                tableNames.add(lookup.from().collection.getText());
            }
        }
        return tableNames;
    }

    @Override
    public List<Selection> createSelectionNodes() {
        List<Selection> selectionList = new ArrayList<Selection>();
        // Search OR conditions
        // We assume that a composedPredicates is only an $or
        List<MongoQueryParser.ComposedPredicateContext> composedPredicates = getParsedQuery().matchStage().composedPredicate();
        if(!composedPredicates.isEmpty()) {
            for (MongoQueryParser.ComposedPredicateContext composedPredicate : composedPredicates) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                for (MongoQueryParser.PredicateContext predicate : composedPredicate.predicate()) {
                    //TODO Actually getParsedQuery().collection works because we not handle JOIN / lookup
                    DotNotation d = new DotNotation(getParsedQuery().collection.getText(), predicate.field().getText());
                    predicateList.add(new Predicate(d, this.comparisonOperatorToString(predicate.comparisonOperator()), predicate.value().raw.getText()));
                }
                Selection s = new Selection(predicateList);
                selectionList.add(s);
            }
        }
        List<MongoQueryParser.PredicateContext> predicates = getParsedQuery().matchStage().predicate();
        // Search AND conditions
        if(!predicates.isEmpty()){
            for(MongoQueryParser.PredicateContext predicate : predicates){
                //TODO Actually getParsedQuery().collection works because we not handle JOIN / lookup
                DotNotation d = new DotNotation(getParsedQuery().collection.getText(), predicate.field().getText());
                Predicate p = new Predicate(d, this.comparisonOperatorToString(predicate.comparisonOperator()), predicate.value().raw.getText());
                Selection s = new Selection(p);
                selectionList.add(s);
            }
        }
        return selectionList;
    }

    @Override
    public List<Join> createJoinNodes() {
        List<Join> joinList = new ArrayList<Join>();
        return joinList;
    }

    @Override
    public Projection createProjectionNode() {
        List<DotNotation> attributesList = new ArrayList<DotNotation>();
        // Handle sql wildcard (*) case
        if(getParsedQuery().projectStage() == null) {
            return new Projection();
        }
        for (MongoQueryParser.FieldContext field : getParsedQuery().projectStage().fieldsProjection().field()){
            //TODO Actually getParsedQuery().collection works because we not handle JOIN / lookup
            attributesList.add(new DotNotation(getParsedQuery().collection.getText(),field.getText()));
        }
        return new Projection(attributesList);
    }

    @Override
    public boolean hasCondition() {
        return getParsedQuery().matchStage() != null && getParsedQuery().lookupStage() != null;
    }

    private String comparisonOperatorToString(MongoQueryParser.ComparisonOperatorContext comparisonOperator){
        if(comparisonOperator == null){
            return "=";
        }
        switch(comparisonOperator.getText()){
            case "$eq":
                return "=";
            case "$ne":
                return "!=";
            case "$gte":
                return ">=";
            case "$gt":
                return ">";
            case "$lte":
                return "<=";
            case "$lt":
                return "<";
            default:
                throw new UnsupportedOperationException("Bad comparison operator");
        }
    }
}
