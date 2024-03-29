package game.levels.tile.transition_tiles;

import game.graphics.SpriteSheet;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteLoader;
import game.graphics.sprite.SpriteSheetRegistry;
import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class GrassToDirtTiles extends TransitionTiles {

    public GrassToDirtTiles(SpriteSheetRegistry spriteSheet, SpriteLoader loader){
        super(spriteSheet, loader);
    }
    @Override
    public void setTiles() {
        SpriteSheet grassToDirt = spriteSheet.get(SpriteSheetRegistry.SpriteSheetItem.GRASS_TO_DIRT);


        Sprite NW_Diagonal = loader.load(8, 0, 0, grassToDirt);
        Sprite NE_Diagonal = loader.load(8, 1, 0, grassToDirt);
        Sprite SW_Diagonal = loader.load(8, 0, 1, grassToDirt);
        Sprite SE_Diagonal = loader.load(8, 1, 1, grassToDirt);

        Sprite NW_Corner = loader.load(8, 2, 0, grassToDirt);
        Sprite NE_Corner = loader.load(8, 3, 0, grassToDirt);
        Sprite SW_Corner = loader.load(8, 2, 1, grassToDirt);
        Sprite SE_Corner = loader.load(8, 3, 1, grassToDirt);

        Sprite northEdge = loader.load(8, 1, 2, grassToDirt);
        Sprite westEdge = loader.load(8, 0, 3, grassToDirt);
        Sprite eastEdge = loader.load(8, 3, 3, grassToDirt);
        Sprite southEdge = loader.load(8, 1, 5, grassToDirt);

        Sprite solidGrass1 = loader.load(8, 1, 3, grassToDirt);
        Sprite solidGrass2 = loader.load(8, 2, 3, grassToDirt);

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

        tiles.put(Variants.S1, new BasicTile(solidGrass1));
        tiles.put(Variants.S2, new BasicTile(solidGrass2));
    }
}
