package game.animators;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 06/02/2017.
 */
public abstract class Animator{

    protected int currentAnimationIndex;
    protected long currentTime;
    protected long lastTime;
    protected long timeBetweenAnim;
    protected int numOfAnims;

    public Animator(int numOfAnims){
        this.currentAnimationIndex = 0;
        this.numOfAnims = numOfAnims;
    }

}
