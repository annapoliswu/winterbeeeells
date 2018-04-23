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
    private double x;
    private double y;
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

    public void setLocation(double a, double b) {
        this.x = a;
        this.y = b;
    }

    public double getX() {
        return this.x;
    }

    public double getY()   {
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

    public boolean checkCollision(Collideable e){
        double x = this.getX();
        double y = this.getY();
        double x2 = this.getX() + this.getWidth();
        double y2 = this.getY() + this.getHeight();

        if (e.getX() > x && e.getX() < x2)  {
            if (e.getY() > y && e.getY() < y2)  {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBounds(Collideable e, int WIDTH, int HEIGHT)   {
        if (e.getX() < 0 || e.getX() > WIDTH)   {
            return false;
        }
        if (e.getY() < 0 || e.getY() > HEIGHT)  {
            return false;
        }
        return true;
    }
}
