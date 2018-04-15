package com.example.lib;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Anna on 4/15/2018.
 */

public abstract class Collideable extends JComponent {

    private int xVelocity;
    private int yVelocity;

    public void setXVelocity(int xVValue){
        xVelocity = xVValue;
    }
    public void setYVelocity(int yVValue){
        yVelocity = yVValue;
    }

    public int getXVelocity(){
        return xVelocity;
    }
    public int getYVelocity(){
        return yVelocity;
    }

    /*
    //JComponent already has all this,except the setters
    //Can't get location / dimensions until rendered with repaint()

    private int width;
    private int height;
    private int x;
    private int y;

    public void setWidth(int widthValue){
        width = widthValue;
    }
    public void setHeight(int heightValue){
        height = heightValue;
    }
    public void setX(int xValue){
        x = xValue;
    }
    public void setY(int yValue){
        y = yValue;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

     */

    //checks if collideables overlap
    public boolean hasCollided(){
        return false;
    }


    public void draw(){
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }



}
