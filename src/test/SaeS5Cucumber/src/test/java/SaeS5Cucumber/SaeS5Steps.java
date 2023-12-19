package SaeS5Cucumber;

import io.cucumber.java.en.*;
import fr.irit.algebraictree.Projection;
import fr.irit.algebraictree.Selection;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import javafx.scene.Node;
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
public class SaeS5Cucumber {

    @Given("User who enters a query ")
    public void GenerateTree() {
    }

    @When("nodes are created when the tree is created")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("the tree doesn't have nodes")
    public void theScenarioPasses() {
    }

}

