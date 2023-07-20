/*
 * Filename     : Game.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : class utama yang mengatur proses game
*/

//AKSES LIBRARY DAN PACKAGE
package View;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.sound.sampled.Clip;
import java.util.Random;
import presenter.ProsesGame;
import View.Obstacles;

public class Game extends Canvas implements Runnable {
    Window window;

    private int obstacleTimer = 0; // Waktu untuk memunculkan obstacle
    private int obstacleInterval = 100; // Interval waktu antara munculnya obstacle (misalnya setiap 100 ticks)
    private int obstacleMinHeight = 50; // Tinggi minimum obstacle
    private int obstacleMaxHeight = 200; // Tinggi maksimum obstacle

    public static final int WIDTH = 800; // panjang windows game
    public static final int HEIGHT = 550; // tinggi windows game

    private int standing = 0; // menyimpan standing
    private int score = 0; // menyimpan score
    private String nama, update; // menyimpan usename dan keterangan update atau tidak

    private Thread thread; // instansiasi Thread
    boolean running = false;

    private Handler handler;
    private Clip audio; // backsound

    int waktu = 0; // untuk waktu muncul nya si item
    int bantu = 0; // agar tidak double tampilannya
    boolean lewat = false; // menandakan celah
    Color color;
    int awal;
    int tinggi = 100;// set tinggi item
    int width;
    int height;

    public enum STATE {
        Game, GameOver
    };

    public STATE gameState = STATE.Game;

