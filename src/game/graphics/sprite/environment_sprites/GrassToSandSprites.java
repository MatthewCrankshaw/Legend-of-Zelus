package game.graphics.sprite.environment_sprites;

import game.graphics.SpriteSheet;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class GrassToSandSprites extends Sprite {

    public static GrassToSandSprites NW_Diagonal = new GrassToSandSprites(8, 0, 0, SpriteSheet.grassToSand);
    public static GrassToSandSprites NE_Diagonal = new GrassToSandSprites(8, 1, 0, SpriteSheet.grassToSand);
    public static GrassToSandSprites SW_Diagonal = new GrassToSandSprites(8, 0, 1, SpriteSheet.grassToSand);
    public static GrassToSandSprites SE_Diagonal = new GrassToSandSprites(8, 1, 1, SpriteSheet.grassToSand);

    public static GrassToSandSprites NW_Corner = new GrassToSandSprites(8, 2, 0, SpriteSheet.grassToSand);
    public static GrassToSandSprites NE_Corner = new GrassToSandSprites(8, 3, 0, SpriteSheet.grassToSand);
    public static GrassToSandSprites SW_Corner = new GrassToSandSprites(8, 2, 1, SpriteSheet.grassToSand);
    public static GrassToSandSprites SE_Corner = new GrassToSandSprites(8, 3, 1, SpriteSheet.grassToSand);

    public static GrassToSandSprites northEdge = new GrassToSandSprites(8, 1, 2, SpriteSheet.grassToSand);
    public static GrassToSandSprites westEdge = new GrassToSandSprites(8, 0, 3, SpriteSheet.grassToSand);
    public static GrassToSandSprites eastEdge = new GrassToSandSprites(8, 3, 3, SpriteSheet.grassToSand);
    public static GrassToSandSprites southEdge = new GrassToSandSprites(8, 1, 5, SpriteSheet.grassToSand);


    public static GrassToSandSprites solidSand1 = new GrassToSandSprites(8, 1, 3, SpriteSheet.grassToSand);
    public static GrassToSandSprites solidSand2 = new GrassToSandSprites(8, 2, 3, SpriteSheet.grassToSand);

    public GrassToSandSprites(int size, int x, int y, SpriteSheet sheet) {
        super(size, x, y, sheet);
    }
}
