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

import javax.swing.*;
import javax.swing.text.html.ImageView;

public class GameController {

    public javafx.scene.image.ImageView appleImage;
    public javafx.scene.image.ImageView armorImage;

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
    @FXML
    private Button armorButton;

    private Button newTail = new Button();
    double speed = 2;
    boolean newApple = false;
    boolean help = false;
    char direction = 'U';
    int snakeLength = 10;
    int visibleSnake = 3;
    int lengthOfArrayList = 0;
    int coordinatesOfAppleX = 0;
    int coordinatesOfAppleY = 0;
    int coordinatesOfDefX = 0;
    int coordinatesOfDefY = 0;
    Button[] snake = new Button[snakeLength];
    double[] speedOfSnakeX = new double[snakeLength];
    double[] speedOfSnakeY = new double[snakeLength];
    ArrayList<Double> rotateCoordinatesX = new ArrayList<>();
    ArrayList<Double> rotateCoordinatesY = new ArrayList<>();
    int[] numberOfRotate = new int[snakeLength];
    int[] armortail = {10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    int scoreofarmor = 0;

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
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
            coordinatesOfAppleX = random.nextInt(600) + 50;
            coordinatesOfAppleY = random.nextInt(600) + 50;
            newApple = true;

            for (int i = 0; i <= snakeLength - 1; i++){
                if (Math.abs(snake[i].getLayoutX() - coordinatesOfAppleX) < 30 || Math.abs(snake[i].getLayoutY() - coordinatesOfAppleY) < 30){
                    newApple = false;
                }
//                if(snake[i].getLayoutX() == coordinatesOfAppleX && snake[i].getLayoutY() == coordinatesOfAppleY) {
//                    newApple = false;
//                }
            }

            if (newApple && (!appleButton.isVisible())){
                appleButton.setVisible(true);
                appleButton.setLayoutX(coordinatesOfAppleX);
                appleButton.setLayoutY(coordinatesOfAppleY);
            }try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        });
        thread.start();
    }

    public void armorhelp() {
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
        if (visibleSnake <= 5) {
            if (random.nextFloat(1) < 0.995) {
                coordinatesOfDefX = random.nextInt(600) + 50;
                coordinatesOfDefY = random.nextInt(600) + 50;
                for (int i = 0; i <= snakeLength - 1; i++) {
                    if (Math.abs(snake[i].getLayoutX() - coordinatesOfDefX) < 30 || Math.abs(snake[i].getLayoutY() - coordinatesOfDefY) < 30) {
                        help = false;
                        break;
                    } else {
                        help = true;
                    }
                }
            }
            if (help && (!armorButton.isVisible())) {
                armorButton.setVisible(true);
                armorButton.setLayoutX(coordinatesOfDefX);
                armorButton.setLayoutY(coordinatesOfDefY);
            }
        } else {
            armorButton.setVisible(false);
        }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }





    public void checkCollisionOfSnakeHeadAndApple() {
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
        if (appleButton.isVisible() && isFlagforapple()){
//            double maxX = Math.max(snakeHead.getLayoutX(), appleButton.getLayoutX());
//            double maxY = Math.max(snakeHead.getLayoutY(), appleButton.getLayoutY());
//            double minX = Math.min(snakeHead.getLayoutX() + snakeHead.getWidth(), appleButton.getLayoutX() + appleButton.getWidth());
//            double minY = Math.min(snakeHead.getLayoutY() + snakeHead.getHeight(), appleButton.getLayoutY() + appleButton.getHeight());
//            if (maxX >= minX - 10 || maxY >= minY - 10){
//                int a = 0;
//            }
            appleButton.setVisible(false);
            coordinatesOfAppleY = 0;
            coordinatesOfAppleX = 0;
            snakeGrowth();
            }
            else{
                return;
            }try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        });
        thread.start();
        }

    public void checkCollisionOfSnakeHeadAndarmorButton() {
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
        if (armorButton.isVisible() && isFlagforarmor()) {
            System.out.println(armorButton.getLayoutX());
            System.out.println(armorButton.getLayoutY());
            armorButton.setVisible(false);
            if(!armorButton.isVisible()) {
                System.out.println("Невидима");
                System.out.println(armorButton.isVisible());
                System.out.println(armorButton.getLayoutX());
                System.out.println(armorButton.getLayoutY());
            }
            coordinatesOfDefX = 0;
            coordinatesOfDefY = 0;
            snakeArmor();
        }
            else {
                return;
            }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        }

    public boolean isFlagforarmor() {
        if (snakeHead.getLayoutX() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() > armorButton.getLayoutX() && snakeHead.getLayoutY() > armorButton.getLayoutY() && snakeHead.getLayoutY() < armorButton.getLayoutY() + armorButton.getHeight()) {
            System.out.println("был");
            return true;

        } else if (snakeHead.getLayoutX() + snakeHead.getWidth() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > armorButton.getLayoutX() && snakeHead.getLayoutY() > armorButton.getLayoutY() && snakeHead.getLayoutY() < armorButton.getLayoutY() + armorButton.getHeight()) {
            System.out.println("был");
            return true;

        } else if(snakeHead.getLayoutX() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() > armorButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > armorButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < armorButton.getLayoutY() + armorButton.getHeight()) {
            System.out.println("был");
            return true;
        } else if(snakeHead.getLayoutX() + snakeHead.getWidth() < armorButton.getLayoutX() + armorButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > armorButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > armorButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < armorButton.getLayoutY() + armorButton.getHeight()) {
            System.out.println("был");
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isFlagforapple() {
        if (snakeHead.getLayoutX() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() > appleButton.getLayoutX() && snakeHead.getLayoutY() > appleButton.getLayoutY() && snakeHead.getLayoutY() < appleButton.getLayoutY() + appleButton.getHeight()) {
            System.out.println("был");
            return true;

        } else if (snakeHead.getLayoutX() + snakeHead.getWidth() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > appleButton.getLayoutX() && snakeHead.getLayoutY() > appleButton.getLayoutY() && snakeHead.getLayoutY() < appleButton.getLayoutY() + appleButton.getHeight()) {
            System.out.println("был");
            return true;

        } else if(snakeHead.getLayoutX() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() > appleButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > appleButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < appleButton.getLayoutY() + appleButton.getHeight()) {
            System.out.println("был");
            return true;
        } else if(snakeHead.getLayoutX() + snakeHead.getWidth() < appleButton.getLayoutX() + appleButton.getWidth() && snakeHead.getLayoutX() + snakeHead.getWidth() > appleButton.getLayoutX() && snakeHead.getLayoutY() + snakeHead.getHeight() > appleButton.getLayoutY() && snakeHead.getLayoutY() + snakeHead.getHeight() < appleButton.getLayoutY() + appleButton.getHeight()) {
            System.out.println("был");
            return true;
        }
        else {
            return false;
        }
    }

    public void snakeGrowth(){
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
        if (visibleSnake < 10){
            snake[visibleSnake].setVisible(true);
            visibleSnake+=1;
            if(appleButton.isVisible()){
                appleButton.setVisible(false);
            }
        }
        else{
            System.out.println("ПОБЕДА!!!!!!!!!");
        }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void snakereduce(int a){
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {

        snake[a].setVisible(false);
        visibleSnake -= 1;
        armortail[a] += 1;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void snakeArmor() {
        scoreofarmor += 1;
        for(int i = 0; i < visibleSnake; i++) {
                armortail[i] += 1;
                System.out.println("Броня для тела" + " " + i + " " + "Увеличена на" + " " + scoreofarmor);
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
    public void Pressmouse(MouseEvent event) {
                for (int i = 0; i < snakeLength; i++) {
                    if (event.getSource() == snake[i]) {
                        ubivat(i);
                        break;
                    }
                }
    }



    public void ubivat(int a) {
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                if (!snake[a].isVisible()) {
                    return;
                }
                armortail[a] -= 1;
                if (armortail[a] == 0) {
                    for (int i = a; i < snakeLength; i++) {
                        if (snake[i].isVisible()) {
                            snakereduce(i);
                        }
                    }
                }
                if(visibleSnake < 3) {
                    System.out.println("Проиграл!!!!!!!!");
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

//    @Override
//    public String toString(){
//        for (int i = 0; i < lengthOfArrayList; i++){
//            System.out.println(rotateCoordinatesX.get(i));
//            System.out.println(rotateCoordinatesY.get(i));
//        }
//        return "";
//    }
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
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                while (true) {

                    Apple();
                    armorhelp();

                    checkCollisionOfSnakeHeadAndApple();
                    checkCollisionOfSnakeHeadAndarmorButton();


                    for (int i = snakeLength - 1; i >= 0; i--){
                        if (i == 0){
                            snakeHead.setLayoutX(snakeHead.getLayoutX() + speedOfSnakeX[0]);
                            snakeHead.setLayoutY(snakeHead.getLayoutY() + speedOfSnakeY[0]);
                        }
                        else{
                        checkSnake(i);

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
