package com.example.lib;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.lang.*;

public class Game extends IController{

    private Display display;
    private User user;

    private ArrayList<Platform> platforms;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private Player player;


    private final int WIDTH = 480;
    private final int HEIGHT = 640;
    private final int PLATFORM_SPAWN_RATE =(int)(.8 * 60);
    private final double ACCEL = .16;


    private void initGame(User u)  {
        user = u;
        display = new Display();
        player = new Player();
        platforms = new ArrayList<>();
        bullets = new ArrayList<>();

        display.getCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY()));
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}

            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
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
            }

            platformTimer += delta;

            if (platformTimer > PLATFORM_SPAWN_RATE)    {
                platformTimer %= PLATFORM_SPAWN_RATE;
                platforms.add(spawnPlatform());
            }

            update(delta);
            if (checkLost(player))  {   //ends game
                return;
            }
            display.clearCanvas();
            display.draw(player);
            display.draw(platforms);
            display.draw(bullets);
            display.draw(user);
            display.paintCanvas();

            try {Thread.sleep( (lastLoopTime-System.nanoTime() + TARGET/1000000));}
            catch(Exception e) {}
        }
    }

    private void update(double delta) {
        //ALL TIME SENSITIVE STUFF MUST MULTIPLY BY DELTA

        Point mouse = display.getMouse();
        player.setVelocity(player.getXVelocity(), calcGravity(delta, player.getYVelocity()));
        player.setLocation((int) mouse.getX(), player.getY() + player.getYVelocity() * delta);

        move(delta, bullets);
        move(delta, platforms);
        Iterator<Platform> i = platforms.iterator();
        while (i.hasNext()) {
            Platform plat = i.next();
            //increases velocity when collides (player jumps)
            if (plat.checkCollision(player)) {
                player.setVelocity(player.getXVelocity(), -5);

                if(!plat.getJumpedOn()){
                    plat.setJumpedOn(true);
                    user.incrementScore();
                }
            }
            else if (!plat.checkCollision(player) && plat.getJumpedOn()){
                plat.setJumpedOn(false);
                plat.setJumpLimit(plat.getJumpLimit()-1);
            }
            else if(plat.getJumpLimit()==0) {
                i.remove(); // do something w/ jumpLimit maybe
            }
        }
    }

    private boolean checkLost(Player p) {
        return p.getY() > HEIGHT;
    }

    private void move(double delta, ArrayList<? extends Collideable> list)   {
        Iterator<? extends Collideable> i = list.iterator();
        while(i.hasNext()) {
            Collideable ent = i.next();
            double x = ent.getX() + ent.getXVelocity() * delta;
            double y = ent.getY() + ent.getYVelocity() * delta;
            ent.setLocation(x,y);

            //Despawns after leaving the screen
            if (!Collideable.checkBounds(ent, WIDTH, HEIGHT))   {
                i.remove();
            }
        }
    }

    private double calcGravity(double delta, double yVelocity)  {
        yVelocity += ACCEL * delta;
        return yVelocity;
    }



    private Platform spawnPlatform()    {
        int a = (int) ((Math.random() * WIDTH *.95) + 1);
        return new Platform(a , 10);
    }
    public static void main(String args[]) {
        Game game = new Game();

        User gamePlayer = new User("PlaceHolder Name");
        game.initGame(gamePlayer);
        game.gameloop();
        System.out.println(gamePlayer.getScore());
    }


}