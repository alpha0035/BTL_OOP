/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author LeeRise
 */
// Hệ tọa độ Oxy có: Ox: trái -> phải
//                   Oy: trên -> dưới 
// Extend KeyAdapter => KeyInput được đưa vào KeyEvent Listener <=> Hoạt động khi 1 phím được bấm :D

public class KeyInput extends KeyAdapter {
    private boolean[] keyDown = new boolean[5];                                 // check ArrowKey State
    private Handler handler;
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_ESCAPE){                                         // Escape
            System.exit(0);
    }
     //                                         CONTROLS
        if (key == KeyEvent.VK_W){                                              // JUMP
            if (!handler.getPlayer().Jumped()){
                handler.getPlayer().setGtY(-15);
                keyDown[0] = true;
                handler.getPlayer().setJump(true);                              // Turn off for Infinite Jump :D
            }
        }
        if (key == KeyEvent.VK_A){                                              // LEFT
            handler.getPlayer().setGtX(-8);
            keyDown[1] = true;
        }
        if (key == KeyEvent.VK_D){                                              // RIGHT
            handler.getPlayer().setGtX(8);
            keyDown[2] = true;
        }
        
        
//        if (key == KeyEvent.VK_S){                                              // DOWN                                                         
//            if (!handler.getPlayer().Jumped()){
//                handler.getPlayer().setGtY(15);
//                keyDown[0] = true;
//                handler.getPlayer().setJump(true);
//            }
//        }
        
    }
        
        @Override
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_W){
                keyDown[0] = false;
     
            }
            
            if (key == KeyEvent.VK_A){
                keyDown[1] = false;
            }
            
            if (key == KeyEvent.VK_D){
                keyDown[2] = false;
            }
            
            if (!keyDown[1] && !keyDown[2]){
                handler.getPlayer().setGtX(0);
            }
    }
    
    
}
