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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RuleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ExitButton;
    @FXML
    private Button PreviousPageButton;
    @FXML
    void ExitButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Stage lastStage = (Stage) ExitButton.getScene().getWindow();
        lastStage.close();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("GameSnake");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }
    @FXML
    void NextPageButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rulePage2.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Stage lastStage = (Stage) ExitButton.getScene().getWindow();
        lastStage.close();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("GameSnake");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }
    @FXML
    void PreviousPageButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rule.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Stage lastStage = (Stage) PreviousPageButton.getScene().getWindow();
        lastStage.close();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("GameSnake");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }
}
