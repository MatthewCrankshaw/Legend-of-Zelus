package game.animators.ability_animators;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class TeleportAnimator extends AbilityAnimator {

    private Sprite teleportSprites;

    public TeleportAnimator(Screen screen, int numOfAnims, Sprite[] charactorSprites, Sprite teleportSprites, int timeForAnim) {
        super(screen, numOfAnims, charactorSprites);
        this.teleportSprites = teleportSprites;
        this.timeBetweenAnim = timeForAnim;
    }

    @Override
    public void renderSprite(int x, int y) {
        this.inAnimation = true;
        this.castAbility = false;
        long currentTime = System.currentTimeMillis();

        screen.renderSprite(x -8, y-4, teleportSprites, true, -1, 1);
        if (currentTime - lastTime >= timeBetweenAnim * 6) {
            currentSprite = basicSprite[0];
            lastTime = currentTime;
            this.inAnimation = false;
        }else if (currentTime - lastTime >= timeBetweenAnim * 5) {
            currentSprite = basicSprite[1];
        }else if (currentTime - lastTime >= timeBetweenAnim * 4) {
            currentSprite = basicSprite[2];
        }else if (currentTime - lastTime >= timeBetweenAnim * 3) {
            currentSprite = basicSprite[3];
            this.castAbility = true;
        }else if (currentTime - lastTime >= timeBetweenAnim * 2){
            currentSprite = basicSprite[2];
        }else if (currentTime - lastTime >= timeBetweenAnim) {
            currentSprite = basicSprite[1];
        }else {
            currentSprite = basicSprite[0];
        }
        screen.renderSprite(x, y, currentSprite, true, -1, 1);
    }

    public void resetAnimation(){
        lastTime = System.currentTimeMillis();
        currentTime = lastTime;
    }
}
