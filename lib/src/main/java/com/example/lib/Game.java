package com.example.lib;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class Game {

    Display display;

    ArrayList<Platform> platforms;
    ArrayList<Enemy> enemies;
    Player player;
    ArrayList<Bullet> bullets;

    final int WIDTH = 480;
    final int HEIGHT = 640;
    final int PLATFORM_SPAWN_RATE =(int)(1.5 * 60);


    public void initGame()  {

        display = new Display();
        player = new Player();
        platforms = new ArrayList<Platform>();
        bullets = new ArrayList<Bullet>();

        display.getCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                bullets.add(new Bullet(player.getX(), player.getY()));
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    public void gameloop()  {
        long lastLoopTime = System.nanoTime();
        final int FPS = 60;
        final long TARGET = 1000000000 / FPS;
        boolean gameRunning = true;
        long lastFpsTime = 0;
        int fps = 0;
        double platformTimer = 0;

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

            platformTimer += delta;
            if (platformTimer > PLATFORM_SPAWN_RATE)    {
                platformTimer %= PLATFORM_SPAWN_RATE;
                platforms.add(spawnPlatform());
            }


            update(delta);
            display.clearCanvas();
            display.draw(player);
            display.draw(platforms);
            display.draw(bullets);
            display.paintCanvas();

            try {Thread.sleep( (lastLoopTime-System.nanoTime() + TARGET/1000000));}
            catch(Exception e) {}
        }
    }

    public void update(double delta)   {
        //ALL TIME SENSITIVE STUFF MUST MULTIPLY BY DELTA
        Point mouse = display.getMouse();
        player.setLocation((int)mouse.getX(), 450);
        move(delta, bullets);
        for (Collideable e : platforms) {
            if (player.checkCollision(e))   {
                //TODO
            }
        }

    }

    public void move(double delta, ArrayList<? extends Collideable> list)   {
        Iterator<? extends Collideable> i = list.iterator();
        while(i.hasNext()) {
            Collideable ent = i.next();
            double x = ent.getX() + ent.getXVelocity() * delta;
            double y = ent.getY() + ent.getYVelocity() * delta;
            ent.setLocation(x,y);

            if (!Collideable.checkBounds(ent, WIDTH, HEIGHT))   {
                i.remove();
            }
        }
    }

    private Platform spawnPlatform()    {
        int a = (int) ((Math.random() * WIDTH) + 1);
        return new Platform(a, 20);
    }
    public static void main(String args[]) {
        Game game = new Game();
        game.initGame();
        game.gameloop();
    }


}