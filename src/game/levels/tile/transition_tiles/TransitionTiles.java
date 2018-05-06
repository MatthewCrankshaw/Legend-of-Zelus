package game.levels.tile.transition_tiles;

import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public abstract class TransitionTiles {

    public BasicTile NW_DiagonalTile;
    public BasicTile NE_DiagonalTile;
    public BasicTile SW_DiagonalTile;
    public BasicTile SE_DiagonalTile;

    public BasicTile NW_CornerTile;
    public BasicTile NE_CornerTile;
    public BasicTile SW_CornerTile;
    public BasicTile SE_CornerTile;

    public BasicTile northEdgeTile;
    public BasicTile westEdgeTile;
    public BasicTile eastEdgeTile;
    public BasicTile southEdgeTile;

    public BasicTile solidTile1;
    public BasicTile solidTile2;

    public TransitionTiles() {
        setTiles();
    }

    public abstract void setTiles();

}
