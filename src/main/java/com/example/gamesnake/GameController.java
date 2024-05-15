package com.example.gamesnake;

import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javax.swing.text.html.ImageView;

public class GameController {

    public javafx.scene.image.ImageView appleImage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label;
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
    private AnchorPane pane;
    @FXML
    private Button appleButton;
    double speed = 2;
    boolean newApple = false;
    char direction = 'U';
    int snakeLength = 10;
    int visibleSnake = 3;
    int lengthOfArrayList = 0;
    int coordinatesOfAppleX = 0;
    int coordinatesOfAppleY = 0;
    Button[] snake = new Button[snakeLength];
    double[] speedOfSnakeX = new double[snakeLength];
    double[] speedOfSnakeY = new double[snakeLength];
    ArrayList<Double> rotateCoordinatesX = new ArrayList<>();
    ArrayList<Double> rotateCoordinatesY = new ArrayList<>();
    int[] numberOfRotate = new int[snakeLength];
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

        for (int i = 0; i < snakeLength; i++){
            speedOfSnakeX[i] = 0;
            speedOfSnakeY[i] = -speed;

            numberOfRotate[i] = 0;
        }

        move();
    }

    public void Apple(){
        if (random.nextFloat(1) < 0.995){
            coordinatesOfAppleX = random.nextInt(600) + 50;
            coordinatesOfAppleY = random.nextInt(600) + 50;
            newApple = true;

            for (int i = 0; i <= snakeLength - 1; i++){
                if (Math.abs(snake[i].getLayoutX() - coordinatesOfAppleX) < 30 || Math.abs(snake[i].getLayoutY() - coordinatesOfAppleY) < 30){
                    newApple = false;
                }
            }

            if (newApple && (!appleButton.isVisible())){
                appleButton.setVisible(true);
                appleButton.setLayoutX(coordinatesOfAppleX);
                appleButton.setLayoutY(coordinatesOfAppleY);
            }
        }
    }



    public void checkCollisionOfSnakeHeadAndApple() {
        if (appleButton.isVisible()){
            double maxX = Math.max(snakeHead.getLayoutX(), appleButton.getLayoutX());
            double maxY = Math.max(snakeHead.getLayoutY(), appleButton.getLayoutY());
            double minX = Math.min(snakeHead.getLayoutX() + snakeHead.getWidth(), appleButton.getLayoutX() + appleButton.getWidth());
            double minY = Math.min(snakeHead.getLayoutY() + snakeHead.getHeight(), appleButton.getLayoutY() + appleButton.getHeight());
            if (maxX >= minX - 10 || maxY >= minY - 10){
                int a = 0;
            }
            else{
                appleButton.setVisible(false);
                coordinatesOfAppleY = 0;
                coordinatesOfAppleX = 0;
                snakeGrowth();
            }
        }

    }

    public void snakeGrowth(){
        if (visibleSnake < 10){
            snake[visibleSnake].setVisible(true);
            visibleSnake+=1;
        }
        else{
            System.out.println("ПОБЕДА!!!!!!!!!");
        }
    }


    @FXML
    void OnKeyPressed(KeyEvent event) {
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
    void destroySnake(MouseEvent event) {
    }
//    @Override
//    public String toString(){
//        for (int i = 0; i < lengthOfArrayList; i++){
//            System.out.println(rotateCoordinatesX.get(i));
//            System.out.println(rotateCoordinatesY.get(i));
//        }
//        return "";
//    }

    public void move(){
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                while (true) {

                    Apple();

                    checkCollisionOfSnakeHeadAndApple();


                    for (int i = snakeLength - 1; i >= 0; i--){
                        if (i == 0){
                            snakeHead.setLayoutX(snakeHead.getLayoutX() + speedOfSnakeX[0]);
                            snakeHead.setLayoutY(snakeHead.getLayoutY() + speedOfSnakeY[0]);
                        }
                        else{

                            if (speedOfSnakeX[i] == speedOfSnakeX[i-1] && speedOfSnakeX[i] != 0){
                                if (Math.abs(snake[i].getLayoutX() - snake[i-1].getLayoutX()) != 28){
                                    snake[i].setLayoutX(snake[i-1].getLayoutX() + 28);
                                }
                            }
                            if (speedOfSnakeY[i] == speedOfSnakeY[i-1] && speedOfSnakeY[i] != 0){
                                if (Math.abs(snake[i].getLayoutY() - snake[i-1].getLayoutY()) != 28){
                                    snake[i].setLayoutY(snake[i-1].getLayoutY() + 28);
                                }
                            }

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
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}
