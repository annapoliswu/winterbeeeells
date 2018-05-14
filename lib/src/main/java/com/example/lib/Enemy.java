package com.example.lib;

public abstract class Enemy extends Collideable {
    private int hp;
    private int points;

    public Enemy(double a, double b) {
        super(0);
        setLocation(a, b);
        setPoints(1);
    }

    public int getHP(){
        return hp;
    }
    public void setHP(int newHP){
        hp = newHP;
    }
    public int getPoints(){
        return points;
    }
    public void setPoints(int pointValue){
        points = pointValue;
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
