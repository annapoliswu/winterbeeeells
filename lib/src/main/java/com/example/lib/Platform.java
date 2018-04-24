package com.example.lib;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Anna on 4/15/2018.
 */

public class Platform extends Collideable {

    private boolean jumpedOn;

    public Platform(double a, double b)   {
        super(2);
        setLocation(a, b);
        setSize((int)(Math.random()*30 + 20), 20);
        setVelocity(0,2.5);
        jumpedOn = false;
    }

    public Platform(double a, double b, int width, int height) {
        super(2);
        setLocation(a,b);
        setSize(width, height);
        setVelocity(0,2.5);
        jumpedOn = false;
    }

    public boolean getJumpedOn(){
        return jumpedOn;
    }

    public void setJumpedOn(){
        jumpedOn = true;
    }

}
