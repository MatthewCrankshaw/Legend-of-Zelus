package game.levels;

import game.entities.Entity;
import game.entities.mob.Mob;
import game.entities.particles.Particles;
import game.graphics.Screen;
import game.levels.tile.Tile;
import game.levels.tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew.c on 26/01/2017.
 */
public class Level {

    int width;
    int height;


    private List<Entity> entities = new ArrayList<>();
    private List<Particles> particles = new ArrayList<>();


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

    public boolean tileCollision(int x, int y, int size, int topOffsetX, int topOffsetY, int botOffsetX, int botOffsetY, int movingDir) {
        boolean solid = false;

        Point corners[] = new Point[4];
        corners[0] = new Point(x + topOffsetX           , y + topOffsetY);
        corners[1] = new Point(x + size + topOffsetX    , y + topOffsetY);
        corners[2] = new Point(x + botOffsetX           , y + size + botOffsetY);
        corners[3] = new Point(x + size + botOffsetX    , y + size + botOffsetY);

        //if object is moving we need to check only the moving direction
        if (movingDir == 0) { //up
            if(TILE_MANAGER.getTile(corners[0].x >> 3, corners[0].y >> 3).isSolid() && TILE_MANAGER.getTile(corners[1].x >> 3, corners[1].y >> 3).isSolid()){
                solid = true;
            }
        } else if (movingDir == 1) { //down
            if(TILE_MANAGER.getTile(corners[2].x >> 3, corners[2].y >> 3).isSolid() && TILE_MANAGER.getTile(corners[3].x >> 3, corners[3].y >> 3).isSolid()){
                solid = true;
            }
        } else if (movingDir == 2) { //left
            if(TILE_MANAGER.getTile(corners[0].x >> 3, corners[0].y >> 3).isSolid() && TILE_MANAGER.getTile(corners[2].x >> 3, corners[2].y >> 3).isSolid()){
                solid = true;
            }
        } else if (movingDir == 3) { //right
            if(TILE_MANAGER.getTile(corners[1].x >> 3, corners[1].y >> 3).isSolid() && TILE_MANAGER.getTile(corners[3].x >> 3, corners[3].y >> 3).isSolid()){
                solid = true;
            }
        } else {//Check all directions

            for (int i = 0; i < 3; i++) {
                if (TILE_MANAGER.getTile(corners[i].x >> 3, corners[i].y >> 3).isSolid()) {
                    solid = true;
                }
            }
        }
        return solid;
    }
}
