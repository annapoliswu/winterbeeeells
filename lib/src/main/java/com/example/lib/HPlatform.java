package com.example.lib;

/**
 * Created by Anna on 5/1/2018.
 */

public class HPlatform extends Platform {
    public HPlatform(double a, double b)   {
        super(2,2);
        setLocation(a, b);
        setSize((int)(Math.random()*30 + 50), 20);
        setJumpLimit(5);
    }
    public HPlatform(double a, double b, double v)   {
        super(2,2);
        setLocation(a, b);
        setSize((int)(Math.random()*30 + 50), 20);
        setVelocity(v, this.getYVelocity());
        setJumpLimit(5);
    }
    public void move(double delta){
        super.move(delta);
        if(!Collideable.checkWidthBound(this, Display.WINDOW_WIDTH)){
            this.setVelocity(-1* this.getXVelocity(), this.getYVelocity());
        }
        //
    }





}