    public Game(String username, String cekUpdate, int standinging, int scoring) {

        // membuat tampilan windows
        window = new Window(WIDTH, HEIGHT, "Keep Standing", this);
        handler = new Handler();

        this.addKeyListener(new KeyInput(handler, this));

        // menentukan warna secara random
        color = randomColor();

        // memberikan nilai awal
        standing = standinging;
        score = scoring;
        nama = username;
        update = cekUpdate;

        if (gameState == STATE.Game) {
            // membuat objek pertama kali
            handler.addObject(new Character(700, 100, ID.Player, width, height));
            handler.addObject(new Obstacles(awal, 515-tinggi, ID.Obstacles, color, tinggi));
        }
        // membuat backsound
        Sound bgm = new Sound();
        audio = bgm.playSound(this.audio, "02_Main_BGM.wav");
        
    }

    
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    // untuk memberhentikan permainan
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                render();
            }
        }
        
     
              // jika state saat ini GameOver
                    Sound bgm = new Sound(); 
                    bgm.stopSound(this.audio); // stop bgm
                   
        stop();
    }

    private void tick() {
        handler.tick();
        if (gameState == STATE.Game) {
            obstacleTimer++;
            if (obstacleTimer >= obstacleInterval) {
                // Munculkan obstacle baru dengan tinggi acak
                int obstacleHeight = generateRandomHeight();
                handler.addObject(new Obstacles(WIDTH, 515 - obstacleHeight, ID.Obstacles, color, obstacleHeight));

                // Reset timer
                obstacleTimer = 0;
            }
            waktu++;
            Objects playerObject = null;
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getId() == ID.Player) {
                    // mencari objek player ada atau tidak
                    playerObject = handler.object.get(i);
                }
            }
            if (playerObject != null) {
                for (int i = 0; i < handler.object.size(); i++) {
                    if (handler.object.get(i).getId() == ID.Obstacles) {
                        Obstacles obstacle = (Obstacles) handler.object.get(i);
                        // mengecek posisi player
                        if (CollisionChecker(playerObject, handler.object.get(i)) == 1) {
                            // jika posisi player ada di atas item
                            
                            if(playerObject.jumpState == true){
                                score+=obstacle.getBonusScore();
                                standing += 1; // skor bertambah 1
                                playerObject.jumpState = false;
                                break;
                            }
                        }
                    }
                }

                // kalau objek diluar frame objek nya dihapus
                for (int i = 0; i < handler.object.size(); i++) {
                    playerObject = handler.object.get(i);
                    if (!playerObject.inFrame) {
                        handler.removeObject(handler.object.get(i));
                    }
                }
                // jika tinggi melebihi frame
                if(tinggi >= 500){
                    tinggi = 100;
                }
                if(tinggi <= 99){
                    tinggi = 100;
                }

                // menentukan warna secara random
                color = randomColor();
                // tambah item
                addItem();
            }
        }
    }

    private int generateRandomHeight() {
        int randomHeight = (int) (Math.random() * (obstacleMaxHeight - obstacleMinHeight + 1)) + obstacleMinHeight;
        return randomHeight;
    }

    public void addItem() {
        int rand = (int) (Math.random() * 5);

        //initiate new obstacles every 50 counts of time
        if (waktu % 100 == 0) {
            if (bantu != waktu) {
                if (lewat) {
                    // biar jaraknya selalu satu
                    handler.addObject(new Obstacles(awal, 515-tinggi, ID.Obstacles, color, rand+tinggi));
                    // ramdom nilai tinggi
                    tinggi = randomizer(tinggi-50,tinggi+50);
                    lewat = false;
                } else if (rand % 2 == 0) {
                    handler.addObject(new Obstacles(awal, 515-tinggi, ID.Obstacles, color, rand+tinggi));
                    // random nilai tinggi
                     tinggi = randomizer(tinggi-50,tinggi+50);
                    lewat = false;
                } else {
                    lewat = true;
                }
            }
            bantu = waktu;
        }
    }

    public static int CollisionChecker(Objects player, Objects item) {

        int result = 0;

        int sizePlayer = player.getWidth();
        int sizeObstacle = item.getWidth();

        int playerLeft = player.getX();
        int playerRight = player.getX() + sizePlayer;
        int playerTop = player.getY();
        int playerBottom = player.getY() + sizePlayer;

        int itemLeft = item.getX();
        int itemRight = item.getX() + sizeObstacle;
        int itemTop = item.getY();
        int itemBottom = item.getY() + item.getHeight();

        if ((itemLeft <= playerRight) && (itemRight >= playerLeft)) {
            if (itemTop <= playerBottom && itemTop >= playerTop) {
                // jika player berada di atas item
                player.falling = false; // falling false
                player.y = itemTop - sizePlayer;// membuat objek berdiri di atas item
                result = 1;
            } else if ((itemTop < playerTop) && (itemBottom > playerBottom)) {
                // jika player terkena pinggir item
                if ((playerRight > itemLeft) && (playerLeft < itemLeft)) {
                    player.x = itemLeft - sizePlayer;
                } else if ((playerLeft < itemRight) && (playerRight > itemRight)) {
                    player.x = itemRight;
                }
            } else if (itemBottom >= playerTop && itemTop <= playerTop) {
                // jika player berada di bawah item
                if (player.jumping) {
                    result = 2;
                }
                player.jumping = false;// jumping false

                if ((playerTop < itemBottom)) {
                    player.gravity = 5.0;
                    player.falling = true;
                }
            }
        }else if (playerBottom >= 470) {
            // jika player berada di tanah
                result = 2;
        }

        return result;
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // membuat background
        Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("pict.jpg"));
        g.drawImage(img1, 0, 0, null);

        if (gameState == STATE.Game) {
            handler.render(g);

            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            g.setFont(newFont);

            g.setColor(Color.BLACK);
            g.drawString("Standing: " + Integer.toString(standing), 650, 30);

            g.setColor(Color.BLACK);
            g.drawString("Score: " + Integer.toString(score), 650, 50);

        } else {

            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 3F);
            g.setFont(newFont);
            
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", WIDTH / 2 - 120, HEIGHT / 2 - 30);

            currentFont = g.getFont();
            Font newstandingFont = currentFont.deriveFont(currentFont.getSize() * 0.5F);
            g.setFont(newstandingFont);

            g.setColor(Color.BLACK);
            g.drawString("Standing : " + Integer.toString(standing), WIDTH / 2 - 50, HEIGHT / 2 - 10);

            g.setColor(Color.BLACK);
            g.drawString("Score  : " + Integer.toString(score), WIDTH / 2 - 50, HEIGHT / 2 + 15);

            g.setColor(Color.BLACK);
            g.drawString("Press Space to Continue", WIDTH / 2 - 100, HEIGHT / 2 + 50);

        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public void close() {
        window.CloseWindow();
    }

    // initiate random color
    Color randomColor() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Color randomColor = new Color(r, g, b);
        return randomColor;
    }

    // initiate random number
    public int randomizer(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }

    public void addData() {
        ProsesGame presenter = new ProsesGame(); 
        presenter.terimaData(update, nama, score, standing);
    }
}
