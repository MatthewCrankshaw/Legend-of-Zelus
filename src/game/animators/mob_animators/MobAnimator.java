package game.animators.mob_animators;

import game.animators.Animator;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 06/02/2017.
 */
public abstract class MobAnimator extends Animator {


    protected  Screen screen;
    protected Sprite[][] mobSprite;
    protected Sprite currentSprite;

    protected int scale;

    public MobAnimator(Screen screen, int numOfAnims, Sprite[][] mobSprite, int scale){
        super(numOfAnims);
        this.screen = screen;
        this.mobSprite = mobSprite;
        this.scale = scale;
    }


    public abstract void renderSprite(int x, int y, boolean isMoving, int movingDir, boolean isSwimming);
}
