package com.example.lib;
import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class Game {
    public static int seconds = 0;

    public static void main(String[] args){
        JFrame frame = new JFrame("teeest");

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Player p1 = new Player();

        frame.add(p1);  //adding object in frame
        p1.draw();
        p1.setLocation(20,20);
        p1.setSize(10,10);
        p1.repaint();
        System.out.print(p1.getWidth() + "   " + p1.getX());
        IControl listener = new IControl();

        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //thread?

                seconds++;
                System.out.println(seconds);


            }
        };


    }
}
