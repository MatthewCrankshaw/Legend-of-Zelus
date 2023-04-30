package game.levels.tile.transition_tiles;

import game.graphics.SpriteSheet;
import game.graphics.sprite.Sprite;
import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class GrassToSandTiles extends TransitionTiles {

    public GrassToSandTiles() {
    }

    @Override
    public void setTiles(){
        Sprite NW_Diagonal = new Sprite(8, 0, 0, SpriteSheet.grassToSand);
        Sprite NE_Diagonal = new Sprite(8, 1, 0, SpriteSheet.grassToSand);
        Sprite SW_Diagonal = new Sprite(8, 0, 1, SpriteSheet.grassToSand);
        Sprite SE_Diagonal = new Sprite(8, 1, 1, SpriteSheet.grassToSand);

        Sprite NW_Corner = new Sprite(8, 2, 0, SpriteSheet.grassToSand);
        Sprite NE_Corner = new Sprite(8, 3, 0, SpriteSheet.grassToSand);
        Sprite SW_Corner = new Sprite(8, 2, 1, SpriteSheet.grassToSand);
        Sprite SE_Corner = new Sprite(8, 3, 1, SpriteSheet.grassToSand);

        Sprite northEdge = new Sprite(8, 1, 2, SpriteSheet.grassToSand);
        Sprite westEdge = new Sprite(8, 0, 3, SpriteSheet.grassToSand);
        Sprite eastEdge = new Sprite(8, 3, 3, SpriteSheet.grassToSand);
        Sprite southEdge = new Sprite(8, 1, 5, SpriteSheet.grassToSand);

        Sprite solidSand1 = new Sprite(8, 1, 3, SpriteSheet.grassToSand);
        Sprite solidSand2 = new Sprite(8, 2, 3, SpriteSheet.grassToSand);

        tiles.put(Variants.NWD, new BasicTile(NW_Diagonal));
        tiles.put(Variants.NED, new BasicTile(NE_Diagonal));
        tiles.put(Variants.SWD, new BasicTile(SW_Diagonal));
        tiles.put(Variants.SED, new BasicTile(SE_Diagonal));

        tiles.put(Variants.NWC, new BasicTile(NW_Corner));
        tiles.put(Variants.NEC, new BasicTile(NE_Corner));
        tiles.put(Variants.SWC, new BasicTile(SW_Corner));
        tiles.put(Variants.SEC, new BasicTile(SE_Corner));

        tiles.put(Variants.NE, new BasicTile(northEdge));
        tiles.put(Variants.WE, new BasicTile(westEdge));
        tiles.put(Variants.EE, new BasicTile(eastEdge));
        tiles.put(Variants.SE, new BasicTile(southEdge));

        tiles.put(Variants.S1, new BasicTile(solidSand1));
        tiles.put(Variants.S2, new BasicTile(solidSand2));
    }
}
