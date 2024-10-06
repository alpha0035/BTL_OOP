/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game.gfx;

import com.game.main.Game;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author LeeRise
 */
public class Windows {                                                          // Create Window
    private JFrame frame;
    private Dimension size;
    
    public Windows(int width, int height, String title, Game game){
        size = new Dimension(width, height);
        frame = new JFrame(title);
        
        frame.setPreferredSize(size);
        frame.setMaximumSize(size);
        frame.setMinimumSize(size);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                   // X is the Exit Button
        frame.setResizable(false);                                              // Disable Size Change
                                                                                    // Change to true if you want the game to break :)))
        frame.setLocationRelativeTo(null);                                      // Location: Middle
        frame.add(game);                                                        // Add game to our Window
        frame.setVisible(true);
        frame.pack();
        
        
        
        
        
    }
    
}
