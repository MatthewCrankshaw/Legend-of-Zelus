package game.levels.tile.animated_transition_tiles;

import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 21/02/2017.
 */
public abstract class AnimatedTransitionTiles {

    public BasicTile NW_DiagonalTile[];
    public BasicTile NE_DiagonalTile[];
    public BasicTile SW_DiagonalTile[];
    public BasicTile SE_DiagonalTile[];

    public BasicTile NW_CornerTile[];
    public BasicTile NE_CornerTile[];
    public BasicTile SW_CornerTile[];
    public BasicTile SE_CornerTile[];

    public BasicTile northEdgeTile[];
    public BasicTile westEdgeTile[];
    public BasicTile eastEdgeTile[];
    public BasicTile southEdgeTile[];

    public BasicTile solidTile1[];
    public BasicTile solidTile2[];

    public AnimatedTransitionTiles() {
        setTiles();
    }

    public abstract void setTiles();
}
