package com.main.irit;

import fr.irit.algebraictree.Projection;
import fr.irit.algebraictree.Selection;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import fr.sae.algebraictree.ETreeNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import javafx.scene.Node;
import org.junit.Assert;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@CucumberOptions(
        glue = "com.main.irit",
        features = "src/test/resources/feature"
)
public class interfaceSteps extends TestFxBase {
    String query;
    private ETreeNode[] computedTrees;

    @Given("an empty query")
    public void emptyQuery(){
        //n'arrive pas à reconnaitre l'intérieur des fonctions => sont non définis
        query = "";
    }
    @Given("a written query")
    public void fullQuery(){
        query = "SELECT * FROM Orders WHERE (Orders.total_price > 100 OR Orders.total_price < 201)";
    }
    @When("writing down query")
    public void writingQuery(){
        clickOn("#requestTextField");
        write(query);
        clickOn("#submitButton");
    }
    @Then("alert dialog should show up because of an exception")
    public void noTreeAndTriggerAlert() {
        FxAssert.verifyThat("#requestError",NodeMatchers.isEnabled());
        clickOn("#requestError");
        Assert.assertThrows(UnsupportedOperationException.class,()->QueryParserUtils.parse(query));
    }
    @Then("numerous elements of the application should work and the query can give a tree with the algebraic tree creation program")
    public void appWorks(){
        FxAssert.verifyThat("#globalPane", Node::isVisible);
        FxAssert.verifyThat("#multiPane", Node::isVisible);
        FxAssert.verifyThat("#transferPane", Node::isVisible);
        FxAssert.verifyThat("#subRequestButton", Node::isVisible);
        FxAssert.verifyThat("#tvNode", Node::isVisible);

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

}
