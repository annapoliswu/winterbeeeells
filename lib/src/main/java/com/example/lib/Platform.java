package com.example.lib;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Anna on 4/15/2018.
 */

public class Platform extends Collideable {

    private boolean jumpedOn;
    private int jumpLimit;

    public Platform(double a, double b) {
        super(2);
        setLocation(a, b);
        setSize((int) (Math.random() * 30 + 20), 20);
        setVelocity(0, FALL_VELOCITY);
        jumpedOn = false;
        jumpLimit = 3;
    }

    public Platform(double a, double b, int width, int height) {
        this(a,b);
        setSize(width, height);
    }

    public boolean getJumpedOn() {
        return jumpedOn;
    }

    public void setJumpedOn(boolean a) {
        jumpedOn = a;
    }

    public int getJumpLimit() {
        return jumpLimit;
    }

    public void setJumpLimit(int limit) {
        jumpLimit = limit;
    }

    //jumpLimit changes only work if player smaller than platform?? -check collision func later
    public void onCollision(Player player) {
        if (this.checkCollision(player)) {
            player.setVelocity(player.getXVelocity(), -5);
            this.setJumpedOn(true);
        } else if (!this.checkCollision(player) && this.getJumpedOn()){
            this.setJumpedOn(false);
            this.setJumpLimit(this.getJumpLimit()-1);
        } else if (this.jumpLimit == 1) {
            this.setID(0);
        }
    }



}
