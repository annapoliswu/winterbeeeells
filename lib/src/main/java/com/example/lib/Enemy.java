package com.example.lib;

/*
* Represents an Enemy class in the game
*
* Unused in android port
* */
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



}
