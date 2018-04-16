package com.example.lib;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Anna on 4/15/2018.
 */

public abstract class Collideable {

    final public static int PLAYER_ID = 1;

    private int xVelocity;
    private int yVelocity;
    private int x;
    private int y;
    private int width;
    private int height;
    private int id;

    public Collideable(int a)    {
        this.id = a;
    }

    public int getID() {
        return this.id;
    }
    public void setSize(int a,int b)    {
        this.width = a;
        this.height = b;
    }

    public int getWidth()   {
        return this.width;
    }

    public int getHeight()  {
        return this.height;
    }

    public void setLocation(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public int getX() {
        return this.x;
    }

    public int getY()   {
        return this.y;
    }
    public void setVelocity(int a, int b) {
        this.xVelocity = a;
        this.yVelocity = b;
    }

    public int getXVelocity(){
        return xVelocity;
    }
    public int getYVelocity(){
        return yVelocity;
    }

    public boolean hasCollided(){
        return false;
    }

}
