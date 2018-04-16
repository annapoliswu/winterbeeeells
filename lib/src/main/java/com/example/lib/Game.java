package com.example.lib;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class Game {

    Display display;
    IController controller;

    Platform[] platforms;
    Enemy[] enemies;
    Player player;


    public void initGame()  {

        display = new Display();
        controller = new IController();
        player = new Player();
    }

    public void gameloop()  {
        long lastLoopTime = System.nanoTime();
        final int FPS = 60;
        final long TARGET = 1000000000 / FPS;
        boolean gameRunning = true;
        long lastFpsTime = 0;
        int fps = 0;

        while(gameRunning)  {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)TARGET);
            lastFpsTime += updateLength;
            fps ++;

            if (lastFpsTime >= 1000000000)  {
                lastFpsTime = 0;
                fps = 0;
            }
            update(delta);
            display.draw(player);

            try {Thread.sleep( (lastLoopTime-System.nanoTime() + TARGET/1000000));}
            catch(Exception e) {}
        }
    }

    public void update(double Delta)   {
        //ALL TIME SENSITIVE STUFF MUST MULTIPLY BY DELTA
        Point mouse = display.getMouse();
        player.setLocation((int)mouse.getX(), 450);

    }

    public void run() {
        display.draw(player);

        Point mouse = display.getMouse();
        player.setLocation((int)mouse.getX(), 450);

    }

    public static void main(String args[]) {
        Game game = new Game();
        game.initGame();
        game.gameloop();
    }


}