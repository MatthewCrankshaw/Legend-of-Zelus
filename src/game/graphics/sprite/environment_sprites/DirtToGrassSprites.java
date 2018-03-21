package game.graphics.sprite.environment_sprites;

import game.graphics.SpriteSheet;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class DirtToGrassSprites extends Sprite{

    public static DirtToGrassSprites NW_Diagonal = new DirtToGrassSprites(8, 0, 0, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites NE_Diagonal = new DirtToGrassSprites(8, 1, 0, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites SW_Diagonal = new DirtToGrassSprites(8, 0, 1, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites SE_Diagonal = new DirtToGrassSprites(8, 1, 1, SpriteSheet.dirtToGrass);

    public static DirtToGrassSprites NW_Corner = new DirtToGrassSprites(8, 2, 0, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites NE_Corner = new DirtToGrassSprites(8, 3, 0, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites SW_Corner = new DirtToGrassSprites(8, 2, 1, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites SE_Corner = new DirtToGrassSprites(8, 3, 1, SpriteSheet.dirtToGrass);

    public static DirtToGrassSprites northEdge = new DirtToGrassSprites(8, 1, 2, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites westEdge = new DirtToGrassSprites(8, 0, 3, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites eastEdge = new DirtToGrassSprites(8, 3, 3, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites southEdge = new DirtToGrassSprites(8, 1, 5, SpriteSheet.dirtToGrass);


    public static DirtToGrassSprites solidGrass1 = new DirtToGrassSprites(8, 1, 3, SpriteSheet.dirtToGrass);
    public static DirtToGrassSprites solidGrass2 = new DirtToGrassSprites(8, 2, 3, SpriteSheet.dirtToGrass);



    public DirtToGrassSprites(int size, int x, int y, SpriteSheet sheet) {
        super(size, x, y, sheet);
    }
}
