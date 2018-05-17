package com.example.lib;
/**
 * Created by Anna on 5/12/2018.
 */

//Sticky platform: players held stuck to platform until they move horizontally of, does not move
public class SPlatform extends Platform {

    private boolean wasOnPlat;

        public SPlatform(double a, double b)   {
            super(a,b);
            setSize((int)(Math.random()*30 + 50), 20);
            setJumpLimit(1);
            setID(4);
        }
        public SPlatform(double a, double b, double v)   {
            this(a,b);
            setVelocity(v, this.getYVelocity());
        }

    //player height set to platform top or bottom height when colliding with top/bottom
    @Override
        public void onCollision(Player player) {
            if (this.checkTopCollision(player)) {
                player.setLocation(player.getX(), this.getY() - this.getHeight());
                this.setJumpedOn(true);
            } else if (this.checkBottomCollision(player)) {
                player.setLocation(player.getX(), this.getY() + this.getHeight()-3);
                this.setJumpedOn(true);
            } else if (this.getJumpedOn()){
                this.setJumpedOn(false);
                this.setJumpLimit(this.getJumpLimit()-1);
                player.setVelocity(player.getXVelocity(), -5);
            }
        }

}
