package game.entities.ability.projectiles;

import game.entities.ability.Ability;
import game.graphics.Screen;
import game.levels.Level;

/**
 * Created by Matthew.c on 24/01/2017.
 */
public abstract class Projectile extends Ability{
    public double angle;
    protected double nx, ny;
    protected double speed, lifeSpan;
    public static int fireCount;


    public Projectile(Level level, Screen screen, int xOrigin, int yOrigin, double dir, int damage){
        super(level, screen, damage);
        this.alive = true;
        this.x = xOrigin;
        this.y = yOrigin;
        this.angle = dir;
    }


    public abstract void tick();

    public abstract void render(Screen screen);

    public abstract void fizzleOut();

    public abstract void explode();

    public boolean isAlive(){
        return alive;
    }

    public boolean isExploding(){
        return exploding;
    }


}
