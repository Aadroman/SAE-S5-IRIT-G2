package fr.irit.module1;

import fr.irit.module1.antlr4.MongoQueryLexer;
import fr.irit.module1.antlr4.MongoQueryParser;
import fr.irit.module1.antlr4.SqlQueryLexer;
import fr.irit.module1.antlr4.SqlQueryParser;
import fr.irit.module1.queries.MongoQuery;
import fr.irit.module1.queries.Query;
import fr.irit.module1.queries.SqlQuery;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Arrays;

/**
 * QueryParserUtils is a utility class whose purpose is to abstract query language by redirect the query given by the user
 * to the good parser depending on query language (sql or mongo).
 */
public final class QueryParserUtils {
    private static String[] sqlWordsList = new String[]{"SELECT", "select", "FROM", "from", "where", "WHERE"};
    private static String[] mongoWordsList = new String[]{"AGGREGATE", "aggregate", "$match", "$","lookup"};
    private static String query;
    private QueryParserUtils(){}

    /**
     * parse method parse query depending on the query language used and return type associated Query object
     * @param rawQuery Query in input written by the user
     * @return Query parsed object
     */
    public static Query parse(String rawQuery){
        query = rawQuery;
        if(Arrays.stream(sqlWordsList).anyMatch(rawQuery::contains)){
            SqlQueryLexer sqlLexer = new SqlQueryLexer(CharStreams.fromString(rawQuery));
            SqlQueryParser sqlParser = new SqlQueryParser(new CommonTokenStream(sqlLexer));
            sqlParser.setBuildParseTree(true);
            return new SqlQuery(sqlParser.query());
        } else if(Arrays.stream(mongoWordsList).anyMatch(rawQuery::contains)){
            MongoQueryLexer mongoLexer = new MongoQueryLexer(CharStreams.fromString(rawQuery));
            MongoQueryParser mongoParser = new MongoQueryParser(new CommonTokenStream(mongoLexer));
            mongoParser.setBuildParseTree(true);
            return new MongoQuery(mongoParser.query());
        }
        throw new UnsupportedOperationException("Query not detected as sql or mongo type");
    }
    public static QueryType getQueryType(){
        if(Arrays.stream(sqlWordsList).anyMatch(query::contains)) {
            return QueryType.RELATIONAL;
        } else if (Arrays.stream(mongoWordsList).anyMatch(query::contains)) {
            return QueryType.DOCUMENT;
        } else {
            throw new UnsupportedOperationException("Query not detected as sql or mongo type");
        }
    }

    public enum QueryType{
        DOCUMENT,
        RELATIONAL,
    }
}

