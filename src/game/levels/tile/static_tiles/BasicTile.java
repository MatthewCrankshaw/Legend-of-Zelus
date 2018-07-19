package game.levels.tile.static_tiles;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;


/**
 * Created by Matthew.c on 26/01/2017.
 */
public class BasicTile extends Tile {

    public BasicTile(Sprite sprite){
        super(sprite, false, false, -0.5f);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << TILE_SHIFT_BIT,y << TILE_SHIFT_BIT, this);
    }

    @Override
    public void tick() {

    }
}
