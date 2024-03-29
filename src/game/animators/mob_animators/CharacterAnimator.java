package game.animators.mob_animators;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.levels.tile.TileManager;
import game.levels.tile.animated_tiles.AnimatedTile;

/**
 * Created by Matthew.c on 06/02/2017.
 */
public class CharacterAnimator extends MobAnimator {
    protected SpriteRegistry spriteRegistry;

    public CharacterAnimator(Screen screen, int numOfAnims, Sprite[][] sprite, int animSpeed, int scale, SpriteRegistry spriteRegistry){
        super(screen, numOfAnims, sprite, scale);
        this.spriteRegistry = spriteRegistry;
        timeBetweenAnim = animSpeed;
    }

    @Override
    public void renderSprite(int x, int y, boolean isMoving, int movingDir, boolean isSwimming, TileManager tileManager) {
        currentTime = System.currentTimeMillis();
        if (isMoving) {
            if (movingDir == 0) {                           //forward
                if (currentTime - lastTime >= timeBetweenAnim * 4) {
                    currentSprite = mobSprite[3][3];
                    lastTime = System.currentTimeMillis();
                }else if(currentTime - lastTime >= timeBetweenAnim * 3) {
                    currentSprite = mobSprite[3][2];
                }else if(currentTime - lastTime >= timeBetweenAnim * 2) {
                    currentSprite = mobSprite[3][1];
                }else if(currentTime - lastTime >= timeBetweenAnim) {
                    currentSprite = mobSprite[3][0];
                }
            }
            if (movingDir == 1) {                           //backward
                if (currentTime - lastTime >= timeBetweenAnim * 4) {
                    currentSprite = mobSprite[0][3];
                    lastTime = System.currentTimeMillis();
                }else if(currentTime - lastTime >= timeBetweenAnim * 3) {
                    currentSprite = mobSprite[0][2];
                }else if(currentTime - lastTime >= timeBetweenAnim * 2) {
                    currentSprite = mobSprite[0][1];
                }else if(currentTime - lastTime >= timeBetweenAnim) {
                    currentSprite = mobSprite[0][0];
                }
            }
            if (movingDir == 2) {                              //right
                if (currentTime - lastTime >= timeBetweenAnim * 4) {
                    currentSprite = mobSprite[2][3];
                    lastTime = System.currentTimeMillis();
                }else if(currentTime - lastTime >= timeBetweenAnim * 3) {
                    currentSprite = mobSprite[2][2];
                }else if(currentTime - lastTime >= timeBetweenAnim * 2) {
                    currentSprite = mobSprite[2][1];
                }else if(currentTime - lastTime >= timeBetweenAnim) {
                    currentSprite = mobSprite[2][0];
                }
            }
            if (movingDir == 3) {                               //left
                if (currentTime - lastTime >= timeBetweenAnim * 4) {
                    currentSprite = mobSprite[1][3];
                    lastTime = System.currentTimeMillis();
                }else if(currentTime - lastTime >= timeBetweenAnim * 3) {
                    currentSprite = mobSprite[1][2];
                }else if(currentTime - lastTime >= timeBetweenAnim * 2) {
                    currentSprite = mobSprite[1][1];
                }else if(currentTime - lastTime >= timeBetweenAnim) {
                    currentSprite = mobSprite[1][0];
                }
            }
        }else{
            //render Idle animation
            currentSprite = mobSprite[0][0];
        }

        if (isSwimming) {
            AnimatedTile tile = tileManager.getAnimatedTile(TileManager.AnimatedTileTypes.SWIMMING);
            screen.renderAnimatedTile(x, y, tile);
            screen.renderPlayer(x, y, 16, 10, scale, currentSprite);
        }else{
            screen.renderPlayer(x, y, 16, 16, scale, currentSprite);
        }
    }
}
