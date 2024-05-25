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

public class TutorialController extends HelloApplication{
    public javafx.scene.image.ImageView appleImage;
    public javafx.scene.image.ImageView armorImage;
    boolean newApple = false;
    boolean help = false;
    int coordinatesOfAppleX = 0;
    int coordinatesOfAppleY = 0;
    int coordinatesOfDefX = 0;
    int coordinatesOfDefY = 0;
    Random random = new Random();
    int visibleSnake = 3;
    @FXML
    private Button appleButton;
    @FXML
    private Button armorButton;
    @FXML
    private AnchorPane pane;
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
    private int currentStage = 1;
    private int wPressed = 0;
    private int sPressed = 0;
    private int aPressed = 0;
    private int dPressed = 0;
    boolean startStage2 = true;
    boolean startStage3 = true;
    boolean startStage4 = true;
    boolean needToCreateApple = false;
    boolean finish = false;
    Button[] snake = new Button[snakeLength];
    double[] speedOfSnakeX = new double[snakeLength];
    double[] speedOfSnakeY = new double[snakeLength];
    ArrayList<Double> rotateCoordinatesX = new ArrayList<>();
    ArrayList<Double> rotateCoordinatesY = new ArrayList<>();
    int[] numberOfRotate = new int[snakeLength];
    int[] armortail = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    int aimcontrol = 10;
    int startarmor;
    boolean buttonwasdelete = false;

    @FXML
    void initialize() {

        text1.setText("                                                        Привет!");
        text2.setText("                               Давай научимся играть в эту игру!");
        text3.setText("                 Для управления змейкой нажимайте на клавиатуру:");
        text4.setText("                        W - вверх, S - вниз, A - влево, D - вправо");
        text5.setText("        Если змейка движется вверх, то нельзя нажимать кнопку 'вниз'");
        text6.setText("        Если змейка движется влево, то нельзя нажимать кнопку 'вправо'");

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
        armorButton.setVisible(false);

        for (int i = 0; i < snakeLength; i++){
            speedOfSnakeX[i] = 0;
            speedOfSnakeY[i] = -speed;

            numberOfRotate[i] = 0;
        }

        move();

    }

    public void Stage2(){
        text1.setText("                       Чтобы расти, змейке нужно кушать яблочки");
        text2.setText("Каждый раз, когда она ест яблоко, она увеличивается в размере на 1");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
        needToCreateApple = true;
    }

    public void Stage3(){
        text1.setText("      Игрок, управляющей мышкой, должен пытаться убить змейку");
        text2.setText("Каждый раз, когда он нажмет на хвост змеи, его жизни уменьшатся");
        text3.setText("Если жизни опустятся до 0, весь хвост ниже уничтоженной кнопки тоже");
        text4.setText("исчезнет. Нажимайте на хвост змейки, чтобы уничтожить его. Учтите,");
        text5.setText("что каждый хвост имеет свои жизни. Если нажать на другой хвост, то");
        text6.setText("жизни первого полностью востановятся. Попробуйте убить змейку!");
    }

    public void Stage4() {
        text1.setText("На поле будут появляться щиты. Они создают броню для хвоста. Это");
        text2.setText("повысит выживаемость змейки. Старайтесь собирать щиты как можно");
        text3.setText("быстрее. Совет: двигайтесь очень странно, извилисто и");
        text4.setText("непредсказуемо, чтобы вас было тяжелее уничтожить");
        text5.setText("");
        text6.setText("");
    }

    public void armorhelp() {
        if (random.nextFloat(1) < 0.998) {
            coordinatesOfDefX = random.nextInt(600) + 50;
            coordinatesOfDefY = random.nextInt(450) + 50;
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
    }

    private void snakeArmor() {
        for(int i = 0; i < visibleSnake; i++) {
            armortail[i] += 1;
            System.out.println("Броня для тела" + " " + i + " " + "Увеличена до" + " " + armortail[i]);
        }
    }
    @FXML
    void Pressmouse(MouseEvent event) {
        if (currentStage >= 3) {
            for (int i = 0; i < snakeLength; i++) {
                if (event.getSource() == snake[i]) {
                    System.out.println("Попал по телу " + i);
                    if (i != aimcontrol) {
                        if (aimcontrol != 10 && armortail[aimcontrol] < startarmor && !(buttonwasdelete)) {
                            armortail[aimcontrol] = startarmor;
                            System.out.println("Броня востановлена для тела " + aimcontrol + " до " + startarmor);
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
    }

    public void ubivat(int a) {
        if (currentStage >= 3) {
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
            if (visibleSnake < 3) {
                System.out.println("Проиграл!!!!!!!!");
            }
        }
    }

    public void snakereduce(int a) {
        if (currentStage >= 3) {
            snake[a].setVisible(false);
            visibleSnake -= 1;
            armortail[a] += 1;
        }
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
    public void checkCollisionOfSnakeHeadAndarmorButton() {
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
        Thread thread = new Thread(() -> {
            while (true) {

                if (finish){
                    break;
                }

                if (needToCreateApple){
                    Apple();
                }

                if (currentStage >= 4){
                    armorhelp();
                    checkCollisionOfSnakeHeadAndarmorButton();
                }

                checkCollisionOfSnakeHeadAndApple();

                if (wPressed >= 2 && sPressed >= 2 && aPressed >= 2 && dPressed >= 2 && startStage2){
                    currentStage = 2;
                    Stage2();
                    startStage2 = false;
                }

                if (visibleSnake == 4 && startStage3){
                    speed = speed / 2;
                    for (int i = 0; i < snakeLength; i++){
                        speedOfSnakeX[i] = speedOfSnakeX[i] / 2;
                        speedOfSnakeY[i] = speedOfSnakeY[i] / 2;
                    }
                    currentStage = 3;
                    Stage3();
                    startStage3 = false;
                }

                if (visibleSnake == 1 && currentStage == 3 && startStage4){
                    currentStage = 4;
                    speed = speed * 2;
                    for (int i = 0; i < snakeLength; i++){
                        speedOfSnakeX[i] = speedOfSnakeX[i] * 2;
                        speedOfSnakeY[i] = speedOfSnakeY[i] * 2;
                    }
                    Stage4();
                    startStage4 = false;
                }

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
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @FXML
    void ToMenuButtonClicked(MouseEvent event) throws IOException {
        finish = true;
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

