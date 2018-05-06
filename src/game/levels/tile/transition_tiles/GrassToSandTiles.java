package game.levels.tile.transition_tiles;

import game.graphics.sprite.environment_sprites.GrassToSandSprites;
import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class GrassToSandTiles extends TransitionTiles {

    public GrassToSandTiles() {
    }

    @Override
    public void setTiles(){
        super.NW_DiagonalTile = new BasicTile(GrassToSandSprites.NW_Diagonal);
        super.NE_DiagonalTile = new BasicTile(GrassToSandSprites.NE_Diagonal);
        super.SW_DiagonalTile = new BasicTile(GrassToSandSprites.SW_Diagonal);
        super.SE_DiagonalTile = new BasicTile(GrassToSandSprites.SE_Diagonal);

        super.NW_CornerTile = new BasicTile(GrassToSandSprites.NW_Corner);
        super.NE_CornerTile = new BasicTile(GrassToSandSprites.NE_Corner);
        super.SW_CornerTile = new BasicTile(GrassToSandSprites.SW_Corner);
        super.SE_CornerTile = new BasicTile(GrassToSandSprites.SE_Corner);

        super.northEdgeTile = new BasicTile(GrassToSandSprites.northEdge);
        super.westEdgeTile = new BasicTile(GrassToSandSprites.westEdge);
        super.eastEdgeTile = new BasicTile(GrassToSandSprites.eastEdge);
        super.southEdgeTile = new BasicTile(GrassToSandSprites.southEdge);

        super.solidTile1 = new BasicTile(GrassToSandSprites.solidSand1);
        super.solidTile2 = new BasicTile(GrassToSandSprites.solidSand2);
    }


}
