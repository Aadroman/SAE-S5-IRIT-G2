package com.main.irit;


import fr.irit.algebraictree.Projection;
import fr.irit.algebraictree.Selection;
import fr.irit.module1.GlobalAlgebraicTree;
import fr.irit.module1.QueryParserUtils;
import fr.irit.module1.queries.Query;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class iritMainApplicationTest {

    /**
     * Méthode d'initialisation appelée avant chaque test
     *
     * @param stage La fenêtre (stage) injectée
     */
    @Start
    private void start(Stage stage) throws Exception {
        iritMainApplication app = iritMainApplication.getInstance();
        app.start(stage);
    }

    @DisplayName("Test create algrebric tree into the application")
    @Test
    public void Test_BuildAlgebraicTree_With_WildcardProjection_And_OrSelection(FxRobot robot) {
        String query = "SELECT * FROM Orders WHERE (Orders.total_price > 100 OR Orders.total_price < 201)";
        robot.clickOn("#requestTextField");
        robot.write(query);
        robot.clickOn("#checkButton");
        //trouver comment verif que pane est pas vide
//        Query queryParsed = QueryParserUtils.parse(query);
//        var rootNode = new GlobalAlgebraicTree(queryParsed).getRootNode();
//        rootNode.print("");
//
//        assertTrue(rootNode instanceof Projection);
//        var level2 = ((Projection) rootNode).getChild();
//        assertTrue(level2 instanceof Selection);
//        var level3 = ((Selection) level2).getChild();
//
//        assertEquals("*", rootNode.toString(), "PROJECTION");
//        assertEquals("(Orders.total_price > 100 OR Orders.total_price < 201)", level2.toString(), "SELECTION");
//        assertEquals("Orders", level3.toString());
    }



    @Test
    public void testMain() {
        //TODO Better tests
    }
}
