package game.animators.ability_animators;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.AnimatedSprite;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class FireballAnimator extends AbilityAnimator {

    public FireballAnimator(Screen screen, int numOfAnims, Sprite[] basicSprite) {
        super(screen, numOfAnims, basicSprite);
        timeBetweenAnim = Player.FIREBALL_CAST_SPEED/numOfAnims;
    }

    @Override
    public void renderSprite(int x, int y) {
        inAnimation = true;
        currentTime = System.currentTimeMillis();

        if (currentTime - lastTime <= timeBetweenAnim) {
            currentSprite = basicSprite[0];
            screen.renderSprite(x - 8, y, AnimatedSprite.fireballFloorSign[0], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 2) {
            currentSprite = basicSprite[1];
            screen.renderSprite(x - 8, y, AnimatedSprite.fireballFloorSign[1], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 3) {
            castAbility = true;
            currentSprite = basicSprite[2];
            screen.renderSprite(x - 8, y, AnimatedSprite.fireballFloorSign[2], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 4 && inAnimation) {
            currentSprite = basicSprite[1];
            screen.renderSprite(x - 8, y, AnimatedSprite.fireballFloorSign[3], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 5 && inAnimation) {
            currentSprite = basicSprite[0];
            screen.renderSprite(x - 8, y, AnimatedSprite.fireballFloorSign[4], true, -1, 1);
        }else{
            currentSprite = basicSprite[0];
            inAnimation = false;
            lastTime = currentTime;
        }
        screen.renderSprite(x, y, currentSprite, true, -1, 1);
    }
}
