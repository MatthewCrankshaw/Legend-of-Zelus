package game.graphics.sprite.mob_sprites;

import game.graphics.SpriteSheet;
import game.graphics.sprite.AnimatedSprite;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class PlayerSprite extends AnimatedSprite {

    public static Sprite[][] playerSprites = loadPlayer();
    public static Sprite[] playerAttackSprites = loadAnimatedSprite(Tile.TILE_SIZE*2, new int[]{0,1,2}, new int[]{0,0,0}, SpriteSheet.characterAnimations16);

    public static Sprite[] swimming = loadAnimatedSprite(16, 0, 0, 3, SpriteSheet.statusEffects_16);

    public PlayerSprite(int x, int y){
        super(16, x, y, SpriteSheet.characterMovement_16);
    }

    public static Sprite[][] loadPlayer(){
        //player forward    = currentSprite[3][0..3]
        //player right      = currentSprite[2][0..3]
        //player left       = currentSprite[1][0..3]
        //player backward   = currentSprite[0][0..3]

        Sprite[][] sprites = new Sprite[4][4];
        for (int anim = 0; anim < 4; anim++) {
            sprites[anim] = loadAnimatedSprite(Tile.TILE_SIZE*2, anim * 4, 0, 4, SpriteSheet.characterMovement_16);
        }
        return sprites;
    }
}
