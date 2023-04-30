package game.levels.tile.transition_tiles;

import game.levels.tile.static_tiles.BasicTile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public abstract class TransitionTiles {

    public enum Variants {
        NWD, NED, SWD, SED,
        NWC, NEC, SWC, SEC,
        NE, WE, EE, SE,
        S1, S2

    }

    public TransitionTiles() {
        tiles = new HashMap<>();
        setTiles();
    }

    protected Map<Variants, BasicTile> tiles;

    abstract public void setTiles();

    public BasicTile getTile(Variants variant) {
        return this.tiles.get(variant);
    }
}
