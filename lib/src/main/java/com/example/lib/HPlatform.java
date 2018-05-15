package com.example.lib;

/**
 * Created by Anna on 5/1/2018.
 */


//Horizontal platform: moves horizontally, player can not go through bottom but can jump on top
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


    //movement behavior: if goes past edge of screen, flips horizontal velocity and sets location to edge of screen
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

    //player collision behavior: jump on top, fall on bottom
    @Override
    public void onCollision(Player player) {
       super.onCollision(player);
        if(this.checkTopCollision(player)) {
            player.setVelocity(player.getXVelocity(), -5);
            this.setJumpedOn(true);
        }
        else if(this.checkBottomCollision(player)) {
            player.setVelocity(player.getXVelocity(), 5);
        }
    }





}


