package com.example.lib;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.lang.*;

public class Game{

    private Display display;
    private User user;

    private ArrayList<Platform> platforms;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private Player player;

    private Iterator<Platform> platformIterator;
    private Iterator<Bullet>bulletIterator;
    private Iterator<Enemy> enemyIterator;


    private final int WIDTH = Display.WINDOW_WIDTH;
    private final int HEIGHT = Display.WINDOW_HEIGHT;
    private final int PLATFORM_SPAWN_RATE =(int)(1 * 60);
    private final double ACCEL = .15;


    private void initGame(User u)  {
        user = u;
        display = new Display();
        player = new Player();
        platforms = new ArrayList<>();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies.add(new Enemy(250,20) );

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
               // platforms.add(new HPlatform(100, 20));
            }


            update(delta);
            if (checkLost(player))  {   //ends game
                return;
            }

            display.clearCanvas();
            display.draw(player);
            display.draw(platforms);
            display.draw(bullets);
            display.draw(enemies);
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
        move(delta, enemies);

        platformIterator = platforms.iterator();
        while (platformIterator.hasNext()) {
            Platform plat = platformIterator.next();

            if(!plat.getJumpedOn() && plat.checkCollision(player)) {
                if (!(plat instanceof HPlatform && plat.checkBottomCollision(player))) {
                       user.incrementScore();
                }
            }
            plat.onCollision(player);

            if(plat.getJumpLimit()==0) {
                platformIterator.remove(); // do something w/ jumpLimit maybe
            }
        }

        enemyIterator = enemies.iterator();
        bulletIterator = bullets.iterator();

        while (enemyIterator.hasNext()) {
            Enemy e = enemyIterator.next();

            if (player.checkCollision(e)){
                System.out.println("do somethingggg?");
            }

            while(bulletIterator.hasNext()){
                Bullet b = bulletIterator.next();
                if(e.checkCollision(b)){
                    e.setHP(e.getHP()-1);
                }
            }

           if(e.getHP()== 0){
               user.incrementScore(20);
               enemyIterator.remove();
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
            ent.move(delta);

            //Despawns after leaving the screen
            if (!Collideable.checkHeightBound(ent, HEIGHT))   {
                i.remove();
            }
        }
    }

    private double calcGravity(double delta, double yVelocity)  {
        yVelocity += ACCEL * delta;
        return yVelocity;
    }



    private Platform spawnPlatform()    {
        int init = (int)(Math.random() * 100);
        int a = (int) ((Math.random() * WIDTH * .95) + 1);
        if (init <= 60) {
            return new Platform(a, 10);
        } else if(init <= 70) {
            return new HPlatform(a, 10, Math.random() * 3 + 1);
        }else if ((init <= 80) ){
            return new HPlatform(a, 10, Math.random()* (-3) - 1 );
        } else
            return new SPlatform(a, 10);

    }



    public static void main(String args[]) {
        Game game = new Game();

        User gamePlayer = new User("PlaceHolder Name");
        game.initGame(gamePlayer);
        game.gameloop();
        System.out.println(gamePlayer.getScore());
    }


}