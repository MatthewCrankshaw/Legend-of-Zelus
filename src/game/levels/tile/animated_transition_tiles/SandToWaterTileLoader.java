package game.levels.tile.animated_transition_tiles;

import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.levels.tile.static_tiles.BasicTile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class SandToWaterTileLoader {
    protected SpriteRegistry registry;

    public SandToWaterTileLoader(SpriteRegistry registry) {
        this.registry = registry;
        load();
    }

    public AnimatedTransitionTiles load() {

        Sprite[] NWD = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_NWD);
        Sprite[] NED = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_NED);
        Sprite[] SWD = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_SWD);
        Sprite[] SED = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_SED);

        Sprite[] NWC = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_NWC);
        Sprite[] NEC = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_NEC);
        Sprite[] SWC = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_SWC);
        Sprite[] SEC = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_SEC);

        Sprite[] NE = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_NE);
        Sprite[] WE = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_WE);
        Sprite[] EE = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_EE);
        Sprite[] SE = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_SE);

        Sprite[] S1 = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_S1);
        Sprite[] S2 = registry.getCollection(SpriteRegistry.AnimatedEnvSprite.SAND_TO_WATER_S2);

        AnimatedTransitionTiles sandToWaterSprites = new AnimatedTransitionTiles();
        sandToWaterSprites.setNWDiagonalTile(new BasicTile[]{
                new BasicTile(NWD[0]),
                new BasicTile(NWD[1]),
                new BasicTile(NWD[2])
        });

        sandToWaterSprites.setNEDiagonalTile(new BasicTile[]{
                new BasicTile(NED[0]),
                new BasicTile(NED[1]),
                new BasicTile(NED[2])
        });
        sandToWaterSprites.setSWDiagonalTile(new BasicTile[]{
                new BasicTile(NWD[0]),
                new BasicTile(NWD[1]),
                new BasicTile(NWD[2])
        });
        sandToWaterSprites.setSEDiagonalTile(new BasicTile[]{
                new BasicTile(SED[0]),
                new BasicTile(SED[1]),
                new BasicTile(SED[2])
        });

        sandToWaterSprites.setNWCornerTile(new BasicTile[]{
                new BasicTile(NWC[0]),
                new BasicTile(NWC[1]),
                new BasicTile(NWC[2])
        });
        sandToWaterSprites.setNECornerTile(new BasicTile[]{
                new BasicTile(NEC[0]),
                new BasicTile(NEC[1]),
                new BasicTile(NEC[2])
        });
        sandToWaterSprites.setSWCornerTile(new BasicTile[]{
                new BasicTile(SWC[0]),
                new BasicTile(SWC[1]),
                new BasicTile(SWC[2])
        });
        sandToWaterSprites.setSECornerTile(new BasicTile[]{
                new BasicTile(SEC[0]),
                new BasicTile(SEC[1]),
                new BasicTile(SEC[2])
        });

        sandToWaterSprites.setNorthEdgeTile(new BasicTile[]{
                new BasicTile(NE[0]),
                new BasicTile(NE[1]),
                new BasicTile(NE[2]),
        });
        sandToWaterSprites.setWestEdgeTile(new BasicTile[]{
                new BasicTile(WE[0]),
                new BasicTile(WE[1]),
                new BasicTile(WE[2]),
        });
        sandToWaterSprites.setEastEdgeTile(new BasicTile[]{
                new BasicTile(EE[0]),
                new BasicTile(EE[1]),
                new BasicTile(EE[2]),
        });
        sandToWaterSprites.setSouthEdgeTile(new BasicTile[]{
                new BasicTile(SE[0]),
                new BasicTile(SE[1]),
                new BasicTile(SE[2]),
        });

        sandToWaterSprites.setSolidTile1(new BasicTile[]{
                new BasicTile(S1[0]),
                new BasicTile(S1[1]),
                new BasicTile(S1[2])
        });
        sandToWaterSprites.setSolidTile2(new BasicTile[]{
                new BasicTile(S2[0]),
                new BasicTile(S2[1]),
                new BasicTile(S2[2])
        });

        return sandToWaterSprites;
    }
}
