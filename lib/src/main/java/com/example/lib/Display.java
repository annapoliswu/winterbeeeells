package com.example.lib;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import sun.applet.Main;


/**
 * Created by DoDo on 4/16/18.
 */

public class Display{
    JFrame frame;
    JPanel panel;
    Canvas canvas;
    BufferStrategy bs;
    public static int WINDOW_WIDTH = 480;
    public static int WINDOW_HEIGHT = 680;

    Color[] color = {Color.RED, Color.BLUE, new Color(100,165,150), Color.BLACK , Color.MAGENTA};

    public Display(){

        frame = new JFrame();

        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        panel.add(canvas);
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void clearCanvas() {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0,0, WINDOW_WIDTH,WINDOW_HEIGHT);

    }

    public void draw(User u){
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, 18));
        g.drawString("SCORE: " + u.getScore(), 10, 20);
        g.dispose();

    }

    public void draw(Collideable a)  {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setColor(color[a.getID()]);

        Graphics2D b = (Graphics2D) bs.getDrawGraphics();
        b.setColor(Color.RED);

        if(a instanceof Enemy){
            g.fillOval((int)a.getX(), (int)a.getY(), a.getWidth(), a.getHeight());
            g.dispose();
        } else if (a instanceof HPlatform){
            g.fillRect((int) a.getX(), (int) a.getY(), a.getWidth(), a.getHeight()/2);
            b.fillRect((int) a.getX(), (int) a.getY()+a.getHeight()/2, a.getWidth(), a.getHeight()/2);
            g.dispose();
            b.dispose();
        } else {
            g.fillRect((int) a.getX(), (int) a.getY(), a.getWidth(), a.getHeight());
            g.dispose();
        }
    }


    public void draw(ArrayList<? extends Collideable> e)  {
        for (Collideable ent : e)   {
            draw(ent);
        }
    }

    public void paintCanvas()   {
        bs.show();
    }

    public Point getMouse() {
        Player player = new Player();
        int pWidth = player.getWidth();
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        Point screen = canvas.getLocationOnScreen();
        int cWidth = canvas.getWidth();
        int cHeight = canvas.getHeight();
        int x = (int)(mouse.getX() - screen.getX() - (.5 * pWidth) );
        int y = (int)(mouse.getY() - screen.getY() - (.5 * pWidth) );

        //sets in bounds of canvas
        if (x <= 0)    {
            x = 0;
        } else if (x >= cWidth - pWidth) {
            x = cWidth - pWidth;
        }
        if (y <= 0) {
            y = 0;
        }
        else if (y >= cHeight)    {
            y = cHeight;
        }

        return new Point(x,y);
    }


}
