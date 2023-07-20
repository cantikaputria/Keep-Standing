/*
 * Filename     : Player.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : Class yang mengatur gameObjek player
*/

//AKSES LIBRARY DAN PACKAGE
package View;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Character extends Objects {
    private int width;
    private int height;

    public Character(int x, int y, ID id, int width, int height) {
        super(10, 10, id);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return 50; // Misalnya, lebar karakter adalah 50 piksel
    }

    @Override
    public void tick() {
        x += vel_x;
        y += vel_y;

        x = Game.clamp(x, 0, Game.WIDTH - 40);
        // posisi pemain dibatasi atasnya 300 dan bawahnya 110
        y = Game.clamp(y, 0, Game.HEIGHT - 110);

        if (jumping) {
            gravity -= 0.1;

            setVel_y((int) -gravity);
        }

        if (falling) {
            gravity += 0.1;
            setVel_y((int) gravity);

            // kalo udah di tanah scoreing nya false
            if (getY() == Game.HEIGHT - 110) {
                falling = false;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("cow.png"));
        g.drawImage(img1, x, y, null);
        
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
