package game.levels.tile;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.tile.animated_transition_tiles.SandToWaterTiles;

/**
 * Created by Matthew.c on 26/01/2017.
 */
public abstract class Tile {
    protected int x, y;
    private float speedImparement;
    private boolean solid;

    protected Sprite currentSprite;
    public static SandToWaterTiles sandToWaterTiles = new SandToWaterTiles();

    public Tile(Sprite currentSprite, boolean solid, float speedImparement){
        this.currentSprite = currentSprite;
        this.solid = solid;
        this.speedImparement = speedImparement;
    }

    public Sprite getCurrentSprite() {
        return this.currentSprite;
    }

    public abstract void render(int x, int y, Screen screen);

    public abstract void tick();

    public boolean isSolid(){
        return solid;
    }

    public float getSpeedImparement(){ return speedImparement; }
}
