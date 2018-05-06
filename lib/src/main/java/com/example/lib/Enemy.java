package com.example.lib;

public class Enemy extends Collideable {
    private int hp;

    public Enemy(double a, double b) {
        super(2);
        setLocation(a, b);
        setSize(40, 40);
        setVelocity(0,2.5);
        hp = 1;
        setID(0);
    }

    public int getHP(){
        return hp;
    }
    public void setHP(int newHP){
        hp = newHP;
    }
}
