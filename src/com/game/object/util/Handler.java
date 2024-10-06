/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.object.util;

import com.game.object.GameObject;
import com.game.object.Player;
import java.awt.Graphics;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author LeeRise
 */
public class Handler {
    public List<GameObject> gameObjects;
    private Player player;
    
    public Handler(){
        gameObjects = new LinkedList<GameObject>();                             // Linked list <=> Ez to iterate
    }
    
    public void tick(){
        for (GameObject object: gameObjects){
            object.tick();
        }
    }
    
    public void render(Graphics g){
        for (GameObject object: gameObjects){
            object.render(g);
        }
    }
    
    public void addObject(GameObject object){
        gameObjects.add(object);
    }
    
    public void removeObject(GameObject object){
        gameObjects.remove(object);
    }
    
    public List<GameObject> getGameObject() {
        return gameObjects;
    }
    
    public int setPlayer(Player player){
        if (this.player != null) 
            return -1;
        
        
        addObject(player);
        this.player = player;
        return 0;
    }
    
    public int removePlayer(){
        if (this.player == null) 
            return -1;
        
        removeObject(player);
        this.player = null;
        return 0;
    }
    
    public Player getPlayer(){
        return player;
    }
}
