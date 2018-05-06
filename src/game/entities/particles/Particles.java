package game.entities.particles;

import game.entities.Entity;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;


/**
 * Created by Matthew.c on 08/02/2017.
 */
public class Particles extends Entity{
    private Sprite sprite;

    private long currentTime;
    private long createdTime;
    private int life;

    private boolean alive;

    protected double xx, yy, zz;
    protected double xa, ya, za;

    public Particles(Level level, int x, int y, int life){
        super(level);
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = life;
        sprite = Sprite.particle_simple;
        alive = true;
        this.createdTime = System.currentTimeMillis();

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.zz = random.nextFloat() + 2.0;
    }


    @Override
    public void tick() {
        if (currentTime - createdTime >= life){
            alive = false;
        }


        if (zz  < 0) {
            zz = 0;
            za *= -0.5d;
            xa *= 0.5d;
            ya *= 0.5d;
        }

        move(xx + xa, (yy + ya)+ (zz + za));


        za -= 0.2;
        currentTime = System.currentTimeMillis();


    }

    public void move(double x, double y){
        if (collision(x, y)) {
            this.xa *= -0.5;
            this.ya *= -0.5;
            this.za *= -0.5;
        }
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
    }

    public boolean collision(double x, double y){
        //width and height modifier should be left at 1 unless the size of the sprite is bigger than 8 by 8 pixel
        boolean solid = false;
        for(int c = 0; c < 4; c++) {
            double xt = ((x - c % 2  * 8) / 8);
            double yt = ((y - c / 2 * 8) / 8);

            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);
            if (c % 2 == 0)ix = (int) Math.floor(xt);
            if (c / 2 == 0)iy = (int) Math.floor(yt);
            if (Level.TILE_MANAGER.getTile(ix, iy).isSolid()) solid = true;
        }
        return solid;
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int) xx, (int) yy - (int)zz +3, sprite);
    }

    public boolean isAlive(){
        return alive;
    }
}
