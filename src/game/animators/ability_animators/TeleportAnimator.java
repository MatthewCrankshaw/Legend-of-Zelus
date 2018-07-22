package game.animators.ability_animators;

import game.entities.ability.ability_managers.AbilityManager;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class TeleportAnimator extends AbilityAnimator {
    public TeleportAnimator(Screen screen, int numOfAnims, Sprite[] basicSprite, AbilityManager abilityManager, int timeForAnim) {
        super(screen, numOfAnims, basicSprite, abilityManager);
        timeBetweenAnim = timeForAnim;
    }

    @Override
    public void renderSprite(int x, int y) {
        long currentTime = System.currentTimeMillis();

        screen.renderSprite(x -8, y-4, Sprite.teleportFloorSign, true, -1, 1);
        if (currentTime - lastTime >= timeBetweenAnim * 6) {
            currentSprite = basicSprite[0];
            lastTime = currentTime;
            abilityManager.setInAnimation(false);
        }else if (currentTime - lastTime >= timeBetweenAnim * 5) {
            currentSprite = basicSprite[1];
        }else if (currentTime - lastTime >= timeBetweenAnim * 4) {
            currentSprite = basicSprite[2];
        }else if (currentTime - lastTime >= timeBetweenAnim * 3) {
            currentSprite = basicSprite[3];
            abilityManager.castAbility(x, y);
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
