package com.example.gamesnake;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button RuleButton;

    @FXML
    void initialize() {
    }
    @FXML
    void RuleButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rule.fxml"));
        Parent root = fxmlLoader.load();
        Stage lastStage = (Stage) RuleButton.getScene().getWindow();
        lastStage.close();
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("Rule");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();

    }
    @FXML
    void StartButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = fxmlLoader.load();
        Stage lastStage = (Stage) RuleButton.getScene().getWindow();
        lastStage.close();
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("Game");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }

    @FXML
    void TutorialButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutorial.fxml"));
        Parent root = fxmlLoader.load();
        Stage lastStage = (Stage) RuleButton.getScene().getWindow();
        lastStage.close();
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("Game");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }


}
