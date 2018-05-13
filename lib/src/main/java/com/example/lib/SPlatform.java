package com.example.lib;
/**
 * Created by Anna on 5/12/2018.
 */

public class SPlatform extends Platform {

    private boolean wasOnPlat;

        public SPlatform(double a, double b)   {
            super(2,2);
            setLocation(a, b);
            setSize((int)(Math.random()*30 + 50), 20);
            setJumpLimit(1);
            setID(4);
        }
        public SPlatform(double a, double b, double v)   {
            super(2,2);
            setLocation(a, b);
            setSize((int)(Math.random()*30 + 50), 20);
            setVelocity(v, this.getYVelocity());
            setJumpLimit(1);
            setID(4);
        }


}
