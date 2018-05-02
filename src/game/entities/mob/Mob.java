package game.entities.mob;


import game.entities.Entity;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 21/01/2017.
 */
public abstract class Mob extends Entity {

    protected Screen screen;

    protected String name;
    protected int speed;
    protected int numSteps;
    protected int movingDir = 1; // 0 is up 1 is down 2 is left 3 is right
    protected boolean moving;

    protected boolean stuck;
    protected Sprite sprite;

    public Mob(Level level, Screen screen, String name, int speed){
        super(level);
        this.screen = screen;
        this.name = name;
        this.speed = speed;
        moving = false;
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
        if (!level.tileColision((int) x + xa, (int) y + ya, 8, 2, 1, 0, 8)) {
            x += xa * speed;
            y += ya * speed;
        }
    }

    public void isStuck(){
        boolean s = true;
        if (!level.tileColision((int)x + -1,(int)y -1, 8, 2, 1, 0, 8)) {
            s = false;
        }
        if (!level.tileColision((int)x -1,(int)y + 1, 8, 2, 1, 0, 8)) {
            s = false;
        }
        if (!level.tileColision((int)x + 1,(int)y - 1, 8, 2, 1, 0, 8)) {
            s = false;
        }
        if (!level.tileColision((int)x + 1,(int)y + 1, 8, 2, 1, 0, 8)) {
            s = false;
        }
        stuck = s;
    }

    public void changeLocation(int x, int y){
        this.x += x;
        this.y += y;
    }


    //todo make this more efficient
    public boolean isSwimming(){
        for(Tile t : Tile.sandToWaterTiles.solidTile1) {
            if (Level.TILE_MANAGER.getTile((int)x >> Tile.TILE_SHIFT_BIT, (int)y >> Tile.TILE_SHIFT_BIT).equals(t)) {
                return true;
            }
            else {
                for(Tile t2 : Tile.sandToWaterTiles.solidTile2) {
                    if (Level.TILE_MANAGER.getTile((int)x >> Tile.TILE_SHIFT_BIT, (int)y >> Tile.TILE_SHIFT_BIT).equals(t2)) {
                        return true;
                    }
                }
            }
        }
        return Level.TILE_MANAGER.getTile((int) x >> Tile.TILE_SHIFT_BIT, (int) y >> Tile.TILE_SHIFT_BIT).equals(Tile.mud);
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
