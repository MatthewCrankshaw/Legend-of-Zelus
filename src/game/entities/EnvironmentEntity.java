package game.entities;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;

import java.awt.geom.Point2D;

public class EnvironmentEntity extends Entity{
    private Sprite sprite;
    private int scale;

    public EnvironmentEntity(Level level, Sprite sprite, double x, double y, int scale) {
        super(level);
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(new Point2D.Float((float)this.x, (float)this.y), sprite, true, -1, scale);
    }
}
