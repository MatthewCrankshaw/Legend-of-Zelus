package game.entities;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;

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
        screen.renderSprite((int)this.x, (int)this.y, sprite, true, -1, scale);
    }
}
