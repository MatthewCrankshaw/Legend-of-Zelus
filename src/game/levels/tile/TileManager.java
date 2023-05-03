package game.levels.tile;

import game.animators.tile_animators.TransitionTileAnimator;
import game.levels.tile.animated_tiles.AnimatedTile;
import game.levels.tile.animated_transition_tiles.AnimatedTransitionTiles;
import game.levels.tile.transition_tiles.TransitionTiles;

import java.util.Map;

/**
 * Created by Matthew.c on 02/03/2017.
 */
public class TileManager {

    public static final int STONE = 0xff494949;
    public static final int GRASS = 0xff0e7700;
    public static final int MUD = 0xff593000;
    public static final int VOIDTILE = 0xffff00ff;
    public static final int SAND_TO_WATER = 0xff0022ff;
    public static final int GRASS_TO_SAND = 0xffcfb579;
    public static final int GRASS_TO_DIRT = 0xff5c4500;
    public static final int DIRT_TO_GRASS = 0xff204600;
    TransitionTileAnimator tileAnimator = new TransitionTileAnimator();
    private int numXTiles;
    private int numYTiles;
    protected int[] tiles;

    public enum TileType {
        VOID,
        STONE,
        GRASS,
        WOOD_FLOOR,
        SAND_STONE
    }

    public enum TransitionTileTypes {
        GRASS_TO_SAND,
        DIRT_TO_GRASS,
        GRASS_TO_DIRT
    }

    public enum AnimatedTileTypes {
        MUD,
        SWIMMING,
    }

    public enum AnimatedTransitionTileTypes {
        SAND_TO_WATER
    }

    protected Map<TileType, Tile> tileTypes;

    protected Map<TransitionTileTypes, TransitionTiles> transitionTypes;

    protected Map<AnimatedTileTypes, AnimatedTile> animatedTypes;

    protected Map<AnimatedTransitionTileTypes, AnimatedTransitionTiles> animatedTransitionTypes;

    public TileManager(
        Map<TileType, Tile> tileTypes,
        Map<TransitionTileTypes, TransitionTiles> transitionTypes,
        Map<AnimatedTileTypes, AnimatedTile> animatedTypes,
        Map<AnimatedTransitionTileTypes, AnimatedTransitionTiles> animatedTransitionTypes
    ) {
        this.tileTypes = tileTypes;
        this.transitionTypes = transitionTypes;
        this.animatedTypes = animatedTypes;
        this.animatedTransitionTypes = animatedTransitionTypes;
    }

    public void setDimensions(int x, int y) {
        this.numXTiles = x;
        this.numYTiles = y;
    }

    public int[] getTiles() {
        return this.tiles;
    }

    public AnimatedTile getAnimatedTile(AnimatedTileTypes type) {
        return this.animatedTypes.get(type);
    }

    public AnimatedTransitionTiles getAnimatedTransitionTiles(AnimatedTransitionTileTypes type) {
        return this.animatedTransitionTypes.get(type);
    }

    public void setTiles(int[] tiles) {
        this.tiles = tiles;
    }

    public Tile getTile(int x , int y){
        if (x < 0 || y < 0 || x >= numXTiles || y >= numYTiles){
            return tileTypes.get(TileType.VOID);
        }else if(tiles[x+y*numXTiles] == STONE){
            return tileTypes.get(TileType.STONE);
        }else if(tiles[x+y*numXTiles] == GRASS){
            return tileTypes.get(TileType.GRASS);
        }else if(tiles[x+y*numXTiles] == SAND_TO_WATER){
            return getAnimatedTransitionTileVariant(x,y, SAND_TO_WATER, GRASS_TO_SAND, animatedTransitionTypes.get(AnimatedTransitionTileTypes.SAND_TO_WATER));
        }else if(tiles[x+y*numXTiles] == MUD){
            return animatedTypes.get(AnimatedTileTypes.MUD);
        }else if(tiles[x+y*numXTiles] == GRASS_TO_SAND) {
            return getTransitionTileVariant(x, y, GRASS_TO_SAND, GRASS, transitionTypes.get(TransitionTileTypes.GRASS_TO_SAND));
        }else if(tiles[x+y*numXTiles] == GRASS_TO_DIRT) {
            return getTransitionTileVariant(x, y, GRASS_TO_DIRT, GRASS, transitionTypes.get(TransitionTileTypes.GRASS_TO_DIRT));
        }else if(tiles[x+y*numXTiles] == DIRT_TO_GRASS){
            return getTransitionTileVariant(x, y, DIRT_TO_GRASS, GRASS_TO_DIRT, transitionTypes.get(TransitionTileTypes.GRASS_TO_DIRT));
        }else{
            return tileTypes.get(TileType.VOID);
        }
    }

    private Tile getAnimatedTransitionTileVariant(int x, int y, int colour, int transitionTileCol, AnimatedTransitionTiles tile){
        switch (findTileType(x, y, colour, transitionTileCol)){
            case S1:
                return tile.getSolidTile1()[tileAnimator.getCurrentAnimationIndex()];
            case S2:
                return tile.getSolidTile2()[tileAnimator.getCurrentAnimationIndex()];
            case NWD:
                return tile.getNWDiagonalTile()[tileAnimator.getCurrentAnimationIndex()];
            case NED:
                return tile.getNEDiagonalTile()[tileAnimator.getCurrentAnimationIndex()];
            case SWD:
                return tile.getSWDiagonalTile()[tileAnimator.getCurrentAnimationIndex()];
            case SED:
                return tile.getSEDiagonalTile()[tileAnimator.getCurrentAnimationIndex()];
            case NWC:
                return tile.getNWCornerTile()[tileAnimator.getCurrentAnimationIndex()];
            case NEC:
                return tile.getNECornerTile()[tileAnimator.getCurrentAnimationIndex()];
            case SWC:
                return tile.getSWCornerTile()[tileAnimator.getCurrentAnimationIndex()];
            case SEC:
                return tile.getSECornerTile()[tileAnimator.getCurrentAnimationIndex()];
            case NE:
                return tile.getNorthEdgeTile()[tileAnimator.getCurrentAnimationIndex()];
            case SE:
                return tile.getSouthEdgeTile()[tileAnimator.getCurrentAnimationIndex()];
            case EE:
                return tile.getEastEdgeTile()[tileAnimator.getCurrentAnimationIndex()];
            case WE:
                return tile.getWestEdgeTile()[tileAnimator.getCurrentAnimationIndex()];
            default:
                return null;
        }
    }

