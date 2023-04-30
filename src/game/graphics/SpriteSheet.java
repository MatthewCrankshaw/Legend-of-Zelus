package game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteSheet {
    public String path;
    public final int SIZE;
    public int[] pixels;

    //Characters *****************************************************************************************
    public static SpriteSheet characterAnimations16 = new SpriteSheet("/Character/CharacterAnimations-16.png", 256);
    public static SpriteSheet characterMovement_16 = new SpriteSheet("/Character/CharacterMovement-16.png", 256);

    //Terrain ********************************************************************************
    public static SpriteSheet grassToSand = new SpriteSheet("/Terrain/grass_to_sand.png", 48);
    public static SpriteSheet dirtToGrass = new SpriteSheet("/Terrain/dirt_to_grass.png", 48);
    public static SpriteSheet grassToDirt = new SpriteSheet("/Terrain/grass_to_dirt.png", 48);

    public static SpriteSheet sandToWater1 = new SpriteSheet("/Terrain/water_sprites/sand_to_water1.png", 48);
    public static SpriteSheet sandToWater2 = new SpriteSheet("/Terrain/water_sprites/sand_to_water2.png", 48);
    public static SpriteSheet sandToWater3 = new SpriteSheet("/Terrain/water_sprites/sand_to_water3.png", 48);

    public static SpriteSheet environment_8 = new SpriteSheet("/Environment/Environment-8.png", 256);
    public static SpriteSheet environment_16 = new SpriteSheet("/Environment/Environment-16.png", 256);
    public static SpriteSheet environment_32 = new SpriteSheet("/Environment/Environment-32.png", 256);

    //Abilities ******************************************************************************
    public static SpriteSheet abilityEffects8 = new SpriteSheet("/AbilityEffects/AbilityEffects-8.png", 256);
    public static SpriteSheet abilityEffects16 = new SpriteSheet("/AbilityEffects/AbilityEffects-16.png", 256);
    public static SpriteSheet abilityEffects32 = new SpriteSheet("/AbilityEffects/AbilityEffects-32.png", 288);

    //Status Effects ***************************************************************************
    public static SpriteSheet statusEffects_16 = new SpriteSheet("/StatusEffects/StatusEffects-16.png", 256);

    //Text Fonts *******************************************************************************
    public static SpriteSheet text = new SpriteSheet("/Text/Text.png", 256);

    public SpriteSheet(String path, int size){
        System.out.println(path);
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    public void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
