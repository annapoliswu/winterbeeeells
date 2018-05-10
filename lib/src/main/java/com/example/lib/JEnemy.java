package com.example.lib;

/**
 * Created by Anna on 5/9/2018.
 */

public class JEnemy extends Enemy {
    public JEnemy(double a, double b) {
        super(a,b);
        setVelocity(0, FALL_VELOCITY);
        setHP(1);
    }


}
