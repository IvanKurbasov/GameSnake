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
    private Button StartButton;

    @FXML
    void initialize() {
        StartButton.setBackground(null);
        RuleButton.setBackground(null);
    }
    @FXML
    void RuleButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rule.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Rule");
        stage.setScene(new Scene(root, 600, 600));
        stage.showAndWait();
    }
    @FXML
    void StartButtonClicked(MouseEvent event) {
        System.out.println("12345");
    }


}
