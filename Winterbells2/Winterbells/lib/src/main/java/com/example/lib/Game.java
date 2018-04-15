package com.example.lib;
import java.awt.*;
import javax.swing.*;

public class Game {

    public static void main(String[] args){
        JFrame frame = new JFrame("teeest");

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Player p1 = new Player();


        frame.add(p1);  //adding object in frame
        p1.draw();

        //test, still getting 0's for getters
        System.out.print(p1.getWidth() + "   " + p1.getX());
    }
}
