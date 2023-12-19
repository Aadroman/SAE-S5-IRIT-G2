package SaeS5Cucumber;



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

@ExtendWith(ApplicationExtension.class)
public class GenerateTree {

     private iritMainApplication app;


    /**
     * Méthode d'initialisation appelée avant chaque test
     *
     * @param stage La fenêtre (stage) injectée
     */
    @Start
    private void start(Stage stage) throws Exception {

        iritMainApplication app = iritMainApplication.getInstance();
        app = iritMainApplication.getInstance();
        app.start(stage);
    }

    public void Test_BuildAlgebraicTree_With_WildcardProjection_And_OrSelection(FxRobot robot) {
        String query = "SELECT * FROM Orders WHERE (Orders.total_price > 100 OR Orders.total_price < 201)";
        robot.clickOn("#requestTextField");
        robot.write(query);
        robot.clickOn("#boutonValider");

        // Le but est de vérifier que le pane n'est pas vide,
        // Il y a sûrement une meilleure solution pour remplacer 'isVisible'

        FxAssert.verifyThat("#globalTreeTab", Node::isVisible);
        FxAssert.verifyThat("#multiStoreTreeTab", Node::isVisible);
        FxAssert.verifyThat("#transferTreeTab", Node::isVisible);

        /* Il faudrait ajouter un id aux éléments de l'arbre pour tester qu'ils ont le bon texte
        // et donc que le pane existe et contient les arbres
        FxAssert.verifyThat("#...", LabeledMatchers.hasText("Orders.total_price"));
        FxAssert.verifyThat("#...", LabeledMatchers.hasText("Orders.total_price"));
        FxAssert.verifyThat("#...", LabeledMatchers.hasText("Orders.total_price"));
        */

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
