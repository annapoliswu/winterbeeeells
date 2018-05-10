package com.example.lib;

public class Enemy extends Collideable {
    private int hp;

    public Enemy(double a, double b) {
        super(2);
        setLocation(a, b);
        setSize(10, 10);
        setVelocity(0,0);
        setHP(1);
        setID(0);
    }

    public int getHP(){
        return hp;
    }
    public void setHP(int newHP){
        hp = newHP;
    }

    public void move(double delta){
        super.move(delta);
        if ( !Collideable.checkWidthBound(this)) {
            //changes velocity, doesn't change posn fast enough,so can still be outside bound flip-flopping velo?
            if (this.getXVelocity() > 0) {
                this.setLocation(Display.WINDOW_WIDTH - this.getWidth(), this.getY());
            } else if (this.getXVelocity() < 0) {
                this.setLocation(0, this.getY());
            }this.setVelocity(-1*this.getXVelocity(), this.getYVelocity());
        }
    }


}
