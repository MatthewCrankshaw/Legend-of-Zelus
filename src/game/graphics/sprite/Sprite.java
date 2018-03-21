package game.graphics.sprite;


import game.graphics.SpriteSheet;
import game.graphics.sprite.environment_sprites.SandToWaterSprites;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Sprite {
    public final int SIZE;
    protected int x,y;
    public int [] pixels;
    private SpriteSheet sheet;

    public static Sprite particle_simple = new Sprite(1, 0x9C2A00);
    public static Sprite particle_simple2 = new Sprite(2, 0xff0000);


    //Projectile Sprites
    public static Sprite projectileFireball = new Sprite(8,0, 0, SpriteSheet.abilityEffects8);
    public static Sprite fireBallFizzleOut = new Sprite(8,1, 0, SpriteSheet.abilityEffects8);
    public static Sprite fireBallExpload = new Sprite(8,2, 0, SpriteSheet.abilityEffects8);

    public static Sprite greenBall= new Sprite(8,3, 0, SpriteSheet.abilityEffects8);
    public static Sprite greenBallFizzleOut= new Sprite(8,4, 0, SpriteSheet.abilityEffects8);
    public static Sprite greenBallExpload = new Sprite(8,5, 0, SpriteSheet.abilityEffects8);

    public static Sprite teleportFloorSign = new Sprite(32, 0, 0, SpriteSheet.abilityEffects32);

    public static Sprite[] teleportSprite = AnimatedSprite.loadAnimatedSprite(16, 3, 0, 4, SpriteSheet.characterAnimations16);

    public static Sprite[] fireballFloorSign = AnimatedSprite.loadAnimatedSprite(32, 4, 1, 5, SpriteSheet.abilityEffects32);

    public Sprite(int size, int x, int y, SpriteSheet sheet){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        load();
    }


    public Sprite(int size, int colour){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    public void setColour(int colour){
        for(int i = 0; i < SIZE  * SIZE; i ++) {
            pixels[i] = colour;
        }
    }

    public void load(){
        for (int y =0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];
            }
        }
    }
}
