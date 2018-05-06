package game.levels.tile.transition_tiles;

import game.graphics.sprite.environment_sprites.GrassToDirtSprites;
import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class GrassToDirtTiles extends TransitionTiles {

    public GrassToDirtTiles() {
    }

    @Override
    public void setTiles() {
        super.NW_DiagonalTile = new BasicTile(GrassToDirtSprites.NW_Diagonal);
        super.NE_DiagonalTile = new BasicTile(GrassToDirtSprites.NE_Diagonal);
        super.SW_DiagonalTile = new BasicTile(GrassToDirtSprites.SW_Diagonal);
        super.SE_DiagonalTile = new BasicTile(GrassToDirtSprites.SE_Diagonal);

        super.NW_CornerTile = new BasicTile(GrassToDirtSprites.NW_Corner);
        super.NE_CornerTile = new BasicTile(GrassToDirtSprites.NE_Corner);
        super.SW_CornerTile = new BasicTile(GrassToDirtSprites.SW_Corner);
        super.SE_CornerTile = new BasicTile(GrassToDirtSprites.SE_Corner);

        super.northEdgeTile = new BasicTile(GrassToDirtSprites.northEdge);
        super.westEdgeTile = new BasicTile(GrassToDirtSprites.westEdge);
        super.eastEdgeTile = new BasicTile(GrassToDirtSprites.eastEdge);
        super.southEdgeTile = new BasicTile(GrassToDirtSprites.southEdge);

        super.solidTile1 = new BasicTile(GrassToDirtSprites.solidDirt1);
        super.solidTile2 = new BasicTile(GrassToDirtSprites.solidDirt2);
    }
}
