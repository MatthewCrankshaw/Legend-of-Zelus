package game.levels;

import game.entities.Entity;
import game.entities.mob.Mob;
import game.entities.particles.Particles;
import game.graphics.Screen;
import game.levels.tile.Tile;
import game.levels.tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew.c on 26/01/2017.
 */
public class Level {

    protected int width;
    protected int height;


    private List<Entity> entities = new ArrayList<>();
    private List<Particles> particles = new ArrayList<Particles>();


    public static TileManager TILE_MANAGER;





    private BufferedImage levelImage;

    public Level(int width, int height){
        this.width = width;
        this.height = height;
        TILE_MANAGER = new TileManager(width, height);
    }

    public Level(String path){
        loadLevelFromFile(path);
        TILE_MANAGER = new TileManager(width, height);
    }

    protected void loadLevelFromFile(String path){
        try{
            this.levelImage = ImageIO.read(Level.class.getResourceAsStream(path));
            this.width = levelImage.getWidth();
            this.height = levelImage.getHeight();
            this.loadTiles();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTiles(){

    }

    public void add(Entity entity){
        if (entity instanceof Particles) {
            particles.add((Particles) entity);
        }else if (entity instanceof Mob){
            entities.add(entity);
        }
    }

    public void tick(){
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick();
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).tick();
            if (!particles.get(i).isAlive()) {
                particles.remove(i);
            }

        }
    }

    public void render(Screen screen, int xScroll, int yScroll){
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> Tile.TILE_SHIFT_BIT;
        int x1 = (xScroll + screen.width + Tile.TILE_SIZE) >> Tile.TILE_SHIFT_BIT;
        int y0 = yScroll >> Tile.TILE_SHIFT_BIT;
        int y1 = (yScroll + screen.height + Tile.TILE_SIZE) >> Tile.TILE_SHIFT_BIT;

        for(int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                TILE_MANAGER.getTile(x,y).render(x, y, screen);
                TILE_MANAGER.getTile(x,y).tick();
            }
        }

        for (Entity e: entities) {
            e.render(screen);
        }
        for (Entity e: particles) {
            e.render(screen);
        }
    }


    public boolean tileColision(int x, int y, int size, int widthModifier, int heightModifier, int xOffset, int yOffset){
        //width and height modifier should be left at 1 unless the size of the sprite is bigger than 8 by 8 pixel
        boolean solid = false;
        for(int c = 0; c < 4; c++) {
            int xt = ((x + c % 2  * size*widthModifier + xOffset) >> 3);
            int yt = ((y + c / 2 * size*heightModifier + yOffset) >> 3);
            if (TILE_MANAGER.getTile(xt, yt).isSolid()) solid = true;
        }
        return solid;
    }
}
