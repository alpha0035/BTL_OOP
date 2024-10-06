/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Cần fix thêm collision

package com.game.object;

import com.game.gfx.Animation;
import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author LeeRise
 */
public class Enemy extends GameObject {
    private static final float WIDTH    = 16;
    private static final float HEIGHT   = 16;                                   // Hitboxes of Character :D
    
    private BufferedImage[] curr_sprite;
    private Animation enemyWalk, currAnimation;
    
    private Handler handler;
    
    private boolean forward = true;
    
    private int health = 1;
    private Texture tex;
    private BufferedImage[] sprite;
    
    public Enemy(float x, float y, int scale, Handler handler){
        super(x, y-5, ObjectId.Enemy, WIDTH, HEIGHT, scale, true);
        this.handler = handler;
        setGtX(5);
        tex = Game.getTexture();
        sprite = tex.getEnemy();
        
        enemyWalk = new Animation(5, sprite[1], sprite[2], sprite[3]);
        
        curr_sprite = sprite;
        currAnimation = enemyWalk;
        
        
    }

 
    @Override
    public void tick() {
       collision();
       System.out.println(getX() + " " + getY() + " " + getGtX() + " " + getGtY());
       setX(getGtX() + getX());                                                 // Change GtX for faster/slower speed
       setY(getGtY() + getY());                                                 // Change GtX for faster/slower jump
       Gravity_Is_Real();                                                      
       currAnimation.runAnimation();
       
    }

   
    @Override
    public void render(Graphics g) {
    
    /*    
        g.setColor(Color.YELLOW);
        g.fillRect((int) getX(), (int) getY(), (int) WIDTH, (int) HEIGHT);
        showBound(g);
    */
        if (getGtX() > 0){
            currAnimation.drawAnimation(g, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
            forward = true;
        }
        else if (getGtX() < 0){
            currAnimation.drawAnimation(g, (int) getX(), (int) getY(), (int) -getWidth(), (int) getHeight());
            forward = false;
        }
        else{
            g.drawImage(curr_sprite[0], (int) getX(), (int) getY(), (int)getWidth(), (int)getHeight(), null);
            forward = true;
        }
        
        
        
    }
    
    //Note: Nó vẽ hitbox từ trái -> phải :D
  
    @Override
    public Rectangle getBound() {
        return new Rectangle((int) (getX() + getWidth()/4),          // First Quarter X dimension
                                (int) (getY() + getHeight()/2),                     // Half of height
                                    (int) (getWidth()/2),                           // Width of hitbox
                                        (int) (getHeight()/2));                     // Height of hitbox
    }
    
    public Rectangle getBoundTop(){
        return new Rectangle((int) (getX() + getWidth()/4),          // First Quarter X dimension
                                (int) (getY()),                                      // Top of the hitbox
                                    (int) (getWidth()/2),                           // Width of hitbox
                                        (int) (getHeight()/2));    
    }
    
    public Rectangle getBoundRight(){                                               // Slim&Tall Rect on the RIGHT
        return new Rectangle((int) (getX() + getWidth() - 5),          
                                (int) (getY() + 5),                    
                                       5,                               
                                        (int) (getHeight()-10));                    // 5 space at top & bottom
    }
    
    public Rectangle getBoundLeft(){                                                // Slim&Tall Rect on the LEFT
        return new Rectangle((int) (getX()),          
                                (int) (getY() + 5),                    
                                       5,                               
                                        (int) (getHeight()-10));                    // 5 space at top & bottom
    }
    
    private void showBound(Graphics g){                                         // Show hitboxes :D
        Graphics2D g2d = (Graphics2D) g;
        
        g.setColor(Color.RED);
        g2d.draw(getBound());
        g2d.draw(getBoundTop());
        g2d.draw(getBoundRight());
        g2d.draw(getBoundLeft());
        
        
    }
    
    
    private void collision(){
        for(int i=0; i < handler.getGameObject().size(); i++){
            GameObject temp = handler.getGameObject().get(i);                   // temp = Object hiện tại
            
            if (temp.getId() == ObjectId.Block
                    || temp.getId() == ObjectId.Pipe){
                
                if (getBound().intersects(temp.getBound())){                    // Check trên mặt đất                  
                   setY(temp.getY() - getHeight());
                   setGtY(0);
                }   
                else if (getBoundLeft().intersects(temp.getBound())){                // check chặn trái
               //     setX(temp.getX() + temp.getWidth());
                    setGtX(5);
                }
                else if (getBoundRight().intersects(temp.getBound())){              // check chặn phải
                    setGtX(-5);
                  //  setX(temp.getX() - 5);
                }
             
            }
                    
        }
    }

   
}
