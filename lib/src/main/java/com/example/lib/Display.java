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
/**
 * Created by DoDo on 4/16/18.
 */

public class Display{
    JFrame frame;
    JPanel panel;
    Canvas canvas;
    BufferStrategy bs;
    public static int WINDOWWIDTH = 480;
    public static int WINDOWHEIGHT = 640;
    IController controller;

    Color[] color = {Color.BLACK, Color.BLUE, Color.CYAN, Color.RED};

    public Display(){

        frame = new JFrame();

        frame.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0,0, WINDOWWIDTH, WINDOWHEIGHT);
        panel.add(canvas);
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

        canvas.addMouseMotionListener(controller);
        canvas.addMouseListener(controller);



    }

    public void draw(Collideable a)  {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0,0, WINDOWWIDTH,WINDOWWIDTH);

        g.setColor(color[a.getID()]);
        g.fillRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
        g.dispose();
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
