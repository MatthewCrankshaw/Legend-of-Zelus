package game.entities.mob;


import game.entities.Entity;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;
import game.levels.tile.Tile;
import game.levels.tile.TileConstants;
import game.levels.tile.TileManager;
import game.levels.tile.animated_tiles.AnimatedTile;
import game.levels.tile.animated_transition_tiles.AnimatedTransitionTiles;

/**
 * Created by Matthew.c on 21/01/2017.
 */
public abstract class Mob extends Entity {

    protected Screen screen;
    protected String name;
    protected double speed;
    protected int numSteps;
    protected int movingDir = 1; // 0 is up 1 is down 2 is left 3 is right
    protected boolean moving;
    protected boolean stuck;
    protected Sprite sprite;
    protected int mobScale;
    protected TileManager tileManager;

    public Mob(Level level, Screen screen, String name, int speed, int mobScale, TileManager tileManager){
        super(level);
        this.screen = screen;
        this.name = name;
        this.speed = speed;
        this.mobScale = mobScale;
        moving = false;
        alive = true;
        this.tileManager = tileManager;
    }


    public void move(int xa, int ya){
        isStuck();
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            numSteps--;
            return;
        }
        numSteps++;
        if (ya < 0) {
            movingDir = 0;
        }
        if (ya > 0) {
            movingDir = 1;
        }
        if (xa < 0) {
            movingDir = 2;
        }
        if (xa > 0) {
            movingDir = 3;
        }
        if (!level.tileCollision( (int)x + xa,  (int)y + ya, 16*mobScale, 0, 8, 0,0, movingDir)) {
            x += xa * (speed + getTileMovementImparement());
            y += ya * (speed + getTileMovementImparement());
        }
    }

    public void isStuck(){
        boolean s = true;
        if (!level.tileCollision((int)x - 1,(int)y - 1, 16*mobScale, 0, 8, 0,0, movingDir)) {
            s = false;
        }
        if (!level.tileCollision((int)x + 1,(int)y - 1, 16*mobScale, 0, 8, 0,0, movingDir)) {
            s = false;
        }
        if (!level.tileCollision((int)x - 1,(int)y + 1, 16*mobScale, 0, 8, 0,0, movingDir)) {
            s = false;
        }
        if (!level.tileCollision((int)x + 1,(int)y + 1, 16*mobScale, 0, 8, 0,0, movingDir)) {
            s = false;
        }
        stuck = s;
    }

    public void changeLocation(int x, int y){
        this.x += x;
        this.y += y;
    }

    public void warpLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    //todo make this more efficient
    public boolean isSwimming(){
        AnimatedTransitionTiles tiles = tileManager.getAnimatedTransitionTiles(TileManager.AnimatedTransitionTileTypes.SAND_TO_WATER);
        for(Tile t : tiles.getSolidTile1()) {
            if (level.getTileManager().getTile((int)x >> TileConstants.TILE_SHIFT_BIT, (int)y >> TileConstants.TILE_SHIFT_BIT).equals(t)) {
                return true;
            }
            else {
                for(Tile t2 : tiles.getSolidTile2()) {
                    if (level.getTileManager().getTile((int)x >> TileConstants.TILE_SHIFT_BIT, (int)y >> TileConstants.TILE_SHIFT_BIT).equals(t2)) {
                        return true;
                    }
                }
            }
        }
        return level.getTileManager().getTile((int)x >> TileConstants.TILE_SHIFT_BIT,(int)y >> TileConstants.TILE_SHIFT_BIT).getClass() == AnimatedTile.class;
    }

    //todo make this more efficient
    public float getTileMovementImparement(){
        AnimatedTransitionTiles tiles = tileManager.getAnimatedTransitionTiles(TileManager.AnimatedTransitionTileTypes.SAND_TO_WATER);
        for(Tile t : tiles.getSolidTile1()) {
            if (level.getTileManager().getTile((int)x >> TileConstants.TILE_SHIFT_BIT, (int)y >> TileConstants.TILE_SHIFT_BIT).equals(t)) {
                return t.getSpeedImparement();
            }
            else {
                for(Tile t2 : tiles.getSolidTile2()) {
                    if (level.getTileManager().getTile((int)x >> TileConstants.TILE_SHIFT_BIT, (int)y >> TileConstants.TILE_SHIFT_BIT).equals(t2)) {
                        return t.getSpeedImparement();
                    }
                }
            }
        }
        return 0.0f;
    }

    public int getMovingDir(){
        return movingDir;
    }

    public boolean isMoving(){
        return moving;
    }

    public boolean getStuck(){
        return stuck;
    }

    public String getName(){ return this.name;}
}
