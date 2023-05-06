package game.levels.tile.static_tiles;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;
import game.levels.tile.TileConstants;

import java.awt.geom.Point2D;

/**
 * Created by Matthew.c on 29/01/2017.
 */
public class BasicSolidTile extends Tile {

    public BasicSolidTile(Sprite sprite){
        super(sprite, true, 0.0f);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        Point2D position = new Point2D.Float(x << TileConstants.TILE_SHIFT_BIT, y << TileConstants.TILE_SHIFT_BIT);
        screen.renderTile(position,this);
    }

    @Override
    public void tick() {

    }
}
