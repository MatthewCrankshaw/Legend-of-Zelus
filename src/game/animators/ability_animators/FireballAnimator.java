package game.animators.ability_animators;

import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;

import java.awt.geom.Point2D;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class FireballAnimator extends AbilityAnimator {
    protected SpriteRegistry spriteRegistry;

    public FireballAnimator(Screen screen, int numOfAnims, Sprite[] basicSprite, SpriteRegistry spriteRegistry) {
        super(screen, numOfAnims, basicSprite);
        this.spriteRegistry = spriteRegistry;
        timeBetweenAnim = Player.FIREBALL_CAST_SPEED/numOfAnims;
    }

    @Override
    public void renderSprite(int x, int y) {
        inAnimation = true;
        currentTime = System.currentTimeMillis();

        Point2D position = new Point2D.Float(x-8, y);
        if (currentTime - lastTime <= timeBetweenAnim) {
            currentSprite = basicSprite[0];
            screen.renderSprite(position, spriteRegistry.getCollection(SpriteRegistry.AnimatedEnvSprite.FIREBALL_FLOOR_SIGN)[0], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 2) {
            currentSprite = basicSprite[1];
            screen.renderSprite(position, spriteRegistry.getCollection(SpriteRegistry.AnimatedEnvSprite.FIREBALL_FLOOR_SIGN)[1], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 3) {
            castAbility = true;
            currentSprite = basicSprite[2];
            screen.renderSprite(position, spriteRegistry.getCollection(SpriteRegistry.AnimatedEnvSprite.FIREBALL_FLOOR_SIGN)[2], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 4 && inAnimation) {
            currentSprite = basicSprite[1];
            screen.renderSprite(position, spriteRegistry.getCollection(SpriteRegistry.AnimatedEnvSprite.FIREBALL_FLOOR_SIGN)[3], true, -1, 1);
        }else if (currentTime - lastTime <= timeBetweenAnim * 5 && inAnimation) {
            currentSprite = basicSprite[0];
            screen.renderSprite(position, spriteRegistry.getCollection(SpriteRegistry.AnimatedEnvSprite.FIREBALL_FLOOR_SIGN)[4], true, -1, 1);
        }else{
            currentSprite = basicSprite[0];
            inAnimation = false;
            lastTime = currentTime;
        }
        position.setLocation(x, y);
        screen.renderSprite(position, currentSprite, true, -1, 1);
    }
}
