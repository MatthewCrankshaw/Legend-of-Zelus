package game.graphics.sprite;

import game.graphics.SpriteSheet;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class AnimatedSprite extends Sprite{

//    public static Sprite[] playerAttackSprites = loadAnimatedSprite(TileConstants.TILE_SIZE*2, new int[]{0,1,2}, new int[]{0,0,0}, SpriteSheet.characterAnimations16);
//    public static Sprite[] swimming = loadAnimatedSprite(16, 0, 0, 3, SpriteSheet.statusEffects_16);
//    public static Sprite[] teleportSprite = loadAnimatedSprite(16, 3, 0, 4, SpriteSheet.characterAnimations16);
//    public static Sprite[] fireballFloorSign = loadAnimatedSprite(32, 4, 1, 5, SpriteSheet.abilityEffects32);

    public AnimatedSprite(int size, int x, int y, SpriteSheet sheet){
        super(size, x, y , sheet);
    }

//    public static Sprite[] loadAnimatedSprite(int size, int[] xLocations, int [] yLocations, SpriteSheet sheet){
//        Sprite[] sprites = new Sprite[xLocations.length];
//        for (int i=0; i < xLocations.length; i++) {
//            sprites[i] = new Sprite(size, xLocations[i], yLocations[i], sheet);
//        }
//        return sprites;
//    }
//
//    //warning if the number of sprites is too high then it may go out of bounds
//    public static Sprite[] loadAnimatedSprite(int size, int startXPos, int startYPos, int numOfSprites, SpriteSheet sheet){
//        Sprite[] sprites = new Sprite[numOfSprites];
//        for(int i = 0; i < numOfSprites; i++) {
//            sprites[i] = new Sprite(size, startXPos + i, startYPos, sheet);
//        }
//        return sprites;
//    }
}
