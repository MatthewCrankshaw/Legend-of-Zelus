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
    protected double speed, lifeSpan, damage;
    public static int fireCount;
    protected boolean explode = false;


    public Projectile(Level level, Screen screen, int xOrigin, int yOrigin, double dir){
        super(level, screen);
        this.alive = true;
        this.x = xOrigin;
        this.y = yOrigin;
        this.angle = dir;
    }


    public abstract void tick();

    public abstract void render(Screen screen);

    public abstract void fizzleOut(Screen screen);

    public abstract void explode(Screen screen);

    public boolean isAlive(){
        return alive;
    }

    public boolean isExploding(){
        return explode;
    }


}
