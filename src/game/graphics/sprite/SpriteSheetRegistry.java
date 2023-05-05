package game.graphics.sprite;


import game.graphics.SpriteSheet;
import game.graphics.files.ImageLoader;

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

    public SpriteSheetRegistry(ImageLoader loader){
        this.spriteSheets.put(SpriteSheetItem.CHARACTER_ANIMS, new SpriteSheet(loader, "/Character/CharacterAnimations-16.png"));
        this.spriteSheets.put(SpriteSheetItem.CHARACTER_MOVEMENT, new SpriteSheet(loader, "/Character/CharacterMovement-16.png"));
        this.spriteSheets.put(SpriteSheetItem.GRASS_TO_SAND, new SpriteSheet(loader, "/Terrain/grass_to_sand.png"));
        this.spriteSheets.put(SpriteSheetItem.DIRT_TO_GRASS, new SpriteSheet(loader, "/Terrain/dirt_to_grass.png"));
        this.spriteSheets.put(SpriteSheetItem.GRASS_TO_DIRT, new SpriteSheet(loader, "/Terrain/grass_to_dirt.png"));
        this.spriteSheets.put(SpriteSheetItem.SAND_TO_WATER_1, new SpriteSheet(loader, "/Terrain/water_sprites/sand_to_water1.png"));
        this.spriteSheets.put(SpriteSheetItem.SAND_TO_WATER_2, new SpriteSheet(loader, "/Terrain/water_sprites/sand_to_water2.png"));
        this.spriteSheets.put(SpriteSheetItem.SAND_TO_WATER_3, new SpriteSheet(loader, "/Terrain/water_sprites/sand_to_water3.png"));
        this.spriteSheets.put(SpriteSheetItem.ENVIRONMENT_8, new SpriteSheet(loader, "/Environment/Environment-8.png"));
        this.spriteSheets.put(SpriteSheetItem.ENVIRONMENT_16, new SpriteSheet(loader, "/Environment/Environment-16.png"));
        this.spriteSheets.put(SpriteSheetItem.ENVIRONMENT_32, new SpriteSheet(loader, "/Environment/Environment-32.png"));
        this.spriteSheets.put(SpriteSheetItem.ABILITIES_8, new SpriteSheet(loader, "/AbilityEffects/AbilityEffects-8.png"));
        this.spriteSheets.put(SpriteSheetItem.ABILITIES_16, new SpriteSheet(loader, "/AbilityEffects/AbilityEffects-16.png"));
        this.spriteSheets.put(SpriteSheetItem.ABILITIES_32, new SpriteSheet(loader, "/AbilityEffects/AbilityEffects-32.png"));
        this.spriteSheets.put(SpriteSheetItem.STATUS_AFFECTS_16, new SpriteSheet(loader, "/StatusEffects/StatusEffects-16.png"));
        this.spriteSheets.put(SpriteSheetItem.TEXT, new SpriteSheet(loader, "/Text/Text.png"));
    }

    public SpriteSheet get(SpriteSheetItem item) {
        return this.spriteSheets.get(item);
    }
}
