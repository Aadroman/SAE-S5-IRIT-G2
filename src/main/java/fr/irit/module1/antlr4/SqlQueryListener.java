package fr.irit.module1.antlr4;// Generated from SqlQuery.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SqlQueryParser}.
 */
public interface SqlQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(SqlQueryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(SqlQueryParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#selectClause}.
	 * @param ctx the parse tree
	 */
	void enterSelectClause(SqlQueryParser.SelectClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#selectClause}.
	 * @param ctx the parse tree
	 */
	void exitSelectClause(SqlQueryParser.SelectClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#selectItem}.
	 * @param ctx the parse tree
	 */
	void enterSelectItem(SqlQueryParser.SelectItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#selectItem}.
	 * @param ctx the parse tree
	 */
	void exitSelectItem(SqlQueryParser.SelectItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(SqlQueryParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(SqlQueryParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#fromItem}.
	 * @param ctx the parse tree
	 */
	void enterFromItem(SqlQueryParser.FromItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#fromItem}.
	 * @param ctx the parse tree
	 */
	void exitFromItem(SqlQueryParser.FromItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(SqlQueryParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(SqlQueryParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#subCondition}.
	 * @param ctx the parse tree
	 */
	void enterSubCondition(SqlQueryParser.SubConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#subCondition}.
	 * @param ctx the parse tree
	 */
	void exitSubCondition(SqlQueryParser.SubConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(SqlQueryParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(SqlQueryParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#join}.
	 * @param ctx the parse tree
	 */
	void enterJoin(SqlQueryParser.JoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#join}.
	 * @param ctx the parse tree
	 */
	void exitJoin(SqlQueryParser.JoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(SqlQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(SqlQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlQueryParser#dotNotation}.
	 * @param ctx the parse tree
	 */
	void enterDotNotation(SqlQueryParser.DotNotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlQueryParser#dotNotation}.
	 * @param ctx the parse tree
	 */
	void exitDotNotation(SqlQueryParser.DotNotationContext ctx);
}