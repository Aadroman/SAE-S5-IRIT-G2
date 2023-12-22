package fr.irit.module2_test;

import fr.irit.algebraictree.Join;
import fr.irit.algebraictree.TreeNode;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.irit.module2.MultistoreAlgebraicTree;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultistoreGlobalAlgebraicTreeTest {

    @Test
    public void Test_BuildMultistoreAlgebraicTree_With_WildcardProjection_And_OrSelection() {
        String query = "SELECT * FROM Orders WHERE (Orders.total_price > 100 OR Orders.total_price < 201)";
        Query queryParsed = QueryParserUtils.parse(query);
        GlobalAlgebraicTree globalAlgebraicTree = new GlobalAlgebraicTree(queryParsed);
        MultistoreAlgebraicTree multistoreAlgebraicTree = new MultistoreAlgebraicTree(globalAlgebraicTree);

        TreeNode t = multistoreAlgebraicTree.getMultistoreAlgebraicTree().findLowestNodeContainingTables(List.of("Orders"));
        multistoreAlgebraicTree.getMultistoreAlgebraicTree().print("");

        assertTrue(t instanceof Join);
        assertEquals("Orders.order_id = Orders.order_id", ((Join) t).toString());
    }
}