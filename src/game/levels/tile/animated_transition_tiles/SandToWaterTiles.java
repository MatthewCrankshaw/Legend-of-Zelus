package game.levels.tile.animated_transition_tiles;

import game.graphics.sprite.environment_sprites.SandToWaterSprites;
import game.levels.tile.Tile;
import game.levels.tile.static_tiles.BasicTile;
import game.levels.tile.transition_tiles.TransitionTiles;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class SandToWaterTiles extends AnimatedTransitionTiles {

    public SandToWaterTiles() {
    }

    @Override
    public void setTiles() {
        /*super.NW_DiagonalTile = new BasicTile(SandToWaterSprites.NW_Diagonal[0]);
        super.NE_DiagonalTile = new BasicTile(SandToWaterSprites.NE_Diagonal[0]);
        super.SW_DiagonalTile = new BasicTile(SandToWaterSprites.SW_Diagonal[0]);
        super.SE_DiagonalTile = new BasicTile(SandToWaterSprites.SE_Diagonal[0]);

        super.NW_CornerTile = new BasicTile(SandToWaterSprites.NW_Corner[0]);
        super.NE_CornerTile = new BasicTile(SandToWaterSprites.NE_Corner[0]);
        super.SW_CornerTile = new BasicTile(SandToWaterSprites.SW_Corner[0]);
        super.SE_CornerTile = new BasicTile(SandToWaterSprites.SE_Corner[0]);

        super.northEdgeTile = new BasicTile(SandToWaterSprites.northEdge[0]);
        super.westEdgeTile = new BasicTile(SandToWaterSprites.westEdge[0]);
        super.eastEdgeTile = new BasicTile(SandToWaterSprites.eastEdge[0]);
        super.southEdgeTile = new BasicTile(SandToWaterSprites.southEdge[0]);

        super.solidTile1 = new BasicTile(SandToWaterSprites.solidWater1[0]);
        super.solidTile2 = new BasicTile(SandToWaterSprites.solidWater2[0]);

        */

        /*********************************************************************************
         * each variation of each Sprite as a basic Tile
         * Diagonal Tiles
         *********************************************************************************/


        NW_DiagonalTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.NW_Diagonal[0]),
                new BasicTile(SandToWaterSprites.NW_Diagonal[1]),
                new BasicTile(SandToWaterSprites.NW_Diagonal[2])
        };
        NE_DiagonalTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.NE_Diagonal[0]),
                new BasicTile(SandToWaterSprites.NE_Diagonal[1]),
                new BasicTile(SandToWaterSprites.NE_Diagonal[2])
        };
        SW_DiagonalTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.SW_Diagonal[0]),
                new BasicTile(SandToWaterSprites.SW_Diagonal[1]),
                new BasicTile(SandToWaterSprites.SW_Diagonal[2])
        };
        SE_DiagonalTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.SE_Diagonal[0]),
                new BasicTile(SandToWaterSprites.SE_Diagonal[1]),
                new BasicTile(SandToWaterSprites.SE_Diagonal[2])
        };

        /*********************************************************************************
         * each variation of each Sprite as a basic Tile
         * Corner Tiles
         *********************************************************************************/

        NW_CornerTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.NW_Corner[0]),
                new BasicTile(SandToWaterSprites.NW_Corner[1]),
                new BasicTile(SandToWaterSprites.NW_Corner[2])
        };
        NE_CornerTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.NE_Corner[0]),
                new BasicTile(SandToWaterSprites.NE_Corner[1]),
                new BasicTile(SandToWaterSprites.NE_Corner[2])
        };
        SW_CornerTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.SW_Corner[0]),
                new BasicTile(SandToWaterSprites.SW_Corner[1]),
                new BasicTile(SandToWaterSprites.SW_Corner[2])
        };
        SE_CornerTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.SE_Corner[0]),
                new BasicTile(SandToWaterSprites.SE_Corner[1]),
                new BasicTile(SandToWaterSprites.SE_Corner[2])
        };

        /*********************************************************************************
         * each variation of each Sprite as a basic Tile
         * Edge Tiles
         *********************************************************************************/

        northEdgeTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.northEdge[0]),
                new BasicTile(SandToWaterSprites.northEdge[1]),
                new BasicTile(SandToWaterSprites.northEdge[2]),
        };
        westEdgeTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.westEdge[0]),
                new BasicTile(SandToWaterSprites.westEdge[1]),
                new BasicTile(SandToWaterSprites.westEdge[2]),
        };
        eastEdgeTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.eastEdge[0]),
                new BasicTile(SandToWaterSprites.eastEdge[1]),
                new BasicTile(SandToWaterSprites.eastEdge[2]),
        };
        southEdgeTile = new BasicTile[]{
                new BasicTile(SandToWaterSprites.southEdge[0]),
                new BasicTile(SandToWaterSprites.southEdge[1]),
                new BasicTile(SandToWaterSprites.southEdge[2]),
        };

        /*********************************************************************************
         * each variation of each Sprite as a basic Tile
         * Edge Tiles
         *********************************************************************************/

        solidTile1 = new BasicTile[]{
                new BasicTile(SandToWaterSprites.solidWater1[0]),
                new BasicTile(SandToWaterSprites.solidWater1[1]),
                new BasicTile(SandToWaterSprites.solidWater1[2])
        };
        solidTile2 = new BasicTile[]{
                new BasicTile(SandToWaterSprites.solidWater2[0]),
                new BasicTile(SandToWaterSprites.solidWater2[1]),
                new BasicTile(SandToWaterSprites.solidWater2[2])
        };

    }
}
