package com.example.lib;

public class Enemy extends Collideable {
    private int hp;

    public Enemy(double a, double b) {
        super(2);
        setLocation(a, b);
        setSize(40, 40);
        setVelocity(2.5,0);
        hp = 1;
        setID(0);
    }

    public int getHP(){
        return hp;
    }
    public void setHP(int newHP){
        hp = newHP;
    }

    //bounces back when meeting edge of screen
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
