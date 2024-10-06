/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.gfx;

import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.Player;

/**
 *
 * @author LeeRise
 */
public class Camera {
    public int x;
    public int y;
    
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    
    public void tick(GameObject player){
        setX((int) (-player.getX() +  Game.getWindowWidth()/2));
      //  setY((int) (-player.getY() +  Game.getWindowHeight()/2));
        
    }
    
}
