package fr.irit;

import fr.irit.algebraictree.*;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import io.cucumber.java.en.*;
import javafx.scene.Node;
import org.testfx.api.FxAssert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class globalAlgebraicTreeSteps {

    String query="";
    TreeNode rootNode;

    @Given("a query")
    public void a_query() {
        // Write code here that turns the phrase above into concrete actions
        query = "SELECT Orders.order_id FROM Reviews, Products, Orders " +
                "WHERE Reviews.product_id = Products.product_id " +
                "AND Orders.product_id = Products.product_id " +
                "AND Reviews.Rating >= 4 " +
                "AND (Products.Brand = nike OR Orders.price >= 100)";
    }
    @When("using the generator of algebraic tree program")
    public void using_the_generator_of_algebraic_tree_program() {
        // Write code here that turns the phrase above into concrete actions
        Query queryParsed = QueryParserUtils.parse(query);
        rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
        rootNode.print("");
    }
    @Then("getting tree structure from my query")
    public void getting_tree_structure_from_my_query() {
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

}
