package fr.irit.module1.antlr4;// Generated from SqlQuery.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SqlQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SqlQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(SqlQueryParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#selectClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectClause(SqlQueryParser.SelectClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#selectItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectItem(SqlQueryParser.SelectItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#fromClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromClause(SqlQueryParser.FromClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#fromItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromItem(SqlQueryParser.FromItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(SqlQueryParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#subCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubCondition(SqlQueryParser.SubConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(SqlQueryParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#join}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin(SqlQueryParser.JoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(SqlQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlQueryParser#dotNotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotNotation(SqlQueryParser.DotNotationContext ctx);
}