    private Tile getTransitionTileVariant(int x, int y, int colour, int transitionTileCol, TransitionTiles tile){
        TransitionTiles.Variants variant = findTileType(x, y, colour, transitionTileCol);
        return tile.getTile(variant);
    }

    private TransitionTiles.Variants findTileType(int x, int y, int colour, int transitionTileCol){

        //*********************************************************************
        //See if it is the solid tile
        //*********************************************************************

        if (tiles[(x-1) + (y-1) * numXTiles] == colour &&
                tiles[(x+1) + (y-1) * numXTiles] == colour &&
                tiles[(x-1) + (y+1) * numXTiles] == colour &&
                tiles[(x+1) + (y+1) * numXTiles] == colour) {

            if(x % 2 == 0 && y % 2 != 0){
                return TransitionTiles.Variants.S1;
            }else if (x % 2 == 0 && y % 2 == 0){
                return TransitionTiles.Variants.S2;
            }else if(x % 2 != 0 && y % 2 == 0){
                return TransitionTiles.Variants.S1;
            }else{
                return TransitionTiles.Variants.S2;
            }
        }

        //********************************************************************
        //See if it is the Edge tile
        //********************************************************************

        //if it is North Edge
        else if(tiles[(x) + (y-1) * numXTiles] == transitionTileCol &&
                tiles[(x-1) + (y) * numXTiles] == colour &&
                tiles[(x+1) + (y) * numXTiles] == colour){
            return TransitionTiles.Variants.NE;
        }
        //if it is South Edge
        else if(tiles[(x) + (y+1) * numXTiles] == transitionTileCol &&
                tiles[(x-1) + (y) * numXTiles] == colour &&
                tiles[(x+1) + (y) * numXTiles] == colour){
            return TransitionTiles.Variants.SE;
        }
        // if it is West Edge
        else if (tiles[(x-1) + (y) * numXTiles] == transitionTileCol &&
                tiles[(x) + (y-1) * numXTiles] == colour &&
                tiles[(x) + (y+1) * numXTiles] == colour){
            return TransitionTiles.Variants.WE;

        }
        // if it is East Edge
        else if (tiles[(x+1) + (y) * numXTiles] == transitionTileCol &&
                tiles[(x) + (y-1) * numXTiles] == colour &&
                tiles[(x) + (y+1) * numXTiles] == colour){
            return TransitionTiles.Variants.EE;
        }

        //********************************************************************
        //see if it is a corner tile
        //********************************************************************

        //if it is north west corner
        else if (tiles[(x) + (y+1) * numXTiles] == colour &&
                tiles[(x+1) + (y) * numXTiles] == colour &&
                tiles[(x) + (y-1) * numXTiles] == transitionTileCol &&
                tiles[(x-1) + (y) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.NWC;
        }
        //if it is north east corner
        else if (tiles[(x) + (y+1) * numXTiles] == colour &&
                tiles[(x-1) + (y) * numXTiles] == colour &&
                tiles[(x) + (y-1) * numXTiles] == transitionTileCol &&
                tiles[(x+1) + (y) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.NEC;
        }
        //if it is south west corner
        else if (tiles[(x) + (y-1) * numXTiles] == colour &&
                tiles[(x+1) + (y) * numXTiles] == colour &&
                tiles[(x) + (y+1) * numXTiles] == transitionTileCol &&
                tiles[(x-1) + (y) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.SWC;
        }
        //if it is south east corner
        else if (tiles[(x) + (y-1) * numXTiles] == colour &&
                tiles[(x-1) + (y) * numXTiles] == colour &&
                tiles[(x) + (y+1) * numXTiles] == transitionTileCol &&
                tiles[(x+1) + (y) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.SEC;
        }

        //********************************************************************
        //see if it is a diagonal Tile
        //********************************************************************


        //if it is North West Diagonal
        else if (tiles[(x-1) + (y+1) * numXTiles] == colour &&
                tiles[(x+1) + (y-1) * numXTiles] == colour &&
                tiles[(x-1) + (y-1) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.NWD;
        }

        //if it is North East Diagonal
        else if (tiles[(x-1) + (y-1) * numXTiles] == colour &&
                tiles[(x+1) + (y+1) * numXTiles] == colour &&
                tiles[(x+1) + (y-1) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.NED;
        }

        //if it is South West Diagonal
        else if (tiles[(x-1) + (y-1) * numXTiles] == colour &&
                tiles[(x+1) + (y+1) * numXTiles] == colour &&
                tiles[(x-1) + (y+1) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.SWD;
        }

        //if it is South East Diagonal
        else if (tiles[(x-1) + (y+1) * numXTiles] == colour &&
                tiles[(x+1) + (y-1) * numXTiles] == colour &&
                tiles[(x+1) + (y+1) * numXTiles] == transitionTileCol) {
            return TransitionTiles.Variants.SED;
        }
        return TransitionTiles.Variants.S1;
    }

    public void tick() {
        for (AnimatedTile tile : animatedTypes.values()) {
            tile.tick();
        }
    }
}
