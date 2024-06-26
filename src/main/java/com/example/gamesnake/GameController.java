package com.example.gamesnake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController {

    public javafx.scene.image.ImageView appleImage;
    public javafx.scene.image.ImageView armorImage;
    public javafx.scene.image.ImageView GameOverImage;
    public javafx.scene.image.ImageView VictoryImage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button snakeHead;
    @FXML
    private Button snakeTail1;
    @FXML
    private Button snakeTail2;
    @FXML
    private Button snakeTail3;

    @FXML
    private Button snakeTail4;

    @FXML
    private Button snakeTail5;

    @FXML
    private Button snakeTail6;

    @FXML
    private Button snakeTail7;

    @FXML
    private Button snakeTail8;

    @FXML
    private Button snakeTail9;
    @FXML
    private Button appleButton;
    @FXML
    private Button armorButton;
    @FXML
    private Button anotherGameButton;
    @FXML
    private Button toMenuButton;
    @FXML
    private Button anotherGameButton1;
    @FXML
    private Button toMenuButton1;
    double speed = 4;
    boolean newApple = false;
    boolean help = false;
    boolean finish = false;
    char direction = 'U';
    int snakeLength = 10;
    int visibleSnake = 3;
    int lengthOfArrayList = 0;
    int coordinatesOfAppleX = 0;
    int coordinatesOfAppleY = 0;
    int coordinatesOfDefX = 0;
    int coordinatesOfDefY = 0;
    private int qPressed = 0;
    Button[] snake = new Button[snakeLength];
    double[] speedOfSnakeX = new double[snakeLength];
    double[] speedOfSnakeY = new double[snakeLength];
    ArrayList<Double> rotateCoordinatesX = new ArrayList<>();
    ArrayList<Double> rotateCoordinatesY = new ArrayList<>();
    int[] numberOfRotate = new int[snakeLength];
    int[] armortail = {6, 5, 4, 1, 1, 1, 1, 1, 1, 1};
    int aimcontrol = 10;
    int startarmor;
    boolean buttonwasdelete = false;
    Random random = new Random();

    @FXML
    void initialize() {
        snake[0] = snakeHead;
        snake[1] = snakeTail1;
        snake[2] = snakeTail2;
        snake[3] = snakeTail3;
        snake[4] = snakeTail4;
        snake[5] = snakeTail5;
        snake[6] = snakeTail6;
        snake[7] = snakeTail7;
        snake[8] = snakeTail8;
        snake[9] = snakeTail9;

        for (int i = 3; i < snakeLength; i++){
            snake[i].setVisible(false);
        }

        appleButton.setVisible(false);
        anotherGameButton.setVisible(false);
        toMenuButton.setVisible(false);
        GameOverImage.setVisible(false);
        anotherGameButton.setText("Играть еще раз");
        toMenuButton.setText("Меню");
        anotherGameButton1.setVisible(false);
        toMenuButton1.setVisible(false);
        anotherGameButton1.setText("Играть еще раз");
        toMenuButton1.setText("Меню");

        for (int i = 0; i < snakeLength; i++){
            speedOfSnakeX[i] = 0;
            speedOfSnakeY[i] = -speed;

            numberOfRotate[i] = 0;
        }

        move();
    }

    public void Apple() {
        coordinatesOfAppleX = random.nextInt(600) + 50;
        coordinatesOfAppleY = random.nextInt(600) + 50;
        newApple = true;

        for (int i = 0; i <= snakeLength - 1; i++){
            if (Math.abs(snake[i].getLayoutX() - coordinatesOfAppleX) < 30 || Math.abs(snake[i].getLayoutY() - coordinatesOfAppleY) < 30){
                newApple = false;
            }
        }

        if (Math.abs(coordinatesOfAppleX - coordinatesOfDefX) < 30 || Math.abs(coordinatesOfAppleY - coordinatesOfDefY) < 30){
            newApple = false;
        }

        if (newApple && (!appleButton.isVisible())){
            appleButton.setVisible(true);
            appleButton.setLayoutX(coordinatesOfAppleX);
            appleButton.setLayoutY(coordinatesOfAppleY);
        }
    }

    public void armorhelp() {
        if (random.nextFloat(1) < 0.998) {
            coordinatesOfDefX = random.nextInt(600) + 50;
            coordinatesOfDefY = random.nextInt(600) + 50;
            help = true;
            for (int i = 0; i <= snakeLength - 1; i++) {
                if (Math.abs(snake[i].getLayoutX() - coordinatesOfDefX) < 30 || Math.abs(snake[i].getLayoutY() - coordinatesOfDefY) < 30) {
                    help = false;
                    break;
                }
                if (Math.abs(coordinatesOfAppleX - coordinatesOfDefX) < 30 || Math.abs(coordinatesOfAppleY - coordinatesOfDefY) < 30){
                    help = false;
                }
            }
        }
        if (help && (!armorButton.isVisible())) {
            armorButton.setVisible(true);
            armorButton.setLayoutX(coordinatesOfDefX);
            armorButton.setLayoutY(coordinatesOfDefY);
        }
    }


    public void checkCollisionOfSnakeHeadAndApple() {

        if (appleButton.isVisible() && isFlagforapple()){
            appleButton.setVisible(false);
            coordinatesOfAppleY = 0;
            coordinatesOfAppleX = 0;
            snakeGrowth();
            }
    }

    public void checkCollisionOfSnakeHeadAndarmorButton() {
        if (armorButton.isVisible() && isFlagforarmor()) {
            armorButton.setVisible(false);
            coordinatesOfDefX = 0;
            coordinatesOfDefY = 0;
            snakeArmor();
        }

    }

    public boolean isFlagforarmor() {
        if (snakeHead.getLayoutX() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() > armorButton.getLayoutX() && snakeHead.getLayoutY() > armorButton.getLayoutY() && snakeHead.getLayoutY() < armorButton.getLayoutY() + armorButton.getHeight()) {
            return true;

        } else if (snakeHead.getLayoutX() + snakeHead.getWidth() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > armorButton.getLayoutX() && snakeHead.getLayoutY() > armorButton.getLayoutY() && snakeHead.getLayoutY() < armorButton.getLayoutY() + armorButton.getHeight()) {
            return true;

        } else if(snakeHead.getLayoutX() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() > armorButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > armorButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < armorButton.getLayoutY() + armorButton.getHeight()) {
            return true;
        } else if(snakeHead.getLayoutX() + snakeHead.getWidth() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > armorButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > armorButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < armorButton.getLayoutY() + armorButton.getHeight()) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isFlagforapple() {
        if (snakeHead.getLayoutX() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() > appleButton.getLayoutX() && snakeHead.getLayoutY() > appleButton.getLayoutY() && snakeHead.getLayoutY() < appleButton.getLayoutY() + appleButton.getHeight()) {
            return true;

        } else if (snakeHead.getLayoutX() + snakeHead.getWidth() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > appleButton.getLayoutX() && snakeHead.getLayoutY() > appleButton.getLayoutY() && snakeHead.getLayoutY() < appleButton.getLayoutY() + appleButton.getHeight()) {
            return true;

        } else if(snakeHead.getLayoutX() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() > appleButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > appleButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < appleButton.getLayoutY() + appleButton.getHeight()) {
            return true;
        } else if(snakeHead.getLayoutX() + snakeHead.getWidth() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > appleButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > appleButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < appleButton.getLayoutY() + appleButton.getHeight()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void snakeGrowth(){
        if (visibleSnake < 10){
            snake[visibleSnake].setVisible(true);
            visibleSnake+=1;
        }
        if (armortail[visibleSnake-2] == 1){
            armortail[visibleSnake-1] = 1;
        }
        else{
            armortail[visibleSnake-1] = armortail[visibleSnake-2] - 1;
        }
        for (int i = 0; i < snakeLength; i++){
        }
    }

    public void snakereduce(int a){
        snake[a].setVisible(false);
        visibleSnake -= 1;
        armortail[a] += 1;
    }

    private void snakeArmor() {
        for(int i = 0; i < visibleSnake; i++) {
            armortail[i] += 1;
        }
    }

    @FXML
    void OnKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.Q){
            qPressed += 1;
        }
        if ((snakeHead.getLayoutX() == snake[1].getLayoutX())  || (snakeHead.getLayoutY() == snake[1].getLayoutY())){
            if (snake[0].getLayoutX() == snake[1].getLayoutX() || snake[0].getLayoutY() == snake[1].getLayoutY()){
                if (event.getCode() == KeyCode.W && direction != 'D' && direction != 'U') {
                    direction = 'U';
                    changeDirection();
                }
                if (event.getCode() == KeyCode.S && direction != 'U' && direction != 'D') {
                    direction = 'D';
                    changeDirection();
                }
                if (event.getCode() == KeyCode.A && direction != 'R' && direction != 'L') {
                    direction = 'L';
                    changeDirection();
                }
                if (event.getCode() == KeyCode.D && direction != 'L' && direction != 'R') {
                    direction = 'R';
                    changeDirection();
                }
            }
        }
    }

    public void changeDirection() {
        if (rotateCoordinatesX.isEmpty()){
            rotateCoordinatesX.add(snakeHead.getLayoutX());
            rotateCoordinatesY.add(snakeHead.getLayoutY());
            lengthOfArrayList++;
            if (direction == 'U') {
                speedOfSnakeX[0] = 0;
                speedOfSnakeY[0] = -speed;
            }
            if (direction == 'D') {
                speedOfSnakeX[0] = 0;
                speedOfSnakeY[0] = speed;
            }
            if (direction == 'L') {
                speedOfSnakeX[0] = -speed;
                speedOfSnakeY[0] = 0;
            }
            if (direction == 'R') {
                speedOfSnakeX[0] = speed;
                speedOfSnakeY[0] = 0;
            }
        }
        else{
            if (rotateCoordinatesX.get(lengthOfArrayList-1) == snakeHead.getLayoutX() && (Math.abs(rotateCoordinatesY.get(lengthOfArrayList-1) - snakeHead.getLayoutY())) >= snakeHead.getWidth()){
                rotateCoordinatesX.add(snakeHead.getLayoutX());
                rotateCoordinatesY.add(snakeHead.getLayoutY());
                lengthOfArrayList++;
                if (direction == 'U') {
                    speedOfSnakeX[0] = 0;
                    speedOfSnakeY[0] = -speed;
                }
                if (direction == 'D') {
                    speedOfSnakeX[0] = 0;
                    speedOfSnakeY[0] = speed;
                }
                if (direction == 'L') {
                    speedOfSnakeX[0] = -speed;
                    speedOfSnakeY[0] = 0;
                }
                if (direction == 'R') {
                    speedOfSnakeX[0] = speed;
                    speedOfSnakeY[0] = 0;
                }
            }
            if (rotateCoordinatesY.get(lengthOfArrayList-1) == snakeHead.getLayoutY() && (Math.abs(rotateCoordinatesX.get(lengthOfArrayList-1) - snakeHead.getLayoutX())) >= snakeHead.getHeight()) {
                rotateCoordinatesX.add(snakeHead.getLayoutX());
                rotateCoordinatesY.add(snakeHead.getLayoutY());
                lengthOfArrayList++;
                if (direction == 'U') {
                    speedOfSnakeX[0] = 0;
                    speedOfSnakeY[0] = -speed;
                }
                if (direction == 'D') {
                    speedOfSnakeX[0] = 0;
                    speedOfSnakeY[0] = speed;
                }
                if (direction == 'L') {
                    speedOfSnakeX[0] = -speed;
                    speedOfSnakeY[0] = 0;
                }
                if (direction == 'R') {
                    speedOfSnakeX[0] = speed;
                    speedOfSnakeY[0] = 0;
                }
            }
        }
    }

    @FXML
    public void Pressmouse(MouseEvent event) {
        for (int i = 0; i < snakeLength; i++) {
            if (event.getSource() == snake[i]) {
                if (i != aimcontrol) {
                    if (aimcontrol != 10 && armortail[aimcontrol] < startarmor && !(buttonwasdelete)) {
                        armortail[aimcontrol] = startarmor;
                    }
                    buttonwasdelete = false;
                    startarmor = armortail[i];
                    aimcontrol = i;
                    ubivat(i);
                    break;
                } else {
                    ubivat(i);
                    break;
                }
            }
        }
    }

    public void gameOver() {
        if (snakeHead.getLayoutX() <= 0 || snakeHead.getLayoutX() >= 700 - snakeHead.getWidth() || snakeHead.getLayoutY() <= 0 || snakeHead.getLayoutY() >= 700 - snakeHead.getHeight() || visibleSnake < 3){
            finish = true;
            for (int i = 0; i < visibleSnake; i++){
                snake[i].setVisible(false);
            }
            appleButton.setVisible(false);
            armorButton.setVisible(false);
            anotherGameButton.setVisible(true);
            toMenuButton.setVisible(true);
            GameOverImage.setVisible(true);
        }
    }
    public void victory(){
        if (visibleSnake == 10 || qPressed >= 5){
            finish = true;
            for (int i = 0; i < visibleSnake; i++){
                snake[i].setVisible(false);
            }
            appleButton.setVisible(false);
            armorButton.setVisible(false);
            anotherGameButton1.setVisible(true);
            toMenuButton1.setVisible(true);
            VictoryImage.setVisible(true);
        }
    }

    public void ubivat(int a) {
        if (!snake[a].isVisible()) {
            return;
        }
        armortail[a] -= 1;
        if (armortail[a] == 0) {
            for (int i = a; i < snakeLength; i++) {
                if (snake[i].isVisible()) {
                    buttonwasdelete = true;
                    snakereduce(i);
                }
            }
        }
    }


    public void checkSnake(int i){
        if (speedOfSnakeX[i] == speedOfSnakeX[i-1] && speedOfSnakeX[i] != 0){
            if (Math.abs(snake[i].getLayoutX() - snake[i-1].getLayoutX()) < 28){
                if (direction == 'R'){
                    snake[i-1].setLayoutX(snake[i].getLayoutX()+28);
                    //System.out.println("R");
                }
                if (direction == 'L'){
                    snake[i-1].setLayoutX(snake[i].getLayoutX()-28);
                    //System.out.println("L");
                }
            }
        }
        if (speedOfSnakeY[i] == speedOfSnakeY[i-1] && speedOfSnakeY[i] != 0){
            if (Math.abs(snake[i].getLayoutY() - snake[i-1].getLayoutY()) < 28){
                if (direction == 'U'){
                    snake[i-1].setLayoutY(snake[i].getLayoutY()-28);
                    //System.out.println("U");
                }
                if (direction == 'D'){
                    snake[i-1].setLayoutY(snake[i].getLayoutY()+28);
                    //System.out.println("D");
                }
            }
        }
    }
    public void move(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {

                Apple();
                armorhelp();

                checkCollisionOfSnakeHeadAndApple();
                checkCollisionOfSnakeHeadAndarmorButton();

                gameOver();
                victory();

                for (int i = snakeLength - 1; i >= 0; i--){
                    if (i == 0){
                        snakeHead.setLayoutX(snakeHead.getLayoutX() + speedOfSnakeX[0]);
                        snakeHead.setLayoutY(snakeHead.getLayoutY() + speedOfSnakeY[0]);
                    }
                    else{

                        checkSnake(i);

                        snake[i].setLayoutX(snake[i].getLayoutX() + speedOfSnakeX[i]);
                        snake[i].setLayoutY(snake[i].getLayoutY() + speedOfSnakeY[i]);

                        if (!rotateCoordinatesX.isEmpty() && (lengthOfArrayList > numberOfRotate[i])){
                            if ((rotateCoordinatesX.get(numberOfRotate[i]) == snake[i].getLayoutX()) && (rotateCoordinatesY.get(numberOfRotate[i]) == snake[i].getLayoutY())){
                                if (i == 1){
                                    speedOfSnakeX[i] = speedOfSnakeX[0];
                                    speedOfSnakeY[i] = speedOfSnakeY[0];
                                }
                                else{
                                    speedOfSnakeX[i] = speedOfSnakeX[i-1];
                                    speedOfSnakeY[i] = speedOfSnakeY[i-1];
                                }
                                numberOfRotate[i]++;
                            }
                        }
                    }
                }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        if (finish){
            timeline.stop();
        }
    }


    @FXML
    void anotherGameButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Stage lastStage = (Stage) snakeHead.getScene().getWindow();
        lastStage.close();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("GameSnake");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }

    @FXML
    void toMenuButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Stage lastStage = (Stage) snakeHead.getScene().getWindow();
        lastStage.close();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("GameSnake");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }

}
