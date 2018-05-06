package game.graphics.sprite.mob_sprites;

import game.graphics.SpriteSheet;
import game.graphics.sprite.AnimatedSprite;
import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class PlayerSprite extends AnimatedSprite {

    public static Sprite[][] playerSprites = loadPlayer(0);
    public static Sprite[][] zombieSprites = loadPlayer(1);
    public static Sprite[][] enemySprites = loadPlayer(2);
    public static Sprite[] playerAttackSprites = loadAnimatedSprite(Tile.TILE_SIZE*2, new int[]{0,1,2}, new int[]{0,0,0}, SpriteSheet.characterAnimations16);

    public static Sprite[] swimming = loadAnimatedSprite(16, 0, 0, 3, SpriteSheet.statusEffects_16);

    public PlayerSprite(int x, int y){
        super(16, x, y, SpriteSheet.characterMovement_16);
    }

    public static Sprite[][] loadPlayer(int spriteNum){
        //spriteNum is the number that the sprite is on the y axis of the sprite page

        Sprite[][] sprites = new Sprite[4][4];
        for (int anim = 0; anim < 4; anim++) {
            sprites[anim] = loadAnimatedSprite(Tile.TILE_SIZE*2, anim * 4, spriteNum, 4, SpriteSheet.characterMovement_16);
        }
        return sprites;
    }
}
