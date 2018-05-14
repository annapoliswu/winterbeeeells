package com.example.lib;

import java.util.ArrayList;

/**
 * Created by Anna on 5/14/2018.
 */

public class Target extends Enemy {

    public Target(double a, double b) {
        super(a,b);
        int pointValue = (int)(Math.random()* 8 + 2)*5;
        setPoints(pointValue);
        setSize(pointValue, pointValue);
        setVelocity(2.5,0);
        setHP(1);
    }




}
