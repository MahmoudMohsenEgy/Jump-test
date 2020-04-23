package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEGIHT = 400;
    double x=WIDTH/2,y=HEGIHT/2,dx=0,dy=0,maxDY=5,gravity=1;
    double rectX = 30;
    double rectY = 30;
    boolean up, down,left,right,jumping;
    Image marioImage;
    Canvas canvas ;
    GraphicsContext gc;
    Scene scene;
    StackPane root ;
    AnimationTimer gameLoop;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new StackPane();

        initNodes(root.getChildren());
        initGameLoop();
        scene = new Scene(root,WIDTH,HEGIHT,Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Game");
        scene.setOnKeyPressed(e->{
            switch(e.getCode()){
                case W:dy=-3;jumping=true;  break;
                case S:dy=1;  break;
                case A:dx=-1;  break;
                case D:dx=1;  break;
            }
        });
        scene.setOnKeyReleased(e->{
            switch(e.getCode()){
                case W:dy=0;  break;
                case S:dy=0;  break;
                case A:dx=0;  break;
                case D:dx=0;  break;
            }
        });

        primaryStage.show();

        gameLoop.start();

    }
    public void initNodes(ObservableList<Node> rootPane){
        canvas = new Canvas(WIDTH,HEGIHT);
        gc = canvas.getGraphicsContext2D();
        marioImage = new Image("https://www.vhv.rs/dpng/d/50-508742_8-bit-mario-mushroom-png-transparent-png.png");


        rootPane.add(canvas);
    }
    public void initGameLoop(){
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                updateHero();
                draw(gc);
                System.out.println(x+" "+y);
            }
        }  ;
    }
    public void updateHero(){

        x+=dx;
        y+=dy;
        if(jumping){
            y+=gravity;
            if(y >= 200){
                jumping=false;
            }

        }



    }

    public void draw(GraphicsContext gc){
        gc.clearRect(0,0,WIDTH,HEGIHT);
        gc.setFill(Color.GREEN);
        gc.fill();
        gc.drawImage(marioImage,x,y,rectX,rectY);
        gc.setFill(Color.ORANGE);
        gc.fill();
        gc.fillRect(0,200+rectY,WIDTH,HEGIHT);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
