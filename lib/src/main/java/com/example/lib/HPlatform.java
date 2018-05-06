package com.example.lib;

/**
 * Created by Anna on 5/1/2018.
 */

public class HPlatform extends Platform {
    public HPlatform(double a, double b)   {
        super(2,2);
        setLocation(a, b);
        setSize((int)(Math.random()*30 + 50), 20);
        setVelocity(2.5, 2.5);
        setJumpLimit(2);
        setID(3);
    }
    public HPlatform(double a, double b, double v)   {
        super(2,2);
        setLocation(a, b);
        setSize((int)(Math.random()*30 + 50), 20);
        setVelocity(v, 2.5);
        setJumpLimit(2);
        setID(3);
    }
}


