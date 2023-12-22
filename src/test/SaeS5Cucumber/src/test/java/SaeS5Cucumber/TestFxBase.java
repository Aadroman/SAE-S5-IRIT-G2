

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import nl.smit.javafx_spring.SpringJavaFxApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

/**
 * Sets up the application for TestFx tests.
 *
 * @author Jordi Smit, 8-2-2018.
 */
public abstract class TestFxBase extends ApplicationTest  {

    @Getter(AccessLevel.PROTECTED)
    @SuppressWarnings("nullness")
    private Stage primaryStage;



    @BeforeAll
    static void beforeAll() {
        doNotDisplayGUIDuringTest();
    }

    private static void doNotDisplayGUIDuringTest() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }

    protected static void displayGUIDuringTest() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "false");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "false");
    }

    @BeforeEach
    void beforeEachTestFXBase() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(getApplicationClass());
    }

    /**
     *
     * @param <T> The type of the class.
     * @return The application class to test.
     */
    protected abstract <T extends SpringJavaFxApplication> Class<T> getApplicationClass();

    @AfterEach
    void afterEachTestFXBase() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.show();
    }

    protected void ensureEventQueueComplete() {
        WaitForAsyncUtils.waitForFxEvents(1);
    }
}