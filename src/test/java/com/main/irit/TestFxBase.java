package com.main.irit;

import com.main.irit.iritMainApplication;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;

import java.util.concurrent.TimeoutException;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;


public class TestFxBase extends ApplicationTest {
    @Getter(AccessLevel.PROTECTED)
    @SuppressWarnings("nullness")
    private Stage primaryStage;
    protected iritMainController irit = new iritMainController();
    protected iritMainApplication app = irit.getApp();
    private static boolean isHeadless = false;

    static {
        if (isHeadless) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
        try {
            ApplicationTest.launch(iritMainApplication.class);
        } catch (Exception e) {
            // oh no
        }
    }



    @BeforeAll
//    static void beforeAll() {
//        doNotDisplayGUIDuringTest();
//    }

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

//    @BeforeEach
//    void beforeEachTestFXBase() throws Exception {
//        FxToolkit.registerPrimaryStage();
//        FxToolkit.setupApplication(iritMainApplication.class);
//    }


//    @AfterEach
//    void afterEachTestFXBase() throws TimeoutException {
//        FxToolkit.hideStage();
//        release(new KeyCode[]{});
//        release(new MouseButton[]{});
//    }


    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
//        app.start(stage);
        stage.show();
    }

    protected void ensureEventQueueComplete() {
        WaitForAsyncUtils.waitForFxEvents(1);
    }

    /* Helper method to retrieve Java FX GUI Components */
    public <T extends Node> T find (final  String query){
        return (T) lookup(query).queryAll().iterator().next();
    }

}
