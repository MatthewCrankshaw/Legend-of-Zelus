package game.graphics.sprite;


import game.graphics.SpriteSheet;
import game.levels.tile.TileConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteRegistry {

    public enum SpriteItem {
        RED_PARTICLE,
        GREEN_PARTICLE,
        RED_PARTICLE_2,
        TREE,
        TELEPORT_FLOOR_SIGN,
        GRASS,
        STONE,
        WOOD_FLOOR,
        SAND,
        SAND_STONE,
        VOID,
    }

    public enum AnimatedEnvSprite {
        FIREBALL_FLOOR_SIGN,
        FIREBALL_SPRITES,
        GREEN_FIREBALL_SPRITES,
        SAND_TO_WATER_NWD, SAND_TO_WATER_NED, SAND_TO_WATER_SWD, SAND_TO_WATER_SED,
        SAND_TO_WATER_NWC, SAND_TO_WATER_NEC, SAND_TO_WATER_SWC, SAND_TO_WATER_SEC,
        SAND_TO_WATER_NE, SAND_TO_WATER_SE, SAND_TO_WATER_WE, SAND_TO_WATER_EE,
        SAND_TO_WATER_S1, SAND_TO_WATER_S2,
        PLAYER_ATTACKS,
        SWIMMING,
        TELEPORT,
        WATER,
        MUD,
    }

    public enum AnimatedCharacterSprites {
        WIZARD,
        ZOMBIE,
        ENEMY,
        DEATH_KEEPER
    }

    protected SpriteLoader loader;

    protected Map<SpriteItem, Sprite> sprites = new HashMap<>();

    protected Map<AnimatedEnvSprite, Sprite[]> spriteCollections = new HashMap<>();

    protected Map<AnimatedCharacterSprites, Sprite[][]> characterSprites = new HashMap<>();

    protected ArrayList<Sprite> fonts;

    public SpriteRegistry(SpriteSheetRegistry registry, SpriteLoader loader){
        this.loader = loader;
        SpriteSheet environment8 = registry.get(SpriteSheetRegistry.SpriteSheetItem.ENVIRONMENT_8);
        sprites.put(SpriteItem.GRASS, loader.load(8, 2, 0, environment8));
        sprites.put(SpriteItem.STONE, loader.load(8, 1, 0, environment8));
        sprites.put(SpriteItem.WOOD_FLOOR,loader.load(8, 5, 0, environment8));
        sprites.put(SpriteItem.SAND, loader.load(8, 6, 0, environment8));
        sprites.put(SpriteItem.SAND_STONE, loader.load(8, 7, 0, environment8));
        sprites.put(SpriteItem.VOID, loader.loadColour(8, 0x1B87E0));

        sprites.put(SpriteItem.RED_PARTICLE, loader.loadColour(1, 0x9C2A00));
        sprites.put(SpriteItem.GREEN_PARTICLE, loader.loadColour(1, 0x00aa00));
        sprites.put(SpriteItem.RED_PARTICLE_2, loader.loadColour(2, 0xff0000));
        sprites.put(SpriteItem.TREE, loader.load(32,0, 0, registry.get(SpriteSheetRegistry.SpriteSheetItem.ENVIRONMENT_32)));
        sprites.put(SpriteItem.TELEPORT_FLOOR_SIGN, loader.load(32, 0, 0,  registry.get(SpriteSheetRegistry.SpriteSheetItem.ABILITIES_32)));

        SpriteSheet abilities = registry.get(SpriteSheetRegistry.SpriteSheetItem.ABILITIES_8);
        spriteCollections.put(AnimatedEnvSprite.FIREBALL_SPRITES, new Sprite[]{
            loader.load(8, 0, 0, abilities),
            loader.load(8, 1, 0, abilities),
            loader.load(8, 2, 0, abilities)
        });

        spriteCollections.put(AnimatedEnvSprite.GREEN_FIREBALL_SPRITES, new Sprite[]{
            loader.load(8,3, 0, abilities),
            loader.load(8,4, 0, abilities),
            loader.load(8,5, 0, abilities)
        });

        SpriteSheet sandToWater1 = registry.get(SpriteSheetRegistry.SpriteSheetItem.SAND_TO_WATER_1);
        SpriteSheet sandToWater2 = registry.get(SpriteSheetRegistry.SpriteSheetItem.SAND_TO_WATER_2);
        SpriteSheet sandToWater3 = registry.get(SpriteSheetRegistry.SpriteSheetItem.SAND_TO_WATER_3);
        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_NWD, new Sprite[]{
                loader.load(8, 0,0, sandToWater1),
                loader.load(8, 0,0, sandToWater2),
                loader.load(8, 0,0, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_NED, new Sprite[]{
                loader.load(8, 1,0, sandToWater1),
                loader.load(8, 1,0, sandToWater2),
                loader.load(8, 1,0, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_SWD, new Sprite[]{
                loader.load(8, 0,1, sandToWater1),
                loader.load(8, 0,1, sandToWater2),
                loader.load(8, 0,1, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_SED, new Sprite[]{
                loader.load(8, 1,1, sandToWater1),
                loader.load(8, 1,1, sandToWater2),
                loader.load(8, 1,1, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_NWC, new Sprite[]{
                loader.load(8, 2,0, sandToWater1),
                loader.load(8, 2,0, sandToWater2),
                loader.load(8, 2,0, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_NEC, new Sprite[]{
                loader.load(8, 3,0, sandToWater1),
                loader.load(8, 3,0, sandToWater2),
                loader.load(8, 3,0, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_SWC, new Sprite[]{
                loader.load(8, 2,1, sandToWater1),
                loader.load(8, 2,1, sandToWater2),
                loader.load(8, 2,1, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_SEC, new Sprite[]{
                loader.load(8, 3,1, sandToWater1),
                loader.load(8, 3,1, sandToWater2),
                loader.load(8, 3,1, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_NE, new Sprite[]{
                loader.load(8, 1,2, sandToWater1),
                loader.load(8, 1,2, sandToWater2),
                loader.load(8, 1,2, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_WE, new Sprite[]{
                loader.load(8, 0,3, sandToWater1),
                loader.load(8, 0,3, sandToWater2),
                loader.load(8, 0,3, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_EE, new Sprite[]{
                loader.load(8, 3,3, sandToWater1),
                loader.load(8, 3,3, sandToWater2),
                loader.load(8, 3,3, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_SE, new Sprite[]{
                loader.load(8, 1,5, sandToWater1),
                loader.load(8, 1,5, sandToWater2),
                loader.load(8, 1,5, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_S1, new Sprite[]{
                loader.load(8, 1,3, sandToWater1),
                loader.load(8, 1,3, sandToWater2),
                loader.load(8, 1,3, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.SAND_TO_WATER_S2, new Sprite[]{
                loader.load(8, 2,3, sandToWater1),
                loader.load(8, 2,3, sandToWater2),
                loader.load(8, 2,3, sandToWater3)
        });

        spriteCollections.put(AnimatedEnvSprite.PLAYER_ATTACKS, loadAnimatedSprite(TileConstants.TILE_SIZE*2, new int[]{0,1,2}, new int[]{0,0,0}, registry.get(SpriteSheetRegistry.SpriteSheetItem.CHARACTER_ANIMS)));
        spriteCollections.put(AnimatedEnvSprite.SWIMMING, loadAnimatedSprite(16, 0, 0, 3, registry.get(SpriteSheetRegistry.SpriteSheetItem.STATUS_AFFECTS_16)));
        spriteCollections.put(AnimatedEnvSprite.TELEPORT, loadAnimatedSprite(16, 3, 0, 4, registry.get(SpriteSheetRegistry.SpriteSheetItem.CHARACTER_ANIMS)));
        spriteCollections.put(AnimatedEnvSprite.FIREBALL_FLOOR_SIGN, loadAnimatedSprite(32, 4, 1, 5, registry.get(SpriteSheetRegistry.SpriteSheetItem.ABILITIES_32)));
        spriteCollections.put(AnimatedEnvSprite.WATER, loadAnimatedSprite(8, 0, 31, 3, registry.get(SpriteSheetRegistry.SpriteSheetItem.ENVIRONMENT_8)));
        spriteCollections.put(AnimatedEnvSprite.MUD, loadAnimatedSprite(8,3,31,3, registry.get(SpriteSheetRegistry.SpriteSheetItem.ENVIRONMENT_8)));

        this.fonts = loadCharSprites(registry);

        this.characterSprites.put(AnimatedCharacterSprites.WIZARD, loadPlayer(0, registry));
        this.characterSprites.put(AnimatedCharacterSprites.ZOMBIE, loadPlayer(1, registry));
        this.characterSprites.put(AnimatedCharacterSprites.ENEMY, loadPlayer(2, registry));
        this.characterSprites.put(AnimatedCharacterSprites.DEATH_KEEPER, loadPlayer(3, registry));
    }

    public Sprite get(SpriteItem item){
        return sprites.get(item);
    }

    public Sprite[] getCollection(AnimatedEnvSprite item){
        return spriteCollections.get(item);
    }

    protected Sprite[] loadAnimatedSprite(int size, int[] xLocations, int [] yLocations, SpriteSheet sheet){
        Sprite[] sprites = new Sprite[xLocations.length];
        for (int i=0; i < xLocations.length; i++) {
            sprites[i] = this.loader.load(size, xLocations[i], yLocations[i], sheet);
        }
        return sprites;
    }

    protected Sprite[] loadAnimatedSprite(int size, int startXPos, int startYPos, int numOfSprites, SpriteSheet sheet){
        Sprite[] sprites = new Sprite[numOfSprites];
        for(int i = 0; i < numOfSprites; i++) {
            sprites[i] = this.loader.load(size, startXPos + i, startYPos, sheet);
        }
        return sprites;
    }

    protected ArrayList<Sprite> loadCharSprites(SpriteSheetRegistry registry){
        SpriteSheet text = registry.get(SpriteSheetRegistry.SpriteSheetItem.TEXT);
        ArrayList<Sprite> font = new ArrayList<>();
        //Letters
        for(int i = 0; i < 26; i++){
            font.add( loader.load(8, i, 0, text));
        }
        //Digits and Signs
        for(int i = 0; i < 26; i++){
            font.add(loader.load(8, i, 1, text));
        }
        return font;
    }

    public Sprite getCharacterSprite(char letter){
        String charOrder = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,:;'\"!?$%()-=+/";

        letter = Character.toUpperCase(letter);
        int i = charOrder.indexOf(letter);
        if(i == -1){
            System.out.println("Error: invalid character for FontSprite. Character:" + letter);
            System.exit(1);
        }
        return fonts.get(i);
    }

    protected Sprite[][] loadPlayer(int spriteNum, SpriteSheetRegistry registry){
        //spriteNum is the number that the sprite is on the y axis of the sprite page

        Sprite[][] sprites = new Sprite[4][4];
        for (int anim = 0; anim < 4; anim++) {
            sprites[anim] = loadAnimatedSprite(TileConstants.TILE_SIZE*2, anim * 4, spriteNum, 4, registry.get(SpriteSheetRegistry.SpriteSheetItem.CHARACTER_MOVEMENT));
        }
        return sprites;
    }

    public Sprite[][] getCharacter(AnimatedCharacterSprites type){
        return characterSprites.get(type);
    }
}
