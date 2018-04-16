package com.example.lib;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Created by Anna on 4/15/2018.
 */

public class Player extends Collideable{
    private String name;
    private static int score;

    public void setScore(int scoreValue){
        score = scoreValue;
    }

    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }

    public Player(){
        name = "none";
        score = 0;

        setXVelocity(10);
        setYVelocity(-10);

    }

    public Player(String nameValue){
        this();
        name = nameValue;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(200, 150, 400, 250); //(x posn, y posn, width, height)

    }



}
