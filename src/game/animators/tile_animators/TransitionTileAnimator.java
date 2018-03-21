package game.animators.tile_animators;

import game.animators.Animator;
import game.graphics.Screen;

/**
 * Created by Matthew.c on 27/02/2017.
 */
public class TransitionTileAnimator extends Animator {

    public static int WATER_TILE_ANIM_SPEED = 1800;


    public TransitionTileAnimator() {
        super(4); //send num of frames to the Animator
        timeBetweenAnim = WATER_TILE_ANIM_SPEED/numOfAnims;
    }


    public int getCurrentAnimationIndex(){
        currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= timeBetweenAnim * 4) {
            lastTime = currentTime;
            return 0;
        }else if(currentTime - lastTime >= timeBetweenAnim * 3) {
            return 1;
        }else if(currentTime - lastTime >= timeBetweenAnim * 2) {
            return 2;
        }
        else if(currentTime - lastTime >= timeBetweenAnim) {
            return 1;
        }
        return 0;
    }
}
