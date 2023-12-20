package SaeS5Cucumber;

import io.cucumber.java.en.*;
import fr.irit.algebraictree.Projection;
import fr.irit.algebraictree.Selection;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import javafx.scene.Node;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
public class SaeS5Cucumber extends TestFxBase{


    @Given("An empty query")
    public void emptyQuery(FxRobot robot){
        String query = "";
        robot.clickOn("#requestTextField");
        robot.write(query);
        robot.clickOn("#boutonValider");
        
    }

    @When("nodes are created when the tree is created")
    public void nodesAreCreated() {
       
    }

    @Then("the tree doesn't have nodes")
    public void treeHasNoNodes() {
    }

}

