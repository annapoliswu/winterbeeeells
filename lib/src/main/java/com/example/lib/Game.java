package com.example.lib;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.lang.*;

import static com.example.lib.Display.WINDOW_HEIGHT;
import static com.example.lib.Display.WINDOW_WIDTH;


public class Game extends IController{

    public static int SCORE = 0;

    Display display;
    ArrayList<Platform> platforms;
    ArrayList<Enemy> enemies;
    Player player;
    ArrayList<Bullet> bullets;

    final int WIDTH = 480;
    final int HEIGHT = 640;
    final int PLATFORM_SPAWN_RATE =(int)(1 * 60);
    final double ACCEL = .15;


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
                bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY()));
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
               // System.out.println();


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
        player.setVelocity(player.getXVelocity(), calcGravity(delta, player.getYVelocity()));
        player.setLocation((int)mouse.getX(), player.getY() + player.getYVelocity() * delta);

        move(delta, bullets);
        move(delta, platforms);
        for (Platform e : platforms) {
            //increases velocity when collides (player jumps)
            if (e.checkCollision(player)) {
                player.setVelocity(player.getXVelocity(), -5);

                if(e.getJumpedOn()== false){
                    e.setJumpedOn(true);
                    SCORE++;
                }
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

            //despawning when off screen
            if (!Collideable.checkBounds(ent, WIDTH, HEIGHT))   {
                i.remove();
            }
        }
    }

    private double calcGravity(double delta, double yVelocity)  {
        yVelocity += ACCEL * delta;
        return yVelocity;
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
        int a = (int) ((Math.random() * WIDTH *.95) + 1);
        return new Platform(a , 10);
    }
    public static void main(String args[]) {
        Game game = new Game();
        game.initGame();
        game.gameloop();
    }


}