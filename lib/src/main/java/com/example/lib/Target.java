package com.example.lib;

import java.util.ArrayList;

/**
 * Created by Anna on 5/14/2018.
 */

//Target: moves back/forth at top of screen, player can shoot for points
public class Target extends Enemy {

    //random size, points based on size
    public Target(double a, double b) {
        super(a,b);
        int pointValue = (int)(Math.random()* 8 + 2)*5;
        setPoints(pointValue);
        setSize(pointValue, pointValue);
        setVelocity(2.5,0);
        setHP(1);
        setID(5);
    }

    //bounces back when meeting edge of screen
    @Override
    public void move(double delta){
        super.move(delta);
        if(!Collideable.checkWidthBound(this, Display.WINDOW_WIDTH)) {

            if (this.getXVelocity() > 0) {
                this.setLocation(Display.WINDOW_WIDTH - this.getWidth(), this.getY());
            } else if (this.getXVelocity() < 0) {
                this.setLocation(0, this.getY());
            }
            this.setVelocity(-1 * this.getXVelocity(), this.getYVelocity());
        }
    }




}
