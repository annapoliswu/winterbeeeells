package com.example.lib;
import java.awt.*;
import javax.swing.*;


/**
 * Created by Anna on 4/15/2018.
 */

public abstract class Collideable {

    final public static int PLAYER_ID = 1;

    private double xVelocity;
    private double yVelocity;
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

    public void setVelocity(double a, double b) {
        this.xVelocity = a;
        this.yVelocity = b;
    }

    public double getXVelocity(){
        return xVelocity;
    }
    public double getYVelocity(){
        return yVelocity;
    }

    public boolean checkCollision(Collideable e){
        double x = this.getX() - this.getWidth()/2 - e.getWidth()/2;
        double y = this.getY() - this.getHeight()/2 - e.getHeight()/2;
        double x2 = this.getX() + this.getWidth()/2 + e.getWidth()/2;
        double y2 = this.getY() + this.getHeight()/2 + e.getHeight()/2;

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
