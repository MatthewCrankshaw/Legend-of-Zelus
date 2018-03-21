package game.animators.ability_animators;

import game.animators.Animator;
import game.entities.ability.ability_managers.AbilityManager;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;


/**
 * Created by Matthew.c on 06/02/2017.
 */
public abstract class AbilityAnimator extends Animator {

    protected Sprite currentSprite;
    protected Sprite[] basicSprite;
    protected AbilityManager abilityManager;
    protected Screen screen;

    public AbilityAnimator(Screen screen, int numOfAnims, Sprite[] basicSprite, AbilityManager abilityManager) {
        super(numOfAnims);
        this.screen = screen;
        this.basicSprite = basicSprite;
        this.abilityManager = abilityManager;
    }

    public abstract void renderSprite(int x, int y);

}
