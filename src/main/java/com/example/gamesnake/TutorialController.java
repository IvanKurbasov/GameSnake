package com.example.gamesnake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TutorialController {
    public javafx.scene.image.ImageView appleImage;
    @FXML
    private Button appleButton;
    boolean newApple = false;
    int coordinatesOfAppleX = 0;
    int coordinatesOfAppleY = 0;
    Random random = new Random();
    int visibleSnake = 3;

    @FXML
    private Button ToMenuButton;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;
    @FXML
    private Text text6;
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
    private int snakeLength = 10;
    int lengthOfArrayList = 0;
    char direction = 'U';
    private int speed = 2;
    private int stage = 1;
    private int currentStage = 1;
    private int wPressed = 0;
    private int sPressed = 0;
    private int aPressed = 0;
    private int dPressed = 0;
    boolean startStage2 = true;
    boolean needToCreateApple = false;
    Button[] snake = new Button[snakeLength];
    double[] speedOfSnakeX = new double[snakeLength];
    double[] speedOfSnakeY = new double[snakeLength];
    ArrayList<Double> rotateCoordinatesX = new ArrayList<>();
    ArrayList<Double> rotateCoordinatesY = new ArrayList<>();
    int[] numberOfRotate = new int[snakeLength];


    @FXML
    void initialize() {

        text1.setText("                                        Привет!");
        text2.setText("                  Давай научимся играть в эту игру!");
        text3.setText("Для управления змейкой нажимайте на клавиатуру:");
        text4.setText("           W - вверх, S - вниз, A - влево, D - вправо");
        text5.setText("Если змейка движется вверх, то нельзя нажимать кнопку 'вниз'");
        text6.setText("Если змейка движется влево, то нельзя нажимать кнопку 'вправо'");

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

    public void Stage2(){
        text1.setText("Чтобы расти, змейке нужно кушать яблочки");
        text2.setText("Каждый раз, когда она ест яблоко, она увеличивается в размере на 1");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
        needToCreateApple = true;
    }



    public void Apple(){
        if (random.nextFloat(1) < 0.995){
            coordinatesOfAppleX = random.nextInt(600) + 50;
            coordinatesOfAppleY = random.nextInt(460) + 160;
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

    public void checkCollisionOfSnakeHeadAndApple(){
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
        if ((snakeHead.getLayoutX() == snake[1].getLayoutX()) || (snakeHead.getLayoutY() == snake[1].getLayoutY())) {
            if (snake[0].getLayoutX() == snake[1].getLayoutX() || snake[0].getLayoutY() == snake[1].getLayoutY()) {
                if (event.getCode() == KeyCode.W && direction != 'D' && direction != 'U') {
                    direction = 'U';
                    wPressed+=1;
                    changeDirection();
                }
                if (event.getCode() == KeyCode.S && direction != 'U' && direction != 'D') {
                    direction = 'D';
                    sPressed+=1;
                    changeDirection();
                }
                if (event.getCode() == KeyCode.A && direction != 'R' && direction != 'L') {
                    direction = 'L';
                    aPressed+=1;
                    changeDirection();
                }
                if (event.getCode() == KeyCode.D && direction != 'L' && direction != 'R') {
                    direction = 'R';
                    dPressed+=1;
                    changeDirection();
                }
            }
        }
    }


    public void changeDirection() {
        if (rotateCoordinatesX.isEmpty()) {
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
        } else {
            if (rotateCoordinatesX.get(lengthOfArrayList - 1) == snakeHead.getLayoutX() && (Math.abs(rotateCoordinatesY.get(lengthOfArrayList - 1) - snakeHead.getLayoutY())) >= snakeHead.getWidth()) {
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
            if (rotateCoordinatesY.get(lengthOfArrayList - 1) == snakeHead.getLayoutY() && (Math.abs(rotateCoordinatesX.get(lengthOfArrayList - 1) - snakeHead.getLayoutX())) >= snakeHead.getHeight()) {
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


    public void move(){
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                while (true) {
                    if (needToCreateApple){
                        Apple();
                    }

                    checkCollisionOfSnakeHeadAndApple();

                    if (wPressed >= 2 && sPressed >= 2 && aPressed >= 2 && dPressed >= 2 && startStage2){
                        Stage2();
                        startStage2 = false;
                    }

                    for (int i = snakeLength - 1; i >= 0; i--){
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

    @FXML
    void ToMenuButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Stage lastStage = (Stage) ToMenuButton.getScene().getWindow();
        lastStage.close();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setOpacity(1);
        newStage.setTitle("GameSnake");
        newStage.setScene(new Scene(root, 700, 700));
        newStage.show();
    }

}
