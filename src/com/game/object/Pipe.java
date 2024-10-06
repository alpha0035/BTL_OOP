/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.object;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author LeeRise
 */
public class Pipe extends GameObject{
    
    private Texture tex = Game.getTexture();
    private int index;
    private BufferedImage[] sprite;
    
    private boolean enterable;
    
    public Pipe(int x, int y, int width, int height, int index, int scale, boolean enterable){
        super(x, y, ObjectId.Pipe, width, height, scale, true);
        this.enterable = enterable;
        this.index = index;
        sprite = tex.getPipe1();
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
    }

    @Override
    public Rectangle getBound() {
          return new Rectangle( (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}
