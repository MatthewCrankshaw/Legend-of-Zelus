package game;

import game.ai.AiManager;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.*;

import game.levels.Level;
import game.levels.SpawnLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    public static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int WIDTH = 700/2;
    public static final int HEIGHT =  700/2;
    public static final int SCALE = 2;
    public static final String NAME = "GAME";

    private JFrame frame;

    public boolean running = false;
    public int tickCount = 0;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public InputHandler input;
    public Level level;
    private Screen screen;
    private Player player;
    private AiManager ai;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public static void main(String[] args) {
        new Game().start();
    }

    public Game(){
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void init(){
        screen = new Screen(WIDTH, HEIGHT);
        input = new InputHandler(this);
        level = new SpawnLevel("/levels/TestingArena.png");
        player = new Player(150, 150, level, screen,input);
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for(int i = 0; i < 20; i++) {
            enemies.add(new Enemy((rand.nextInt() % 200) + 200, (rand.nextInt() % 200) + 200, level, screen, "name", 1, null));
        }
        for(Enemy e: enemies) {
            level.add(e);
        }
        ai = new AiManager(player, enemies);
        level.add(player);
    }


    public synchronized void start(){
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop(){
        running = false;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60;

        int frames = 0;
        int ticks = 0;

        long lastTimer = System.currentTimeMillis();

        double delta = 0;

        init();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            while(delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try{
                Thread.sleep(2);
            }

            catch (InterruptedException e ) {
                e.printStackTrace();
            }

            if(shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(frames + ", " + ticks);
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick(){
        tickCount++;
        level.tick();
        ai.tick();
    }


    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }


        screen.clear();

        int playerx = (int)player.x - screen.width/ 2;
        int playery = (int)player.y - screen.height / 2;
        level.render(screen, playerx, playery);


        for (int i = 0; i < pixels.length; i ++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g;
        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.drawImage(image, 0,0,getWidth(), getHeight(), null);
        g2.dispose();
        bs.show();
    }
}
