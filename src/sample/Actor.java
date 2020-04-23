package sample;

import javafx.scene.canvas.GraphicsContext;

public  class Actor {
    double x,y,dx,dy,gravity;
    boolean up , down, left , right;
    Actor(){
        x=800/2;y=400/2;
        dx=dy=0;
        up=down=left=right=false;
    }
    public void update(){


    }
    public void draw(GraphicsContext gc){
        gc.fillRect(x,y,30,30);
    }

}
