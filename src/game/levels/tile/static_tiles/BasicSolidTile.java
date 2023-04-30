package game.levels.tile.static_tiles;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;
import game.levels.tile.TileConstants;

/**
 * Created by Matthew.c on 29/01/2017.
 */
public class BasicSolidTile extends Tile {

    public BasicSolidTile(Sprite sprite){
        super(sprite, true, 0.0f);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << TileConstants.TILE_SHIFT_BIT, y << TileConstants.TILE_SHIFT_BIT,this);
    }

    @Override
    public void tick() {

    }
}
