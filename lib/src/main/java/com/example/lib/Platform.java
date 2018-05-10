package com.example.lib;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Anna on 4/15/2018.
 */

public class Platform extends Collideable {

    private boolean jumpedOn;
    private int jumpLimit;

    public Platform(double a, double b)   {
        super(2);
        setLocation(a, b);
        setSize((int)(Math.random()*30 + 20), 20);
        setVelocity(0,FALL_VELOCITY);
        jumpedOn = false;
        jumpLimit = 2;
    }

    public Platform(double a, double b, int width, int height) {
        super(2);
        setLocation(a,b);
        setSize(width, height);
        setVelocity(0,FALL_VELOCITY);
        jumpedOn = false;
        jumpLimit = 2;
    }

    public boolean getJumpedOn(){
        return jumpedOn;
    }

    public void setJumpedOn(boolean a){
        jumpedOn = a;
    }
    public int getJumpLimit(){
        return jumpLimit;
    }

    public void setJumpLimit(int limit){
        jumpLimit = limit;
    }



}
