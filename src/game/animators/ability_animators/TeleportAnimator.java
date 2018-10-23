package game.animators.ability_animators;

import game.entities.ability.ability_managers.AbilityManager;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class TeleportAnimator extends AbilityAnimator {

    private Sprite teleportSprites;

    public TeleportAnimator(Screen screen, int numOfAnims, Sprite[] charactorSprites, Sprite teleportSprites, AbilityManager abilityManager, int timeForAnim) {
        super(screen, numOfAnims, charactorSprites, abilityManager);
        this.teleportSprites = teleportSprites;
        this.timeBetweenAnim = timeForAnim;
    }

    @Override
    public void renderSprite(int x, int y) {
        long currentTime = System.currentTimeMillis();

        screen.renderSprite(x -8, y-4, teleportSprites, true, -1, 1);
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
