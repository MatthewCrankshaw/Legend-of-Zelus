package game.entities.ability.projectiles;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;

import java.awt.geom.Point2D;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class FireballProjectile extends Projectile{

    private long createdTime;


    public FireballProjectile(Level level, Screen screen, int x, int y, double dir, Sprite[] fireballSprites){
        super(level, screen, x, y, dir, 10, fireballSprites);
        createdTime = System.currentTimeMillis();
        speed = 1.5;
        lifeSpan = 0.7;
        damage = 10;
        fireCount = 0;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    @Override
    public void tick() {
        move();
    }

    public void move(){
        fireCount++;

        long currentTime = System.currentTimeMillis();
        if (currentTime - createdTime >= lifeSpan * 1000) {
            alive = false;
        }

        if (level.tileCollision((int)(x),(int)(y), 8, 0, 0, 0, 0, -1)) {
            exploding = true;
        }
        x += nx;
        y += ny;
    }

    @Override
    public void render(Screen screen) {
        Point2D position = new Point2D.Float((float)x, (float)y);
        screen.renderSprite(position, fireballSprite[0], true, -1, 1);
    }

    @Override
    public void explode() {
        Point2D position = new Point2D.Float((float)x, (float)y);
        screen.renderSprite(position, fireballSprite[1], true, -1, 1);
    }

    public void fizzleOut(){
        Point2D position = new Point2D.Float((float)x, (float)y);
        screen.renderSprite(position, fireballSprite[1], true, -1, 1);
    }
}
