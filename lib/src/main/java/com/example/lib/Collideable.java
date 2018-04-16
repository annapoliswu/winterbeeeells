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
