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
public class Player extends GameObject {
    private static final float WIDTH    = 16;
    private static final float HEIGHT   = 32;                                   // Hitboxes of Character :D
    
    private PlayerState state; 
    private BufferedImage[] curr_sprite;
    private Animation playerWalkL, playerWalkS, currAnimation;
    
    private Handler handler;
    
    private boolean jumping = false;
    private boolean forward = true;
    
    private int health = 2;
    private Texture tex;
    private BufferedImage[] spriteL, spriteS;
    
    
    public Player(float x, float y, int scale, Handler handler){
        super(x, y, ObjectId.Player, WIDTH, HEIGHT, scale, true);
        this.handler = handler;
        
        tex = Game.getTexture();
        spriteL = tex.getMarioL();
        spriteS = tex.getMarioS();
        
        playerWalkL = new Animation(5, spriteL[1], spriteL[2], spriteL[3]);
        playerWalkS = new Animation(5, spriteS[1], spriteS[2], spriteS[3]);
        
        state = PlayerState.Large;
        curr_sprite = spriteL;
        currAnimation = playerWalkL;
        
        
    }

 
    @Override
    public void tick() {
       collision();
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
        if (jumping){
            if (forward){
                g.drawImage(curr_sprite[6], (int) getX(), (int) getY(), (int)getWidth(), (int)getHeight(), null);
            }
            else{
                g.drawImage(curr_sprite[6], (int) (getX() + getWidth()), (int) getY(), (int)-getWidth(), (int)getHeight(), null);
            }
        }
        else if (getGtX() > 0){
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
    
    public boolean Jumped(){
        return jumping;
    }
    
    public void setJump(boolean hasJumped){
        jumping = hasJumped;
    }
    
    
    private void collision(){
        for(int i=0; i < handler.getGameObject().size(); i++){
            GameObject temp = handler.getGameObject().get(i);                   // temp = Object hiện tại
            
            if (temp.getId() == ObjectId.Block
                    || temp.getId() == ObjectId.Pipe){
                
                if (getBound().intersects(temp.getBound())){                    // Check trên mặt đất                  
                   setY(temp.getY() - getHeight());
                   setGtY(0);
                   jumping = false;
                }
                else if (getBoundTop().intersects(temp.getBound())){                 // check đập đầu
                    setY(temp.getY() + getHeight());
                    setGtY(0);
                    
                }
                else if (getBoundLeft().intersects(temp.getBound())){                // check chặn trái
                    setX(temp.getX() + temp.getWidth()+5);
                    setGtX(0);
                }
                else if (getBoundRight().intersects(temp.getBound())){              // check chặn phải
                    setGtX(0);
                    setX(temp.getX() - 35);
                }
             
            }
            
            else if (temp.getId() == ObjectId.Enemy){
                if (getBound().intersects(temp.getBound()) 
//                        && !getBoundLeft().intersects(temp.getBound())
//                            && !getBoundRight().intersects(temp.getBound())
//                                && !getBoundTop().intersects(temp.getBound())
                        ){                                 
                    setGtY(-5);
                   
                   jumping = false;
                   handler.removeObject(temp);
                }
               
            }
                    
        }
    }

   
}
