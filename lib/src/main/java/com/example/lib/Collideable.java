package com.example.lib;
import java.awt.*;
import javax.swing.*;


/**
 * Created by Anna on 4/15/2018.
 * Represents anything in game that can be drawn and can collide with another object
 */

//class for anything that can collide
public abstract class Collideable {

    final public static int PLAYER_ID = 1;
    protected final double FALL_VELOCITY = 2.5;

    private double xVelocity;
    private double yVelocity;
    private double x;
    private double y;
    private int width;
    private int height;
    private int id;

    /*
    * ID value to determine type of collideable to determine render color and game logic
    * */
    public Collideable(int a)    {
        this.id = a;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int i) {
        id = i;
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


    /*
   //Old collision check function
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
*/


    //true if top Collideable e collides with bottom of this
    public boolean checkBottomCollision(Collideable e){
        double leftx = this.getX();
        double rightx = this.getX() + this.getWidth();
        double topy = this.getY() - this.getHeight()/2;
        double boty = this.getY() + this.getHeight()/2;

        double leftx2 = e.getX();
        double rightx2 = e.getX() + e.getWidth();
        double topy2 = e.getY() - e.getHeight()/2;
        double boty2 = e.getY() + e.getHeight()/2;

        if ( !((leftx2 < leftx && rightx2 < leftx) || (rightx2 > rightx && leftx2 > rightx)) )  {
            if (topy2 < boty &&  topy2 > topy) {
                return true;
            }
        }
        return false;
    }

    //true if bottom of Collideable e collides with top of this
    public boolean checkTopCollision(Collideable e){
        double leftx = this.getX();
        double rightx = this.getX() + this.getWidth();
        double topy = this.getY() - this.getHeight()/2;
        double boty = this.getY() + this.getHeight()/2;

        double leftx2 = e.getX();
        double rightx2 = e.getX() + e.getWidth();
        double topy2 = e.getY() - e.getHeight()/2;
        double boty2 = e.getY() + e.getHeight()/2;

        if ( !((leftx2 < leftx && rightx2 < leftx) || (rightx2 > rightx && leftx2 > rightx)) )  {
            if (boty2 > topy &&  boty2 < boty  )  {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision(Collideable e){
        return this.checkBottomCollision(e) || this.checkTopCollision(e);
    }


    public static boolean checkHeightBound(Collideable e, int HEIGHT)   {
        if (e.getY() < 0 || e.getY() > HEIGHT)  {
            return false;
        }
        return true;
    }

    public static boolean checkWidthBound(Collideable e, int WIDTH)   {
        if (e.getX() < 0 || (e.getX() + e.getWidth() ) > WIDTH)  {
            return false;
        }
        return true;
    }

    //moves Collideable based on velocities
    public void move(double delta){
        double x = this.getX() + this.getXVelocity() * delta;
        double y = this.getY() + this.getYVelocity() * delta;
        this.setLocation(x,y);
    }



}





