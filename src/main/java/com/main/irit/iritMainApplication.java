package com.main.irit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class iritMainApplication extends Application {
    private Stage mainStage;
    private iritMainController mainController;
    public static iritMainApplication instance = null;


    public static void main(String[] args) {
        launch(args);
    }


    public static iritMainApplication getInstance() {
        if(iritMainApplication.instance==null){
            synchronized (iritMainApplication.class){
                if(iritMainApplication.instance==null){
                    iritMainApplication.instance=new iritMainApplication();
                }
            }
        }
        return iritMainApplication.instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(iritMainController.class.getResource("/fxml/irit-main-view.fxml"));

        Pane root = loader.load();
        Scene sceneRoot = new Scene(root);
        // Set the scene's "favicon"
        stage.getIcons().add(new Image(String.valueOf(iritMainController.class.getResource("/img/favicon.png"))));

        stage.setScene(sceneRoot);
        stage.setMaximized(true);
        iritMainController mainController = loader.getController();
        mainController.initContext(stage, this);
        mainController.displayDialog();

        Node queryInput = root.lookup("#requestTextField");
        Platform.runLater(queryInput::requestFocus);

        sceneRoot.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.ENTER) {
                mainController.interactWithPolystore();
                ke.consume();
            }
        });
    }
}
