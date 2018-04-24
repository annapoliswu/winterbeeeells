package com.example.lib;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Anna on 4/15/2018.
 */

public class Platform extends Collideable {

    public Platform(double a, double b)   {
        super(2);
        setLocation(a, b);
        setSize(15,15);
        setVelocity(0,5f);
    }
}
