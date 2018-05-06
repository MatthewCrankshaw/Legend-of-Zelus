package game.levels.tile.animated_tiles;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 30/01/2017.
 */
public class AnimatedTile extends Tile{
    //public int x, y;
    private int currentAnimationIndex;
    private long lastIterationTime;
    private int animationSwitchDelay;

    public AnimatedTile(Sprite[] sprites, boolean solid, boolean emitter, int animationSwitchDelay){
        super(sprites, solid, emitter);
        currentSprite = animatedSprite[0];
        this.animationSwitchDelay = animationSwitchDelay;
    }

    public int getCurrentAnimationIndex(){
        return currentAnimationIndex;
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << TILE_SHIFT_BIT,y << TILE_SHIFT_BIT, this);
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
