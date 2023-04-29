package game.animators.ability_animators;

import game.animators.Animator;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;


/**
 * Created by Matthew.c on 06/02/2017.
 */
public abstract class AbilityAnimator extends Animator {

    protected Sprite currentSprite;
    protected Sprite[] basicSprite;
    protected Screen screen;
    protected boolean inAnimation = true;

    protected boolean castAbility = false;

    public AbilityAnimator(Screen screen, int numOfAnims, Sprite[] basicSprite) {
        super(numOfAnims);
        this.screen = screen;
        this.basicSprite = basicSprite;
    }

    public abstract void renderSprite(int x, int y);


    public boolean isInAnimation() {
        return this.inAnimation;
    }

    public boolean castAbility() {
        return this.castAbility;
    }
}
