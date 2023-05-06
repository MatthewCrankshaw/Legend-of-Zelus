package game.levels.tile.animated_tiles;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;
import game.levels.tile.TileConstants;

import java.awt.geom.Point2D;

/**
 * Created by Matthew.c on 30/01/2017.
 */
public class AnimatedTile extends Tile {
    private int currentAnimationIndex;
    private long lastIterationTime;
    private int animationSwitchDelay;
    protected Sprite[] animatedSprite;

    public AnimatedTile(Sprite[] sprites, boolean solid, float speedImparement, int animationSwitchDelay){
        super(sprites[0], solid, speedImparement);
        this.animatedSprite = sprites;
        this.animationSwitchDelay = animationSwitchDelay;
    }

    public int getCurrentAnimationIndex(){
        return currentAnimationIndex;
    }

    @Override
    public void render(int x, int y, Screen screen) {
        Point2D position = new Point2D.Float(x << TileConstants.TILE_SHIFT_BIT, y << TileConstants.TILE_SHIFT_BIT);
        screen.renderTile(position, this);
    }

    @Override
    public void tick() {
        if ((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)) {
            lastIterationTime = System.currentTimeMillis();
            currentAnimationIndex = (currentAnimationIndex +1) % animatedSprite.length;
            currentSprite = animatedSprite[currentAnimationIndex];
        }
    }
}
