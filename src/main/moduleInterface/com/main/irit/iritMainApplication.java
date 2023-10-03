package com.main.irit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class iritMainApplication extends Application {
    private Stage mainStage;
    private iritMainController mainController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        FXMLLoader loader =new FXMLLoader(iritMainController.class.getResource("irit-main-view.fxml"));
        Pane root = loader.load();
        Scene sceneRoot = new Scene(root);
        stage.setScene(sceneRoot);
        this.mainController =loader.getController();
        this.mainController.initContext(mainStage, this);
        this.mainController.displayDialog();
    }
}
