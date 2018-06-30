package game.animators.ability_animators;
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

        if (currentTime - lastTime <= timeBetweenAnim) {
            animating = true;
            currentSprite = basicSprite[0];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[0], true, -1);
        }else if (currentTime - lastTime <= timeBetweenAnim && animating) {
            currentSprite = basicSprite[1];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[1], true, -1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 2 && animating) {
            abilityManager.castAbility(x,y);
            currentSprite = basicSprite[2];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[2], true, -1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 3 && animating) {
            currentSprite = basicSprite[1];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[3], true, -1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 4 && animating) {
            currentSprite = basicSprite[0];
            screen.renderSprite(x - 8, y, Sprite.fireballFloorSign[4], true, -1);
        }else{
            animating = false;
            abilityManager.setInAnimation(false);
            lastTime = currentTime;
        }
        screen.renderSprite(x, y, currentSprite, true, -1);
    }
}
