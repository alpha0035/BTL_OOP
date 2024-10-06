/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author LeeRise
 */
public class Animation {
    
    private int speed;
    private int frame;
    
    private int index = 0;
    private int count = 0;
    
    private BufferedImage[] images;
    private BufferedImage current_image;
    
    public Animation(int speed, BufferedImage... args){
        this.speed = speed;
        images = new BufferedImage[args.length];
        
        for(int i=0; i<args.length; i++){
            images[i] = args[i];
        }
        
        frame = args.length;
    }
    
    public void runAnimation(){
        index++;
        if (index > speed){
            index = 0;
            nextFrame();
        }
    }
    
    private void nextFrame(){
        if (count == 0) count ++;                 // Nhỡ để animation walk ở ô 3 nên phải thêm :))
        current_image = images[count];
        count++;
        
        if(count >= frame) count = 0;
    }
    
    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        g.drawImage(current_image, x, y, scaleX, scaleY, null);
    }
}
