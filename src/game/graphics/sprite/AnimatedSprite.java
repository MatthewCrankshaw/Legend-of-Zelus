package game.graphics.sprite;

import game.graphics.SpriteSheet;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class AnimatedSprite extends Sprite {

    public AnimatedSprite(int size, int x, int y, SpriteSheet sheet){
        super(size, x, y , sheet);
    }


    public static Sprite[] loadAnimatedSprite(int size, int[] xLocations, int [] yLocations, SpriteSheet sheet){
        Sprite[] sprites = new Sprite[xLocations.length];
        for (int i=0; i < xLocations.length; i++) {
            sprites[i] = new Sprite(size, xLocations[i], yLocations[i], sheet);
        }
        return sprites;
    }

    //warning if the number of sprites is too high then it may go out of bounds
    public static Sprite[] loadAnimatedSprite(int size, int startXPos, int startYPos, int numOfSprites, SpriteSheet sheet){
        Sprite[] sprites = new Sprite[numOfSprites];
        for(int i = 0; i < numOfSprites; i++) {
            sprites[i] = new Sprite(size, startXPos + i, startYPos, sheet);
        }
        return sprites;
    }
}
