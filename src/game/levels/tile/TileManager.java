package game.levels.tile;

import game.animators.tile_animators.TransitionTileAnimator;
import game.levels.Level;
import game.levels.tile.animated_transition_tiles.AnimatedTransitionTiles;
import game.levels.tile.transition_tiles.TransitionTiles;

import static game.levels.tile.TileManager.tileType.*;

/**
 * Created by Matthew.c on 02/03/2017.
 */
public class TileManager {

    public enum tileType {
        NW_DIAG, NE_DIAG, SW_DIAG, SE_DIAG,
        NW_CRNR, NE_CRNR, SW_CRNR, SE_CRNR,
        N_EDGE, W_EDGE, E_EDGE, S_EDGE,
        S1, S2
    }

    public static final int STONE = 0xff494949;
    public static final int GRASS = 0xff0e7700;
    public static final int MUD = 0xff593000;
    public static final int WOODFLOOR = 0xffae5d17;
    public static final int SANDSTONE = 0xffe6cd91;
    public static final int VOIDTILE = 0xffff00ff;


    public static final int SAND_TO_WATER = 0xff0022ff;
    public static final int GRASS_TO_SAND = 0xffcfb579;

    TransitionTileAnimator tileAnimator = new TransitionTileAnimator();


    protected int numXTiles;
    protected int numYTiles;
    public static int[] tiles;

    public TileManager(int numXTiles, int numYTiles){
        this.numXTiles = numXTiles;
        this.numYTiles = numYTiles;
    }

    public Tile getTile(int x , int y){
        if (x < 0 || y < 0 || x >= numXTiles || y >= numYTiles){
            return Tile.voidTile;
        }else if(tiles[x+y*numXTiles] == STONE){
            return Tile.stone;
        }else if(tiles[x+y*numXTiles] == GRASS){
            return Tile.grass;
        }else if(tiles[x+y*numXTiles] == SAND_TO_WATER){
            Tile temp = getAnimatedTransitionTileVariant(x,y, SAND_TO_WATER, GRASS_TO_SAND, Tile.sandToWaterTiles);
            return temp;
        }else if(tiles[x+y*numXTiles] == MUD){
            return Tile.mud;
        }else if(tiles[x+y*numXTiles] == WOODFLOOR){
            return Tile.woodFloor;
        }else if(tiles[x+y*numXTiles] == GRASS_TO_SAND){
            return getTransitionTileVariant(x, y, GRASS_TO_SAND, GRASS, Tile.grassToSandTiles);
        }else if(tiles[x+y*numXTiles] == SANDSTONE){
            return Tile.sandStone;
        }else{
            return Tile.voidTile;
        }
    }


    private Tile getAnimatedTransitionTileVariant(int x, int y, int colour, int transitionTileCol, AnimatedTransitionTiles tile){
        switch (findTileType(x, y, colour, transitionTileCol)){
            case S1:
                return tile.solidTile1[tileAnimator.getCurrentAnimationIndex()];
            case S2:
                return tile.solidTile2[tileAnimator.getCurrentAnimationIndex()];
            case NW_DIAG:
                return tile.NW_DiagonalTile[tileAnimator.getCurrentAnimationIndex()];
            case NE_DIAG:
                return tile.NE_DiagonalTile[tileAnimator.getCurrentAnimationIndex()];
            case SW_DIAG:
                return tile.SW_DiagonalTile[tileAnimator.getCurrentAnimationIndex()];
            case SE_DIAG:
                return tile.SE_DiagonalTile[tileAnimator.getCurrentAnimationIndex()];
            case NW_CRNR:
                return tile.NW_CornerTile[tileAnimator.getCurrentAnimationIndex()];
            case NE_CRNR:
                return tile.NE_CornerTile[tileAnimator.getCurrentAnimationIndex()];
            case SW_CRNR:
                return tile.SW_CornerTile[tileAnimator.getCurrentAnimationIndex()];
            case SE_CRNR:
                return tile.SE_CornerTile[tileAnimator.getCurrentAnimationIndex()];
            case N_EDGE:
                return tile.northEdgeTile[tileAnimator.getCurrentAnimationIndex()];
            case S_EDGE:
                return tile.southEdgeTile[tileAnimator.getCurrentAnimationIndex()];
            case E_EDGE:
                return tile.eastEdgeTile[tileAnimator.getCurrentAnimationIndex()];
            case W_EDGE:
                return tile.westEdgeTile[tileAnimator.getCurrentAnimationIndex()];
            default:
                return null;
        }
    }

    private Tile getTransitionTileVariant(int x, int y, int colour, int transitionTileCol, TransitionTiles tile){
        switch (findTileType(x, y, colour, transitionTileCol)){
            case S1:
                return tile.solidTile1;
            case S2:
                return tile.solidTile2;
            case NW_DIAG:
                return tile.NW_DiagonalTile;
            case NE_DIAG:
                return tile.NE_DiagonalTile;
            case SW_DIAG:
                return tile.SW_DiagonalTile;
            case SE_DIAG:
                return tile.SE_DiagonalTile;
            case NW_CRNR:
                return tile.NW_CornerTile;
            case NE_CRNR:
                return tile.NE_CornerTile;
            case SW_CRNR:
                return tile.SW_CornerTile;
            case SE_CRNR:
                return tile.SE_CornerTile;
            case N_EDGE:
                return tile.northEdgeTile;
            case S_EDGE:
                return tile.southEdgeTile;
            case E_EDGE:
                return tile.eastEdgeTile;
            case W_EDGE:
                return tile.westEdgeTile;
            default:
                return null;
        }
    }

