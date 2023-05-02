package game.entities.ability.projectiles;

import game.entities.ability.Ability;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;
import game.levels.tile.TileManager;

/**
 * Created by Matthew.c on 24/01/2017.
 */
public abstract class Projectile extends Ability{
    public double angle;
    protected double nx, ny;
    protected double speed, lifeSpan;
    public static int fireCount;
    protected Sprite[] fireballSprite;


    public Projectile(Level level, Screen screen, int xOrigin, int yOrigin, double dir, int damage, Sprite[] fireballSprite){
        super(level, screen, damage);
        this.alive = true;
        this.x = xOrigin;
        this.y = yOrigin;
        this.angle = dir;
        this.fireballSprite = fireballSprite;
    }


    public abstract void tick();

    public abstract void render(Screen screen, TileManager tileManager);

    public abstract void fizzleOut();

    public abstract void explode();

    public boolean isAlive(){
        return alive;
    }

    public boolean isExploding(){
        return exploding;
    }


}
