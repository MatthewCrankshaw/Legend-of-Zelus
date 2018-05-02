package game.animators.mob_animators;

import game.entities.mob.Mob;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 06/02/2017.
 */
public class CharacterAnimator extends MobAnimator {


    public CharacterAnimator(Screen screen, int numOfAnims, Sprite[][] sprite, Mob mob, int animSpeed){
        super(screen, numOfAnims, sprite, mob);
        timeBetweenAnim = animSpeed;
    }

    @Override
    public void renderSprite(int x, int y) {
        currentTime = System.currentTimeMillis();
        if (mob.isMoving()) {
            if (mob.getMovingDir() == 0) {                           //forward
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
            if (mob.getMovingDir() == 1) {                           //backward
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
            if (mob.getMovingDir() == 2) {                              //right
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
            if (mob.getMovingDir() == 3) {                               //left
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

        if (mob.isSwimming()) {
            screen.renderAnimatedTile((int)mob.getX(), (int)mob.getY(), Tile.swimming, PlayerSprite.swimming);
            screen.renderPlayer((int)mob.getX(), (int)mob.getY(), Tile.TILE_SIZE*2, 12, currentSprite);
        }else{
            screen.renderPlayer((int)mob.getX(), (int)mob.getY(), Tile.TILE_SIZE*2, Tile.TILE_SIZE*2, currentSprite);
        }
    }
}
