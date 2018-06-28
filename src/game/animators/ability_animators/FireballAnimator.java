package game.animators.ability_animators;

import game.Game;
import game.entities.ability.ability_managers.AbilityManager;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class FireballAnimator extends AbilityAnimator {

    private boolean animating;

    public FireballAnimator(Screen screen, int numOfAnims, Sprite[] basicSprite, AbilityManager abilityManager) {
        super(screen, numOfAnims, basicSprite, abilityManager);
        timeBetweenAnim = Player.FIREBALL_CAST_SPEED/numOfAnims;
        animating = false;
    }

    @Override
    public void renderSprite(int x, int y) {
        if (!animating) {
            lastTime = System.currentTimeMillis();
        }
        currentTime = System.currentTimeMillis();

        double progressPercent = 0;
        int sizeOfBar = 0;
        if (currentTime - lastTime <= timeBetweenAnim *4) {
            progressPercent = ((currentTime - lastTime) / (timeBetweenAnim * 4d));
            sizeOfBar = (int) (progressPercent * 100d);
        }

        screen.renderRectangle(1, Game.HEIGHT - 11, sizeOfBar, 10, 0xff00ff, false);
        screen.renderCircle(100, 100, 50, sizeOfBar, 0xff00ff, 0x000000, true, false);
        if (currentTime - lastTime <= timeBetweenAnim) {
            animating = true;
            currentSprite = basicSprite[0];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[0]);
        }else if (currentTime - lastTime <= timeBetweenAnim && animating) {
            currentSprite = basicSprite[1];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[1]);
            screen.renderRectangle(100, 100, 100/2, 20, 0xff00ff, true);
        }else if (currentTime - lastTime <= timeBetweenAnim * 2 && animating) {
            abilityManager.castAbility(x,y);
            currentSprite = basicSprite[2];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[2]);
        }else if (currentTime - lastTime <= timeBetweenAnim * 3 && animating) {
            currentSprite = basicSprite[1];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[3]);
        }else if (currentTime - lastTime <= timeBetweenAnim * 4 && animating) {
            currentSprite = basicSprite[0];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[4]);
        }else{
            animating = false;
            abilityManager.setInAnimation(false);
            lastTime = currentTime;
        }
        screen.renderSprite(x, y, currentSprite);
    }
}
