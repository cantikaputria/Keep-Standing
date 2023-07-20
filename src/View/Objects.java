/*
 * Filename     : GameObject.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : class yang berfungsi sebagai abstrak class dari setiap 
 *                object game
*/

//AKSES LIBRARY DAN PACKAGE
package View;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Objects {
    protected int x, y;
    protected ID id;
    protected int vel_x;
    protected int vel_y;
    protected int width, height;
    
    public boolean jumping = false;
    public boolean falling = true;
    public double gravity = 0.0;
    public boolean inFrame = true;
    public boolean ok = true;
    public boolean jumpState = false;
    
    public Objects(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVel_x() {
        return vel_x;
    }

    public void setVel_x(int vel_x) {
        this.vel_x = vel_x;
    }

    public int getVel_y() {
        return vel_y;
    }

    public void setVel_y(int vel_y) {
        this.vel_y = vel_y;
    }  
}
