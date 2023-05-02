package game;

import game.ai.AiManager;
import game.ai.PathFinder;
import game.animators.ability_animators.FireballAnimator;
import game.animators.ability_animators.TeleportAnimator;
import game.animators.mob_animators.CharacterAnimator;
import game.entities.Spawner;
import game.entities.ability.ability_managers.FireballManager;
import game.entities.ability.ability_managers.TeleportManager;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.AnimatedSprite;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.environment_sprites.FloorTileSprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.graphics.ui.UserInterface;
import game.levels.Level;
import game.levels.tile.Tile;
import game.levels.tile.TileManager;
import game.levels.tile.animated_tiles.AnimatedTile;
import game.levels.tile.static_tiles.BasicTile;
import game.levels.tile.static_tiles.VoidTile;
import game.levels.tile.transition_tiles.DirtToGrassTiles;
import game.levels.tile.transition_tiles.GrassToDirtTiles;
import game.levels.tile.transition_tiles.GrassToSandTiles;
import game.levels.tile.transition_tiles.TransitionTiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int WIDTH = (screensize.width / 2) - (screensize.width / 15);
    private static int HEIGHT = (screensize.height / 2) - (screensize.height / 15);
    private static int SCALE;
    private static final String NAME = "Never Lost - Matthew Crankshaw - 14303742";
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private UserInterface ui;
    private InputHandler input;
    private Level level;
    private Screen screen;
    private Player player;
    private AiManager ai;
    private Spawner spawner;
    private TileManager tileManager;

    public static void main(String[] args) {
        int numpixels = screensize.width * screensize.height;
        System.out.println("Screen Width: " + screensize.width + " Screen Height: " + screensize.height + " Number of Pixels: " + numpixels);


        if (numpixels < 1000000) {
            WIDTH = screensize.width;
            HEIGHT = screensize.height;
            SCALE = 1;
            System.out.println("Adjusted Width: " + WIDTH + " Adjusted Height: " + HEIGHT + " Scale: " + SCALE);

        } else if (numpixels > 1000000 && numpixels < 2000000) {
            WIDTH = (screensize.width / 2);
            HEIGHT = (screensize.height / 2);
            SCALE = 2;
            System.out.println("Adjusted Width: " + WIDTH + " Adjusted Height: " + HEIGHT + " Scale: " + SCALE);
        } else if (numpixels > 2000000 && numpixels < 4000000) {
            WIDTH = (screensize.width / 4);
            HEIGHT = (screensize.height / 4);
            SCALE = 4;
            System.out.println("Adjusted Width: " + WIDTH + " Adjusted Height: " + HEIGHT + " Scale: " + SCALE);
        } else if (numpixels > 4000000 && numpixels < 8000000) {
            WIDTH = (screensize.width / 8);
            HEIGHT = (screensize.height / 8);
            SCALE = 8;
            System.out.println("Adjusted Width: " + WIDTH + " Adjusted Height: " + HEIGHT + " Scale: " + SCALE);
        }

        //Start the game
        new Game().start();
    }

    public Game() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void init() {
        this.screen = new Screen(WIDTH, HEIGHT, SCALE);
        this.input = new InputHandler(this);
        this.input.registerKey(new Key(KeyEvent.VK_UP));
        this.input.registerKey(new Key(KeyEvent.VK_DOWN));
        this.input.registerKey(new Key(KeyEvent.VK_LEFT));
        this.input.registerKey(new Key(KeyEvent.VK_RIGHT));
        this.input.registerKey(new Key(KeyEvent.VK_E));
        this.input.registerKey(new Key(KeyEvent.VK_ESCAPE));
        this.input.registerKey(new Key(KeyEvent.VK_SPACE));
        this.input.registerKey(new Key(MouseEvent.BUTTON1));

        Map<TileManager.TileType, Tile> tileTypes = new HashMap<>();
        tileTypes.put(TileManager.TileType.VOID, new VoidTile(FloorTileSprite.voidSprite));
        tileTypes.put(TileManager.TileType.STONE, new BasicTile(FloorTileSprite.stone));
        tileTypes.put(TileManager.TileType.GRASS, new BasicTile(FloorTileSprite.grass));
        tileTypes.put(TileManager.TileType.WOOD_FLOOR, new BasicTile(FloorTileSprite.woodFloor));
        tileTypes.put(TileManager.TileType.SAND_STONE, new BasicTile(FloorTileSprite.sandStone));

        Map<TileManager.TransitionTileTypes, TransitionTiles> transitionTiles = new HashMap<>();
        transitionTiles.put(TileManager.TransitionTileTypes.GRASS_TO_SAND, new GrassToSandTiles());
        transitionTiles.put(TileManager.TransitionTileTypes.DIRT_TO_GRASS, new DirtToGrassTiles());
        transitionTiles.put(TileManager.TransitionTileTypes.GRASS_TO_DIRT, new GrassToDirtTiles());

        Map<TileManager.AnimatedTileTypes, AnimatedTile> animatedTiles = new HashMap<>();
        animatedTiles.put(TileManager.AnimatedTileTypes.MUD, new AnimatedTile(FloorTileSprite.mud, false, 0.1f, 1000));
        animatedTiles.put(TileManager.AnimatedTileTypes.SWIMMING, new AnimatedTile(PlayerSprite.swimming, false, 0.0f, 500));

        tileManager = new TileManager(tileTypes, transitionTiles, animatedTiles);
        this.level = new Level("/levels/TestingArena.png", tileManager);
        Spawner spawner = new Spawner(level, screen);

        FireballAnimator fireballAnimator = new FireballAnimator(screen, 4, PlayerSprite.playerAttackSprites);
        FireballManager fireballManager = new FireballManager(screen, input, level, Sprite.fireballSprites, fireballAnimator, spawner);

        TeleportAnimator teleportAnimator = new TeleportAnimator(
            screen,
            6,
            AnimatedSprite.teleportSprite,
            Sprite.teleportFloorSign,
            Player.TELEPORT_CAST_SPEED/6
        );
        TeleportManager teleportManager = new TeleportManager(screen, input, level, teleportAnimator);

        CharacterAnimator characterAnimator = new CharacterAnimator(screen, 4, PlayerSprite.wizardSprites, 100, 1);
        this.player = new Player(150, 150, level, screen, input, fireballManager, teleportManager, characterAnimator);

        this.ui = new UserInterface(screen, player);
        this.spawner = new Spawner(level, screen);

        ArrayList<Enemy> enemies = initializeEnemies();
        PathFinder pathFinder = new PathFinder(player, enemies, level);
        this.ai = new AiManager(this.level, this.screen, this.ui, this.spawner, pathFinder, enemies);

        this.ui.setAiManager(ai);
        this.input.setUi(ui);
        this.level.add(player);
    }

    private ArrayList<Enemy> initializeEnemies(){
        int numberOfZombies = 1;
        int numberOfEnemyWiz = 1;
        int numberOfDeathKeepers = 1;

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.addAll(this.spawner.spawnEnemies(20, 20, Spawner.Type.ENEMY_ZOMBIE, numberOfZombies));
        enemies.addAll(this.spawner.spawnEnemies(20, 20, Spawner.Type.ENEMY_WIZARD, numberOfEnemyWiz));
        enemies.addAll(this.spawner.spawnEnemies(20, 20, Spawner.Type.ENEMY_DEATH_KEEPER, numberOfDeathKeepers));
        return enemies;
    }


    private synchronized void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60;

        int frames = 0;
        int ticks = 0;

        long lastTimer = System.currentTimeMillis();

        double delta = 0;

        init();

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (delta >= 1) {
                ticks++;
                if (!input.isKeyPressed(KeyEvent.VK_ESCAPE)) tick();
                delta -= 1;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frames++;
            render();

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(frames + ", " + ticks);
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick() {
        //tickCount++;
        //if paused don't update these
        if (!ui.isGamePaused()) {
            level.tick();
            ai.tick();
            tileManager.tick();
        }
        ui.tick();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        //Make sure game is not paused
        if (!ui.isGamePaused()) {
            ui.setGamePaused(false);
            int playerx = (int) player.getX() - screen.getWidth() / 2;
            int playery = (int) player.getY() - screen.getHeight() / 2;
            level.render(screen, playerx, playery);
        } else {
            ui.setGamePaused(true);
        }
        ui.render();


        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixels(i);
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
        bs.show();
    }
}
