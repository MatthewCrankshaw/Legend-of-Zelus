package game.graphics.sprite.environment_sprites;

import game.graphics.SpriteSheet;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 20/02/2017.
 */
public class SandToWaterSprites extends Sprite {

    //public static SandToWaterSprites NW_Diagonal = new SandToWaterSprites(0, 0, SpriteSheet.sandToWater);
    //public static SandToWaterSprites NE_Diagonal = new SandToWaterSprites(1, 0, SpriteSheet.sandToWater);
    //public static SandToWaterSprites SW_Diagonal = new SandToWaterSprites(0, 1, SpriteSheet.sandToWater);
    //public static SandToWaterSprites SE_Diagonal = new SandToWaterSprites(1, 1, SpriteSheet.sandToWater);

    //public static SandToWaterSprites NW_Corner = new SandToWaterSprites(2, 0, SpriteSheet.sandToWater);
    //public static SandToWaterSprites NE_Corner = new SandToWaterSprites(3, 0, SpriteSheet.sandToWater);
    //public static SandToWaterSprites SW_Corner = new SandToWaterSprites(2, 1, SpriteSheet.sandToWater);
    //public static SandToWaterSprites SE_Corner = new SandToWaterSprites(3, 1, SpriteSheet.sandToWater);

    //public static SandToWaterSprites northEdge = new SandToWaterSprites(1, 2, SpriteSheet.sandToWater);
    //public static SandToWaterSprites westEdge = new SandToWaterSprites(0, 3, SpriteSheet.sandToWater);
    //public static SandToWaterSprites eastEdge = new SandToWaterSprites(3, 3, SpriteSheet.sandToWater);
    //public static SandToWaterSprites southEdge = new SandToWaterSprites(1, 5, SpriteSheet.sandToWater);

    //public static SandToWaterSprites solidWater1 = new SandToWaterSprites(1, 3, SpriteSheet.sandToWater);
    //public static SandToWaterSprites solidWatet2 = new SandToWaterSprites(2, 3, SpriteSheet.sandToWater);

    /***********************************************************************************
     * There are 3 iterations/variations which it will tick through making it look animated
     * The Diagonal Sand to water sprite array
     ***********************************************************************************/
    public static SandToWaterSprites[] NW_Diagonal = new SandToWaterSprites[]{
            new SandToWaterSprites(0,0, SpriteSheet.sandToWater1),
            new SandToWaterSprites(0,0, SpriteSheet.sandToWater2),
            new SandToWaterSprites(0,0, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] NE_Diagonal = new SandToWaterSprites[]{
            new SandToWaterSprites(1,0, SpriteSheet.sandToWater1),
            new SandToWaterSprites(1,0, SpriteSheet.sandToWater2),
            new SandToWaterSprites(1,0, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] SW_Diagonal = new SandToWaterSprites[]{
            new SandToWaterSprites(0,1, SpriteSheet.sandToWater1),
            new SandToWaterSprites(0,1, SpriteSheet.sandToWater2),
            new SandToWaterSprites(0,1, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] SE_Diagonal = new SandToWaterSprites[]{
            new SandToWaterSprites(1,1, SpriteSheet.sandToWater1),
            new SandToWaterSprites(1,1, SpriteSheet.sandToWater2),
            new SandToWaterSprites(1,1, SpriteSheet.sandToWater3)
    };


    /***********************************************************************************
     * The Corner Sand to water sprite array
     ***********************************************************************************/

    public static SandToWaterSprites[] NW_Corner = new SandToWaterSprites[]{
            new SandToWaterSprites(2,0, SpriteSheet.sandToWater1),
            new SandToWaterSprites(2,0, SpriteSheet.sandToWater2),
            new SandToWaterSprites(2,0, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] NE_Corner = new SandToWaterSprites[]{
            new SandToWaterSprites(3,0, SpriteSheet.sandToWater1),
            new SandToWaterSprites(3,0, SpriteSheet.sandToWater2),
            new SandToWaterSprites(3,0, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] SW_Corner = new SandToWaterSprites[]{
            new SandToWaterSprites(2,1, SpriteSheet.sandToWater1),
            new SandToWaterSprites(2,1, SpriteSheet.sandToWater2),
            new SandToWaterSprites(2,1, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] SE_Corner = new SandToWaterSprites[]{
            new SandToWaterSprites(3,1, SpriteSheet.sandToWater1),
            new SandToWaterSprites(3,1, SpriteSheet.sandToWater2),
            new SandToWaterSprites(3,1, SpriteSheet.sandToWater3)
    };

    /***********************************************************************************
     * The Edge Sand to water sprite array
     ***********************************************************************************/

    public static SandToWaterSprites[] northEdge = new SandToWaterSprites[]{
            new SandToWaterSprites(1,2, SpriteSheet.sandToWater1),
            new SandToWaterSprites(1,2, SpriteSheet.sandToWater2),
            new SandToWaterSprites(1,2, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] westEdge = new SandToWaterSprites[]{
            new SandToWaterSprites(0,3, SpriteSheet.sandToWater1),
            new SandToWaterSprites(0,3, SpriteSheet.sandToWater2),
            new SandToWaterSprites(0,3, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] eastEdge = new SandToWaterSprites[]{
            new SandToWaterSprites(3,3, SpriteSheet.sandToWater1),
            new SandToWaterSprites(3,3, SpriteSheet.sandToWater2),
            new SandToWaterSprites(3,3, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] southEdge = new SandToWaterSprites[]{
            new SandToWaterSprites(1,5, SpriteSheet.sandToWater1),
            new SandToWaterSprites(1,5, SpriteSheet.sandToWater2),
            new SandToWaterSprites(1,5, SpriteSheet.sandToWater3)
    };

    /***********************************************************************************
     * The Edge Sand to water sprite array
     ***********************************************************************************/

    public static SandToWaterSprites[] solidWater1 = new SandToWaterSprites[]{
            new SandToWaterSprites(1,3, SpriteSheet.sandToWater1),
            new SandToWaterSprites(1,3, SpriteSheet.sandToWater2),
            new SandToWaterSprites(1,3, SpriteSheet.sandToWater3)
    };
    public static SandToWaterSprites[] solidWater2 = new SandToWaterSprites[]{
            new SandToWaterSprites(2,3, SpriteSheet.sandToWater1),
            new SandToWaterSprites(2,3, SpriteSheet.sandToWater2),
            new SandToWaterSprites(2,3, SpriteSheet.sandToWater3)
    };


    public SandToWaterSprites(int x, int y, SpriteSheet sheet) {
        super(8, x, y, sheet);
    }

}