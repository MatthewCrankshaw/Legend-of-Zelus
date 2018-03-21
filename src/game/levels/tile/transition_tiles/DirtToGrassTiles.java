package game.levels.tile.transition_tiles;

import game.graphics.sprite.environment_sprites.DirtToGrassSprites;
import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class DirtToGrassTiles extends TransitionTiles {

    public DirtToGrassTiles() {
    }

    @Override
    public void setTiles() {
        super.NW_DiagonalTile = new BasicTile(DirtToGrassSprites.NW_Diagonal);
        super.NE_DiagonalTile = new BasicTile(DirtToGrassSprites.NE_Diagonal);
        super.SW_DiagonalTile = new BasicTile(DirtToGrassSprites.SW_Diagonal);
        super.SE_DiagonalTile = new BasicTile(DirtToGrassSprites.SE_Diagonal);

        super.NW_CornerTile = new BasicTile(DirtToGrassSprites.NW_Corner);
        super.NE_CornerTile = new BasicTile(DirtToGrassSprites.NE_Corner);
        super.SW_CornerTile = new BasicTile(DirtToGrassSprites.SW_Corner);
        super.SE_CornerTile = new BasicTile(DirtToGrassSprites.SE_Corner);

        super.northEdgeTile = new BasicTile(DirtToGrassSprites.northEdge);
        super.westEdgeTile = new BasicTile(DirtToGrassSprites.westEdge);
        super.eastEdgeTile = new BasicTile(DirtToGrassSprites.eastEdge);
        super.southEdgeTile = new BasicTile(DirtToGrassSprites.southEdge);

        super.solidTile1 = new BasicTile(DirtToGrassSprites.solidGrass1);
        super.solidTile2 = new BasicTile(DirtToGrassSprites.solidGrass2);
    }
}
