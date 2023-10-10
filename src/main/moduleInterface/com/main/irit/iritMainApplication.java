package com.main.irit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class iritMainApplication extends Application {
    private Stage mainStage;
    private iritMainController mainController;
    public static volatile iritMainApplication instance = null;

    public static void main(String[] args) {
        launch(args);
    }

    private iritMainApplication(){super();}

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
        mainStage = stage;
        FXMLLoader loader = new FXMLLoader(iritMainController.class.getResource("/fxml/irit-main-view.fxml"));

        Pane root = loader.load();
        Scene sceneRoot = new Scene(root);
        // Set the scene's "favicon"
        stage.getIcons().add(new Image(String.valueOf(iritMainController.class.getResource("/img/favicon.png"))));

        stage.setScene(sceneRoot);
        stage.setMaximized(true);
        this.mainController = loader.getController();
        this.mainController.initContext(mainStage, this);
        this.mainController.displayDialog();
    }
}
