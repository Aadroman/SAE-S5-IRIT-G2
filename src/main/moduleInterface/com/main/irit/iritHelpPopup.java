package com.main.irit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class iritHelpPopup {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/helpPopup.fxml")));

        Scene scene = new Scene(root);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.setScene(scene);
        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (!popupStage.getScene().getRoot().getLayoutBounds().contains(event.getSceneX(), event.getSceneY())) {
                popupStage.close();
            }
        });
        popupStage.setResizable(false);
        popupStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/img/help.png"))));
        popupStage.show();
    }
}
