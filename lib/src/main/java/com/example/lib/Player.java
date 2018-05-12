package com.example.lib;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Created by Anna on 4/15/2018.
 */

public class Player extends Collideable{



    public Player(){
        super(Collideable.PLAYER_ID);
        this.setLocation(240,200);
        this.setVelocity(0,-10);
        this.setSize(40,40);
    }





}
