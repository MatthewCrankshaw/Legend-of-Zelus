package game.graphics.sprite.environment_sprites;

import game.graphics.SpriteSheet;
import game.graphics.sprite.AnimatedSprite;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class FloorTileSprite extends Sprite{

    public static final int SIZE = 8;

    public static FloorTileSprite grass = new FloorTileSprite(2, 0);
    public static FloorTileSprite stone = new FloorTileSprite(1, 0);
    public static FloorTileSprite woodFloor = new FloorTileSprite(5, 0);
    public static FloorTileSprite sand = new FloorTileSprite(6, 0);
    public static FloorTileSprite sandStone = new FloorTileSprite(7, 0);
    public static FloorTileSprite voidSprite = new FloorTileSprite(0x1B87E0);

    public static Sprite[] water = AnimatedSprite.loadAnimatedSprite(8, 0, 31, 3, SpriteSheet.environment_8);
    public static Sprite[] mud = AnimatedSprite.loadAnimatedSprite(8,3,31,3,SpriteSheet.environment_8);

    public FloorTileSprite(int x, int y){
        super(SIZE, x, y, SpriteSheet.environment_8);
    }

    public FloorTileSprite(int colour){
        super(SIZE, colour);
    }
}
