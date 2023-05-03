package game.graphics.sprite;


import game.graphics.SpriteSheet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteSheetRegistry {

    public enum SpriteSheetItem {
        CHARACTER_ANIMS,
        CHARACTER_MOVEMENT,
        GRASS_TO_SAND,
        DIRT_TO_GRASS,
        GRASS_TO_DIRT,
        SAND_TO_WATER_1,
        SAND_TO_WATER_2,
        SAND_TO_WATER_3,
        ENVIRONMENT_8,
        ENVIRONMENT_16,
        ENVIRONMENT_32,
        ABILITIES_8,
        ABILITIES_16,
        ABILITIES_32,
        STATUS_AFFECTS_16,
        TEXT,
    }

    protected Map<SpriteSheetItem, SpriteSheet> spriteSheets = new HashMap<>();

    public SpriteSheetRegistry(){
        this.spriteSheets.put(SpriteSheetItem.CHARACTER_ANIMS, new SpriteSheet("/Character/CharacterAnimations-16.png", 256));
        this.spriteSheets.put(SpriteSheetItem.CHARACTER_MOVEMENT, new SpriteSheet("/Character/CharacterMovement-16.png", 256));
        this.spriteSheets.put(SpriteSheetItem.GRASS_TO_SAND, new SpriteSheet("/Terrain/grass_to_sand.png", 48));
        this.spriteSheets.put(SpriteSheetItem.DIRT_TO_GRASS, new SpriteSheet("/Terrain/dirt_to_grass.png", 48));
        this.spriteSheets.put(SpriteSheetItem.GRASS_TO_DIRT, new SpriteSheet("/Terrain/grass_to_dirt.png", 48));
        this.spriteSheets.put(SpriteSheetItem.SAND_TO_WATER_1, new SpriteSheet("/Terrain/water_sprites/sand_to_water1.png", 48));
        this.spriteSheets.put(SpriteSheetItem.SAND_TO_WATER_2, new SpriteSheet("/Terrain/water_sprites/sand_to_water2.png", 48));
        this.spriteSheets.put(SpriteSheetItem.SAND_TO_WATER_3, new SpriteSheet("/Terrain/water_sprites/sand_to_water3.png", 48));
        this.spriteSheets.put(SpriteSheetItem.ENVIRONMENT_8, new SpriteSheet("/Environment/Environment-8.png", 256));
        this.spriteSheets.put(SpriteSheetItem.ENVIRONMENT_16, new SpriteSheet("/Environment/Environment-16.png", 256));
        this.spriteSheets.put(SpriteSheetItem.ENVIRONMENT_32, new SpriteSheet("/Environment/Environment-32.png", 256));
        this.spriteSheets.put(SpriteSheetItem.ABILITIES_8, new SpriteSheet("/AbilityEffects/AbilityEffects-8.png", 256));
        this.spriteSheets.put(SpriteSheetItem.ABILITIES_16, new SpriteSheet("/AbilityEffects/AbilityEffects-16.png", 256));
        this.spriteSheets.put(SpriteSheetItem.ABILITIES_32, new SpriteSheet("/AbilityEffects/AbilityEffects-32.png", 288));
        this.spriteSheets.put(SpriteSheetItem.STATUS_AFFECTS_16, new SpriteSheet("/StatusEffects/StatusEffects-16.png", 256));
        this.spriteSheets.put(SpriteSheetItem.TEXT, new SpriteSheet("/Text/Text.png", 256));
    }

    public SpriteSheet get(SpriteSheetItem item) {
        return this.spriteSheets.get(item);
    }
}
