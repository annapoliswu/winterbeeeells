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

import static com.example.lib.Display.WINDOWHEIGHT;
import static com.example.lib.Display.WINDOWWIDTH;


public class Game extends IController{

    Display display;
    ArrayList<Platform> platforms;
    ArrayList<Enemy> enemies;
    Player player;
    ArrayList<Bullet> bullets;

    final int WIDTH = 480;
    final int HEIGHT = 640;
    final int PLATFORM_SPAWN_RATE =(int)(1.5 * 60);
    final int ACCEL = 5;

    static int fps = 0; //counts up in nanosec?, resets to 0 at 1 sec

    private void initGame()  {

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

    private void gameloop()  {
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
                System.out.println(platformTimer);

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

    private void update(double delta)   {
        //ALL TIME SENSITIVE STUFF MUST MULTIPLY BY DELTA

        Point mouse = display.getMouse();
        player.setLocation((int)mouse.getX(), 450);
        move(delta, bullets);
        move(delta, platforms);
        for (Collideable e : platforms) {
            if (player.checkCollision(e)) {
                //TODO
            }
        }

    }

    private void move(double delta, ArrayList<? extends Collideable> list)   {
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

    private double calcGravity(double delta, Collideable e)  {
        double y = e.getYVelocity();
        y += ACCEL * delta;
        return y;
    }
/*
        //should probably be in getMouse or display
        if(fps%50 == 0 && player.checkBounds(WINDOWWIDTH , WINDOWHEIGHT)){ // EVERY.05milisecond?
            player.setLocation((int)mouse.getX(), player.getY()+ 30);
        } else{
            player.setLocation((int)mouse.getX(), player.getY());
        }

        //System.out.println(seconds);

    }

*/
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