/*
 * Filename     : Handler.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : class yang berfungsi untuk mengatur setiap game objek
 *                seperti menambah, menghapus, dan proses rendering
*/

//AKSES LIBRARY DAN PACKAGE
package View;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import View.ID;

public class Handler {
     LinkedList<Objects> object = new LinkedList<Objects>();
    
    public void tick(){
        for(int i=0;i<object.size(); i++){
            Objects tempObject = object.get(i);
            
            tempObject.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i=0;i<object.size(); i++){
            Objects tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    //membuat objek
    public void addObject(Objects object){
        this.object.add(object);
    }
    
    //menghapus objek
    public void removeObject(Objects object){
        this.object.remove(object);
    }
}
