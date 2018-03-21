package game.graphics.sprite.environment_sprites;

import game.graphics.SpriteSheet;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class GrassToDirtSprites extends Sprite {

    public static GrassToDirtSprites NW_Diagonal = new GrassToDirtSprites(8, 0, 0, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites NE_Diagonal = new GrassToDirtSprites(8, 1, 0, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites SW_Diagonal = new GrassToDirtSprites(8, 0, 1, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites SE_Diagonal = new GrassToDirtSprites(8, 1, 1, SpriteSheet.grassToDirt);

    public static GrassToDirtSprites NW_Corner = new GrassToDirtSprites(8, 2, 0, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites NE_Corner = new GrassToDirtSprites(8, 3, 0, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites SW_Corner = new GrassToDirtSprites(8, 2, 1, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites SE_Corner = new GrassToDirtSprites(8, 3, 1, SpriteSheet.grassToDirt);

    public static GrassToDirtSprites northEdge = new GrassToDirtSprites(8, 1, 2, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites westEdge = new GrassToDirtSprites(8, 0, 3, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites eastEdge = new GrassToDirtSprites(8, 3, 3, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites southEdge = new GrassToDirtSprites(8, 1, 5, SpriteSheet.grassToDirt);


    public static GrassToDirtSprites solidDirt1 = new GrassToDirtSprites(8, 1, 3, SpriteSheet.grassToDirt);
    public static GrassToDirtSprites solidDirt2 = new GrassToDirtSprites(8, 2, 3, SpriteSheet.grassToDirt);


    public GrassToDirtSprites(int size, int x, int y, SpriteSheet sheet) {
        super(size, x, y, sheet);
    }
}
