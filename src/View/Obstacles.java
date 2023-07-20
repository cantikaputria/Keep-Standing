/*
 * Filename     : Obstacle.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : Class yang mengatur gameObjek Obstacles
*/

//AKSES LIBRARY DAN PACKAGE
package View;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.util.Random;

public class Obstacles extends Objects{
    
    Color color;    //menyimpan color untuk warna Obstacles
    private int panjang;
    private int bonusScore;
    
    public Obstacles(int x, int y, ID id, Color c, int panjang){
        //kontruktor
        super(x, y, id);
        inFrame=true;
        this.color = c;
        this.panjang = generateRandomHeight();
        this.y = Game.HEIGHT;

        this.bonusScore = calculateBonusScore(panjang);
        
    }
    
    public int getWidth(){
        return panjang;
    }
    public int getHeigth(){
        return 50;
    }
    
    public int getBonusScore() {
        return bonusScore;
    }
    
    private int generateRandomHeight() {
        // Menentukan rentang tinggi acak yang diinginkan (misalnya 50 hingga 200)
        int minHeight = 50;
        int maxHeight = 400;

        // Menghasilkan tinggi acak dalam rentang yang ditentukan
        int randomHeight = (int) (Math.random() * (maxHeight - minHeight + 1)) + minHeight;

        return randomHeight;
    }
    
    
    private int calculateBonusScore(int panjang) {
        int bonus=0;
        if(panjang >= 50 && panjang <60){
            bonus = 60;
        } else if(panjang >=60 && panjang < 90){
            bonus = 50;
        } else if(panjang >=90 && panjang < 130){
            bonus = 40;
        } else if(panjang >=130 && panjang < 200){
            bonus = 30;
        } else if(panjang >=200 && panjang < 300){
            bonus = 20;
        } else if(panjang >=300 && panjang < 400){
            bonus = 10;
        }

        return bonus;
    }

    @Override
    public void tick() {
        y += vel_x;
        x += vel_y;
        
        Moving_Up();
    }

    @Override
    public void render(Graphics g) {
        // memberi warna kotak
        g.setColor(this.color);
        g.fillRect(x, y, panjang, 40);
        // membuat kotak
        g.drawRect(x, y, panjang, 40);
        
    }

    private void Moving_Up(){
        y -= 1;//bergerak ke atas
        if( y < -50 ){
            inFrame = false;//kalo sudah keluar dari frame
        }
    }  
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, panjang, 50);
    }

}