    private tileType findTileType(int x, int y, int colour, int transitionTileCol){
        try{
            //*********************************************************************
            //See if it is the solid tile
            //*********************************************************************

            if (tiles[(x-1) + (y-1) * numXTiles] == colour &&
                    tiles[(x+1) + (y-1) * numXTiles] == colour &&
                    tiles[(x-1) + (y+1) * numXTiles] == colour &&
                    tiles[(x+1) + (y+1) * numXTiles] == colour) {

                if(x % 2 == 0 && y % 2 != 0){
                    return tileType.S1;
                }else if (x % 2 == 0 && y % 2 == 0){
                    return tileType.S2;
                }else if(x % 2 != 0 && y % 2 == 0){
                    return tileType.S1;
                }else{
                    return tileType.S2;
                }
            }

            //********************************************************************
            //See if it is the Edge tile
            //********************************************************************

            //if it is North Edge
            else if(tiles[(x) + (y-1) * numXTiles] == transitionTileCol &&
                    tiles[(x-1) + (y) * numXTiles] == colour &&
                    tiles[(x+1) + (y) * numXTiles] == colour){
                return tileType.N_EDGE;
            }
            //if it is South Edge
            else if(tiles[(x) + (y+1) * numXTiles] == transitionTileCol &&
                    tiles[(x-1) + (y) * numXTiles] == colour &&
                    tiles[(x+1) + (y) * numXTiles] == colour){
                return tileType.S_EDGE;
            }
            // if it is West Edge
            else if (tiles[(x-1) + (y) * numXTiles] == transitionTileCol &&
                    tiles[(x) + (y-1) * numXTiles] == colour &&
                    tiles[(x) + (y+1) * numXTiles] == colour){
                return tileType.W_EDGE;

            }
            // if it is East Edge
            else if (tiles[(x+1) + (y) * numXTiles] == transitionTileCol &&
                    tiles[(x) + (y-1) * numXTiles] == colour &&
                    tiles[(x) + (y+1) * numXTiles] == colour){
                return tileType.E_EDGE;
            }

            //********************************************************************
            //see if it is a corner tile
            //********************************************************************

            //if it is north west corner
            else if (tiles[(x) + (y+1) * numXTiles] == colour &&
                    tiles[(x+1) + (y) * numXTiles] == colour &&
                    tiles[(x) + (y-1) * numXTiles] == transitionTileCol &&
                    tiles[(x-1) + (y) * numXTiles] == transitionTileCol) {
                return tileType.NW_CRNR;
            }
            //if it is north east corner
            else if (tiles[(x) + (y+1) * numXTiles] == colour &&
                    tiles[(x-1) + (y) * numXTiles] == colour &&
                    tiles[(x) + (y-1) * numXTiles] == transitionTileCol &&
                    tiles[(x+1) + (y) * numXTiles] == transitionTileCol) {
                return tileType.NE_CRNR;
            }
            //if it is south west corner
            else if (tiles[(x) + (y-1) * numXTiles] == colour &&
                    tiles[(x+1) + (y) * numXTiles] == colour &&
                    tiles[(x) + (y+1) * numXTiles] == transitionTileCol &&
                    tiles[(x-1) + (y) * numXTiles] == transitionTileCol) {
                return tileType.SW_CRNR;
            }
            //if it is south east corner
            else if (tiles[(x) + (y-1) * numXTiles] == colour &&
                    tiles[(x-1) + (y) * numXTiles] == colour &&
                    tiles[(x) + (y+1) * numXTiles] == transitionTileCol &&
                    tiles[(x+1) + (y) * numXTiles] == transitionTileCol) {
                return tileType.SE_CRNR;
            }

            //********************************************************************
            //see if it is a diagonal Tile
            //********************************************************************


            //if it is North West Diagonal
            else if (tiles[(x-1) + (y+1) * numXTiles] == colour &&
                    tiles[(x+1) + (y-1) * numXTiles] == colour &&
                    tiles[(x-1) + (y-1) * numXTiles] == transitionTileCol) {
                return tileType.NW_DIAG;
            }

            //if it is North East Diagonal
            else if (tiles[(x-1) + (y-1) * numXTiles] == colour &&
                    tiles[(x+1) + (y+1) * numXTiles] == colour &&
                    tiles[(x+1) + (y-1) * numXTiles] == transitionTileCol) {
                return tileType.NE_DIAG;
            }

            //if it is South West Diagonal
            else if (tiles[(x-1) + (y-1) * numXTiles] == colour &&
                    tiles[(x+1) + (y+1) * numXTiles] == colour &&
                    tiles[(x-1) + (y+1) * numXTiles] == transitionTileCol) {
                return tileType.SW_DIAG;
            }

            //if it is South East Diagonal
            else if (tiles[(x-1) + (y+1) * numXTiles] == colour &&
                    tiles[(x+1) + (y-1) * numXTiles] == colour &&
                    tiles[(x+1) + (y+1) * numXTiles] == transitionTileCol) {
                return tileType.SE_DIAG;
            }

        }catch (ArrayIndexOutOfBoundsException e ){
        }
        //return GrassToSandTiles.solidTile1;
        return tileType.S1;
    }
}
