/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.object;

import com.game.object.util.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author LeeRise
 */
public abstract class GameObject {                                              // Khai báo là lớp trừu tượng :D
    private float x;
    private float y;                                                            // Vi tri hien tai
    private ObjectId id;
    private float gtX, gtY;                                                      // Gia toc hien tai theo Ox, Oy
    private float width, height;
    private int scale;     
    public boolean solid;
    
    public GameObject(float x, float y, ObjectId id, float width, float height, int scale, boolean solid){
        this.x      = x*scale;
        this.y      = y*scale;
        this.id     = id;
        this.width  = width*scale;
        this.height = height*scale;
        this.scale  = scale;
        this.solid = solid;
    }
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBound();
    
    public void Gravity_Is_Real(){
        gtY += 0.5f;
    }
                                                           
    //              SET
    
    public void setX(float x){
        this.x = x;
    }   
    public void setY(float y){
        this.y = y;
    } 
    public void setId(ObjectId id){
        this.id = id;
    } 
    public void setGtX(float gtX){
        this.gtX = gtX;
    } 
    public void setGtY(float gtY){
        this.gtY = gtY;
    }
    public void setWidth(float width){
        this.width = width*scale;
    }
    public void setHeight(float height){
        this.height = height*scale;
    }
    public void setSolid(boolean solid){
        this.solid = solid;
    }
    
    //          GET
    
    public float getX(){
        return x;
    }   
    public float getY(){
        return y;
    }   
    public ObjectId getId(){
        return id;
    }   
    public float getGtX(){
        return gtX;
    } 
    public float getGtY(){
        return gtY;
    } 
    public float getWidth(){
        return width;
    } 
    public float getHeight(){
        return height;
    } 
    public boolean isSolid(){
        return solid;
    }
    
    
}
