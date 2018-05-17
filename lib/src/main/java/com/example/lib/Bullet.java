package com.example.lib;

/**
 * Created by DoDo on 4/22/18.
 *
 * Represents a bullet object spawned from the player on click
 */

public class Bullet extends Collideable {

    /*
    * Takes the x and y coordinates where the bullet should be spawned from
    * Initializes the bullet with default parameters
     */
    public Bullet(double a, double b) {
        super(0);
        setLocation(a,b);
        setVelocity(0,-15);
        setSize(4,4);
    }
    /*
    * Overloaded constructers allows you to change the velocity of the bullet spawned
    * */
    public Bullet(double a, double b, double xv, double yv) {
        this(a,b);
        setVelocity(xv,yv);

    }

}
