/*
 * Filename     : ID.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : class yang mengatur key input keyboard pada game
*/

//AKSES LIBRARY DAN PACKAGE
package View;
import View.Menu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import View.Game.STATE;

public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    Game game;
    int kecepatan=5;
    int jump=10;
    
    public KeyInput(Handler handler, Game game){
        this.game = game;
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(game.gameState == STATE.Game){
            for(int i = 0;i<handler.object.size();i++){
                Objects tempObject = handler.object.get(i);
                
                if(tempObject.getId() == ID.Player){
                    if((key == KeyEvent.VK_UP)){
                        if(!tempObject.jumping){
                            tempObject.jumping = true;
                            tempObject.gravity = 10.0;
                        } 
                    }

                    if(key == KeyEvent.VK_LEFT){
                        tempObject.setVel_x(-kecepatan);
                    }

                    if(key == KeyEvent.VK_RIGHT){
                        tempObject.setVel_x(+kecepatan);
                    }
                }
               
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
        
        if(key == KeyEvent.VK_SPACE){
            if(game.gameState == STATE.Game) {
                game.addData();
                game.gameState = STATE.GameOver;
            }else{
                new Menu().setVisible(true);
                game.close();
                game.running = false;
            }
            

        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0;i<handler.object.size();i++){
            Objects tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_UP){
                    tempObject.setVel_y(+5);
                    tempObject.jumping = false;
                    tempObject.falling = true;
                    tempObject.jumpState = true;
                    tempObject.setVel_x(0);
                }
                
                if(key == KeyEvent.VK_LEFT){
                    tempObject.setVel_x(0);
                }
                
                if(key == KeyEvent.VK_RIGHT){
                    tempObject.setVel_x(0);
                }
                
            }
        }
    }
}