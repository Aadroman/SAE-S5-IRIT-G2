package fr.irit.module1.antlr4;// Generated from MongoQuery.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MongoQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MongoQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(MongoQueryParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#matchStage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchStage(MongoQueryParser.MatchStageContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#composedPredicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComposedPredicate(MongoQueryParser.ComposedPredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(MongoQueryParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(MongoQueryParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#projectStage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjectStage(MongoQueryParser.ProjectStageContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#fieldsProjection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldsProjection(MongoQueryParser.FieldsProjectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#lookupStage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLookupStage(MongoQueryParser.LookupStageContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrom(MongoQueryParser.FromContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#localField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalField(MongoQueryParser.LocalFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#foreignField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignField(MongoQueryParser.ForeignFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#as}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAs(MongoQueryParser.AsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(MongoQueryParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(MongoQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MongoQueryParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOperator(MongoQueryParser.LogicalOperatorContext ctx);
}