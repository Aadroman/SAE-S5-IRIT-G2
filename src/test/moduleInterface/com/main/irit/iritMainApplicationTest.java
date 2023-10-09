package com.main.irit;


import fr.irit.algebraictree.Join;
import fr.irit.algebraictree.Projection;
import fr.irit.algebraictree.Selection;
import fr.irit.algebraictree.Table;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class iritMainApplicationTest {

    @DisplayName("Test query with '*' PROJECTION and OR SELECTION")
    @Test
    public void Test_BuildAlgebraicTree_With_WildcardProjection_And_OrSelection() {
        String query = "SELECT * FROM Orders WHERE (Orders.total_price > 100 OR Orders.total_price < 201)";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");

        assertTrue(rootNode instanceof Projection);
        var level2 = ((Projection) rootNode).getChild();
        assertTrue(level2 instanceof Selection);
        var level3 = ((Selection) level2).getChild();

        assertEquals("*", rootNode.toString(), "PROJECTION");
        assertEquals("(Orders.total_price > 100 OR Orders.total_price < 201)", level2.toString(), "SELECTION");
        assertEquals("Orders", level3.toString());
    }

    @DisplayName("Test query without SELECTION")
    @Test
    public void Test_BuildAlgebraicTree_Without_Selection() {
        String query = "SELECT Products.product_id, Products.brand FROM Products";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");

        assertTrue(rootNode instanceof Projection);
        var level2 = ((Projection) rootNode).getChild();
        assertTrue(level2 instanceof Table);

        assertEquals("Products.product_id, Products.brand", rootNode.toString(), "PROJECTION");
        assertEquals("Products", level2.toString(), "TABLE");
    }

    @DisplayName("Test query without JOIN")
    @Test
    public void Test_BuildAlgebraicTree_Without_Join() {
        String query = "SELECT Orders.order_id FROM Orders WHERE Orders.total_price > 100";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");

        assertTrue(rootNode instanceof Projection);
        var level2 = ((Projection) rootNode).getChild();
        assertTrue(level2 instanceof Selection);
        var level3 = ((Selection) level2).getChild();
        assertTrue(level3 instanceof Table);

        assertEquals("Orders.order_id", rootNode.toString(), "PROJECTION");
        assertEquals("Orders.total_price > 100", level2.toString(), "SELECTION");
        assertEquals("Orders", level3.toString(), "TABLE");
    }

    @DisplayName("Test query with JOIN")
    @Test
    public void Test_BuildAlgebraicTree_With_Join() {
        String query = "SELECT * FROM Orders, Order_line WHERE Orders.order_id=Order_line.order_id";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");

        assertTrue(rootNode instanceof Projection);
        var level2 = ((Projection) rootNode).getChild();
        assertTrue(level2 instanceof Join);
        var level2_1 = ((Join) level2).getLeftChild();
        assertTrue(level2_1 instanceof Table);
        var level2_2 = ((Join) level2).getRightChild();
        assertTrue(level2_2 instanceof Table);

        assertEquals("*", rootNode.toString(), "PROJECTION");
        assertEquals("Orders.order_id = Order_line.order_id", level2.toString(), "JOIN");
        assertEquals("Orders", level2_1.toString(), "TABLE");
        assertEquals("Order_line", level2_2.toString(), "TABLE");
    }

    @DisplayName("Test query with JOIN and SELECTION")
    @Test
    public void Test_BuildAlgebraicTree_With_Join_And_Selection() {
        String query = "SELECT * FROM Reviews, Products WHERE Reviews.product_id=Products.product_id and Reviews.rating=5";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");

        assertTrue(rootNode instanceof Projection);
        var level2 = ((Projection) rootNode).getChild();
        assertTrue(level2 instanceof Join);
        var level2_1 = ((Join) level2).getLeftChild();
        assertTrue(level2_1 instanceof Selection);
        var level2_1_1 = ((Selection) level2_1).getChild();
        assertTrue(level2_1_1 instanceof Table);
        var level2_2 = ((Join) level2).getRightChild();
        assertTrue(level2_2 instanceof Table);

        assertEquals("*", rootNode.toString(), "PROJECTION");
        assertEquals("Reviews.product_id = Products.product_id", level2.toString(), "JOIN");
        assertEquals("Reviews.rating = 5", level2_1.toString(), "SELECTION");
        assertEquals("Reviews", level2_1_1.toString(), "TABLE");
        assertEquals("Products", level2_2.toString(), "TABLE");
    }

    @DisplayName("Test query with several JOIN and several SELECTION")
    @Test
    public void Test_BuildAlgebraicTree_With_SeveralJoins_And_SeveralSelection() {
        String query = "SELECT Orders.order_id FROM Reviews, Products, Orders " +
                "WHERE Reviews.product_id = Products.product_id " +
                "AND Orders.product_id = Products.product_id " +
                "AND Reviews.Rating >= 4 " +
                "AND (Products.Brand = nike OR Orders.price >= 100)";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");

        var join1 = rootNode.findLowestNodeContainingTables(new ArrayList<String>(List.of("Reviews", "Products", "Orders")));
        assertTrue(join1 instanceof Join);
        var join2 = rootNode.findLowestNodeContainingTables(new ArrayList<String>(List.of("Products", "Orders")));
        assertTrue(join2 instanceof Join);

        // Assert joins are at the good place
        assertEquals("Reviews.product_id = Products.product_id", join1.toString());
        assertEquals("Orders.product_id = Products.product_id", join2.toString());
        // Assert selections are at the good place
        var lowestNodeReviews = rootNode.findLowestNodeContainingTables(new ArrayList<>(List.of("Reviews")));
        assertEquals("Reviews.Rating >= 4", lowestNodeReviews.getParent().toString());
        var lowestNodeProductsOrders = rootNode.findLowestNodeContainingTables(new ArrayList<>(List.of("Products", "Orders")));
        assertEquals("(Products.Brand = nike OR Orders.price >= 100)", lowestNodeProductsOrders.getParent().toString());
    }

    @DisplayName("Test query where JOIN have two child Joins")
    @Test
    public void Test_BuildAlgebraicTree_Where_JoinHaveTwoChildJoins() {
        String query = "SELECT Customers.customer_id, Orders.order_id, Orders.total_price, Products.brand " +
                "FROM Reviews, Orders, Products, Customers " +
                "WHERE Reviews.order_id = Orders.order_id " +
                "AND Reviews.product_id = Products.product_id " +
                "AND Customers.customer_id = Orders.customer_id ";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");

        var join1 = rootNode.findLowestNodeContainingTables(new ArrayList<String>(List.of("Reviews", "Products", "Orders", "Customers")));
        assertTrue(join1 instanceof Join);
        var join2 = rootNode.findLowestNodeContainingTables(new ArrayList<String>(List.of("Customers", "Orders")));
        assertTrue(join2 instanceof Join);
        var join3 = rootNode.findLowestNodeContainingTables(new ArrayList<String>(List.of("Products", "Reviews")));
        assertTrue(join3 instanceof Join);

        // Assert joins are at the good place
        assertEquals("Reviews.order_id = Orders.order_id", join1.toString());
        assertEquals("Customers.customer_id = Orders.customer_id", join2.toString());
        assertEquals("Reviews.product_id = Products.product_id", join3.toString());
        // Assert join 2 et 3 are the children of join 1
        assertTrue(((Join) join1).getLeftChild() == join3);
        assertTrue(((Join) join1).getRightChild() == join2);
    }

    @DisplayName("Test basic JOIN query with Alias")
    @Disabled("Alias not implemented")
    public void Test_BuildAlgebraicTree_With_Alias() {
        String query = "SELECT product_id, title, rating FROM Product P, Reviews R WHERE P.product_id=R.product_id";
        Query queryParsed = QueryParserUtils.parse(query);
        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
    }

    @Test
    public void testMain() {
        //TODO Better tests
    }
}
