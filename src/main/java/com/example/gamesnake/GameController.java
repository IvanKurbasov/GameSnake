package com.example.gamesnake;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class GameController {

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
    private AnchorPane pane;
    double speed = 2;
    char direction = 'U';
    int snakeLenght = 3;
    int maxLenght = 10;
    Button[] snake = new Button[snakeLenght];
    double[] speedOfSnakeX = new double[snakeLenght];
    double[] speedOfSnakeY = new double[snakeLenght];
    ArrayList<Double> rotateCoordinatesX = new ArrayList<>();
    ArrayList<Double> rotateCoordinatesY = new ArrayList<>();
    int lenghtOfArrayList = 0;
    int[] numberOfRotate = new int[snakeLenght];


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
            lenghtOfArrayList++;
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
            if (rotateCoordinatesX.get(lenghtOfArrayList-1) == snakeHead.getLayoutX() && (Math.abs(rotateCoordinatesY.get(lenghtOfArrayList-1) - snakeHead.getLayoutY())) > 20){
                rotateCoordinatesX.add(snakeHead.getLayoutX());
                rotateCoordinatesY.add(snakeHead.getLayoutY());
                lenghtOfArrayList++;
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
            if (rotateCoordinatesY.get(lenghtOfArrayList-1) == snakeHead.getLayoutY() && (Math.abs(rotateCoordinatesX.get(lenghtOfArrayList-1) - snakeHead.getLayoutX())) > 20) {
                rotateCoordinatesX.add(snakeHead.getLayoutX());
                rotateCoordinatesY.add(snakeHead.getLayoutY());
                lenghtOfArrayList++;
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
    void initialize() {
        snake[0] = snakeHead;
        snake[1] = snakeTail1;
        snake[2] = snakeTail2;
        for (int i = 0; i <= snakeLenght - 1; i++){
            speedOfSnakeX[i] = 0;
            speedOfSnakeY[i] = -speed;

            numberOfRotate[i] = 0;
        }
        move();
    }

    @Override
    public String toString(){
        for (int i = 0; i < lenghtOfArrayList; i++){
            System.out.println(rotateCoordinatesX.get(i));
            System.out.println(rotateCoordinatesY.get(i));
        }
        return "";
    }


    public void move(){
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                while (true) {
                    for (int i = snakeLenght - 1; i >= 0; i--){
                        if (i == 0){
                            snakeHead.setLayoutX(snakeHead.getLayoutX() + speedOfSnakeX[0]);
                            snakeHead.setLayoutY(snakeHead.getLayoutY() + speedOfSnakeY[0]);
                        }
                        else{
//                            System.out.println(rotateCoordinatesX);
//                            System.out.println(rotateCoordinatesY);
//                            System.out.println(lenghtOfArrayList);
                            snake[i].setLayoutX(snake[i].getLayoutX() + speedOfSnakeX[i]);
                            snake[i].setLayoutY(snake[i].getLayoutY() + speedOfSnakeY[i]);

                            if (!rotateCoordinatesX.isEmpty() && (lenghtOfArrayList > numberOfRotate[i])){
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
