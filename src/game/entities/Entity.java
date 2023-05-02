package game.entities;

import game.graphics.Screen;
import game.levels.Level;
import game.levels.tile.TileManager;

import java.util.Random;

/**
 * Created by Matthew.c on 21/01/2017.
 */

public abstract class Entity {
    protected double x,y;
    protected Level level;
    protected boolean alive;

    protected Random random = new Random();

    public Entity(Level level){
        this.level = level;
    }

    public abstract void tick();
    public abstract void render(Screen screen, TileManager tileManager);

    public boolean isAlive(){
        return alive;
    }
    public void kill(){
        alive = false;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
