/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.main;

import com.game.gfx.Camera;
import com.game.gfx.Texture;
import com.game.gfx.Windows;
import com.game.main.util.LevelHandler;
import com.game.object.Block;
import com.game.object.GameObject;
import com.game.object.Player;
import com.game.object.util.Handler;
import com.game.object.util.KeyInput;
import com.game.object.util.ObjectId;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LeeRise
 */
public class Game extends Canvas implements Runnable  {
    
    //                  CONSTANT
    private static final int MPS = 1000;                       // MPS = Milliseconds Per Second
    
    private static final int NPS = 1000000000;                 // NPS = Nanosecond Per Second
            
    private static final double NUM_TICKS = 120.0;               // TIME between EACH UPDATE to STATUS

    private static final String NAME = "Fake_Mario_Bros";
            
    private static final int WIN_WIDTH  = 960;
    private static final int WIN_HEIGHT = 720;
    private static final int SCALE = 4;
    
    //                  VARIABLE
    
    private boolean running;                                // Check if Game is running
    
    
    //                  COMPONENTS
    
    private Thread thread;
    private Handler handler;
    private Camera cam;
    private static Texture tex;
    private LevelHandler levelhandler;
    
    //                  MAIN
    
    public Game(){                                      //     Start Initialize new Game
        Initialize();
    }
    
    public static void main(String[] args){
        
        new Game();
        
      //  Player mario = new Player();        // Set new player  
    }

        
    private void Initialize(){  
        cam = new Camera();
        tex = new Texture();
        handler = new Handler();
        levelhandler = new LevelHandler(handler);
        levelhandler.start();
        
        this.addKeyListener(new KeyInput(handler));
        
        handler.setPlayer(new Player(levelhandler.spawn_x, levelhandler.spawn_y, 2, handler));
         
        new Windows(WIN_WIDTH, WIN_HEIGHT, NAME, this);
        
        Start_Game();
        
    }
    
    public synchronized void Start_Game(){
        if (running) return;
        
        thread = new Thread(this);
        thread.start();
        
        running = true;                                 // Confirm Game is running
        
    }
    
    public synchronized void Stop_Game(){
        
        try {
            thread.join();
            running = false;                                // Confirm Game stopped running
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void run() {
        
        long LastTime = System.nanoTime();
        double Amount_Of_Ticks = NUM_TICKS;
        double ns = NPS / Amount_Of_Ticks;    // Nano per tick
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int update = 0;
        
        while (running){
            long now = System.nanoTime();
            delta += (now - LastTime) / ns;
            LastTime = now;                                                // amount of time passed
            
            while (delta >=1){                                              
                tick();
                update++;
                delta--;
            }
            
            if (running) {                                                  // Confirm running=true before rendering
                render();  
                frames++;
            }                                            
            
            
//            if (System.currentTimeMillis() - timer > MPS){                     // Print FPS & TPS
//                timer += MPS;
//                System.out.println("FPS: " + frames + " TPS " + update);
//                update = 0;
//                frames = 0;
//            }
        }
        
        Stop_Game();
    }
    
    private void tick(){
        handler.tick();
        
        for (GameObject temp: handler.gameObjects){
            if (temp.getId() == ObjectId.Player){
                cam.tick(temp);
            }
        }
    }
    
    private void render(){
        BufferStrategy buf = this.getBufferStrategy();
        if (buf == null) {
            this.createBufferStrategy(3);                               // Create 3 frame every time
            return;
        }
        
     // Graphic Creation
     Graphics g = buf.getDrawGraphics();
     
     g.setColor(Color.BLACK);                                                 // pick colour
     g.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);                                   // Fill (0,0) -> (width, height)
     
     g.translate(cam.getX(), cam.getY());
     handler.render(g);
     
     g.dispose();
     buf.show();
    }
    
    public static int getWindowHeight(){
        return WIN_HEIGHT;
    }
    
    public static int getWindowWidth(){
        return WIN_WIDTH;
    }
    
   
    
    public static Texture getTexture(){
        return tex;
    }
    
}
