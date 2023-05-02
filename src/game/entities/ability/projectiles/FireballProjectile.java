package game.entities.ability.projectiles;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;

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
        screen.renderSprite((int)x, (int)y, fireballSprite[0], true, -1, 1);
    }

    @Override
    public void explode() {
        screen.renderSprite((int)x, (int)y, fireballSprite[1], true, -1, 1);
    }

    public void fizzleOut(){
        screen.renderSprite((int)x,(int)y, fireballSprite[1], true, -1, 1);
    }
}
