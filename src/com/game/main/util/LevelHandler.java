/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.main.util;

import com.game.gfx.BufferedImageLoader;
import com.game.object.Block;
import com.game.object.Enemy;
import com.game.object.Pipe;
import com.game.object.Player;
import com.game.object.util.Handler;
import com.game.object.util.KeyInput;
import java.awt.image.BufferedImage;

/**
 *
 * @author LeeRise
 */
public class LevelHandler {
    
    private final String PARENT_FOLDER = "/level";
    
    private BufferedImageLoader loader;
    private BufferedImage levelTiles;
    private Handler handler;
    
    public int spawn_x, spawn_y;
    
    public LevelHandler(Handler handler){
        this.handler = handler;
        loader = new BufferedImageLoader();
        
    }
    
    public void start(){
        setLevel(PARENT_FOLDER + "/1_1.png");
        loadCharacters(PARENT_FOLDER + "/1_1_c.png");   
    }
    
    public void setLevel(String levelTilesPath){
        this.levelTiles = loader.loadImage(levelTilesPath); 
        
        int width = levelTiles.getWidth();
        int height = levelTiles.getHeight();
             
        for (int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                
                int pixel = levelTiles.getRGB(i, j);                // return (4 bytes = 32 bits)
                                                                    // 0000 0000, 0000 0000, 0000 0000, 0000 0000
                                                                    //    alpha       red        green      blue
                                                                    // 0xff = 1111 1111
                                                                    
                // breakable block = 255 127 39
                // unbreakable block = 0 0 0
                // item = 77 38 12
                // Pipe = 181 230 29
                // Enterable Pipe = 153 217 234
                // finish line = 34 177 76
                // void = 255 255 255

                
                int red     = (pixel>>16) & 0xff;
                int green   = (pixel>>8 ) & 0xff;
                int blue    = (pixel    ) & 0xff;
                
                if (red == 255 && blue == 255  && green == 255) continue;
                
                if (red == blue && blue == green && red == 0){
                    handler.addObject(new Block(i*16, j*16, 16, 16, 0, 3));

                }
                
                else if (red == 181 && green == 230 && blue == 29){
                    handler.addObject(new Pipe(i*16, j*16, 32, 16, 1, 3, false));
                }
                
                else if (red == 153 && green == 217 && blue == 234){
                    handler.addObject(new Pipe(i*16, j*16, 32, 16, 0, 3, true));
                }
                
                else if (red == 255 && green == 127 && blue == 39){
                    handler.addObject(new Block(i*16, j*16, 16, 16, 1, 3));

                }
                
            }
        }
    }
    
    private void loadCharacters(String levelCharactersPath){
        this.levelTiles = loader.loadImage(levelCharactersPath); 
        
        int width = levelTiles.getWidth();
        int height = levelTiles.getHeight();
        
        for (int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                
                int pixel = levelTiles.getRGB(i, j);            
                
                // Spawn point = 237 28 36
                // Goomba spawn = 185 122 87
                
                int red     = (pixel>>16) & 0xff;
                int green   = (pixel>>8 ) & 0xff;
                int blue    = (pixel    ) & 0xff;
                
                if (red == 237 && green == 28 && blue == 36){
                    spawn_x = i;
                    spawn_y = j;
                }
                else if (red == 185 && green == 122 && blue == 87){
                    handler.addObject(new Enemy(i*16, j*16, 3, handler));
                }
//                
                
            }
        }
    }
}


