package fr.irit.module1.antlr4;// Generated from MongoQuery.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MongoQueryParser}.
 */
public interface MongoQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(MongoQueryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(MongoQueryParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#matchStage}.
	 * @param ctx the parse tree
	 */
	void enterMatchStage(MongoQueryParser.MatchStageContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#matchStage}.
	 * @param ctx the parse tree
	 */
	void exitMatchStage(MongoQueryParser.MatchStageContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#composedPredicate}.
	 * @param ctx the parse tree
	 */
	void enterComposedPredicate(MongoQueryParser.ComposedPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#composedPredicate}.
	 * @param ctx the parse tree
	 */
	void exitComposedPredicate(MongoQueryParser.ComposedPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(MongoQueryParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(MongoQueryParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(MongoQueryParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(MongoQueryParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#projectStage}.
	 * @param ctx the parse tree
	 */
	void enterProjectStage(MongoQueryParser.ProjectStageContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#projectStage}.
	 * @param ctx the parse tree
	 */
	void exitProjectStage(MongoQueryParser.ProjectStageContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#fieldsProjection}.
	 * @param ctx the parse tree
	 */
	void enterFieldsProjection(MongoQueryParser.FieldsProjectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#fieldsProjection}.
	 * @param ctx the parse tree
	 */
	void exitFieldsProjection(MongoQueryParser.FieldsProjectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#lookupStage}.
	 * @param ctx the parse tree
	 */
	void enterLookupStage(MongoQueryParser.LookupStageContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#lookupStage}.
	 * @param ctx the parse tree
	 */
	void exitLookupStage(MongoQueryParser.LookupStageContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#from}.
	 * @param ctx the parse tree
	 */
	void enterFrom(MongoQueryParser.FromContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#from}.
	 * @param ctx the parse tree
	 */
	void exitFrom(MongoQueryParser.FromContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#localField}.
	 * @param ctx the parse tree
	 */
	void enterLocalField(MongoQueryParser.LocalFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#localField}.
	 * @param ctx the parse tree
	 */
	void exitLocalField(MongoQueryParser.LocalFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#foreignField}.
	 * @param ctx the parse tree
	 */
	void enterForeignField(MongoQueryParser.ForeignFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#foreignField}.
	 * @param ctx the parse tree
	 */
	void exitForeignField(MongoQueryParser.ForeignFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#as}.
	 * @param ctx the parse tree
	 */
	void enterAs(MongoQueryParser.AsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#as}.
	 * @param ctx the parse tree
	 */
	void exitAs(MongoQueryParser.AsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(MongoQueryParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(MongoQueryParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(MongoQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(MongoQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MongoQueryParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(MongoQueryParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MongoQueryParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(MongoQueryParser.LogicalOperatorContext ctx);
}