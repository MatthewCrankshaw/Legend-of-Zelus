package game.entities.ability.ability_managers;

import game.InputHandler;
import game.animators.ability_animators.TeleportAnimator;
import game.entities.mob.Mob;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class TeleportManager extends AbilityManager{

    private Mob mob;
    private TeleportAnimator animator;

    private boolean alreadyTP;

    public static int MANA_COST = 100;

    public TeleportManager(Screen screen, InputHandler input, Level level, Mob mob, Sprite[] characterSprites, Sprite teleportSprite, TeleportAnimator animator){
        super(screen, input, level, 6);
        this.timeBetweenAnim = Player.TELEPORT_CAST_SPEED/numOfAnim;
        this.input = input;
        this.mob = mob;
        this.animator = animator;
        this.inAnimation = false;
    }

    @Override
    public void tick() {
    }

    @Override
    public void renderSprite(double x, double y) {
        animator.renderSprite((int)x, (int)y);
        this.setInAnimation(animator.isInAnimation());
        if (animator.castAbility()) {
            castAbility((int)x, (int)y);
        }
    }

    @Override
    protected void castAbility(int x, int y) {
        if (!alreadyTP){
            mob.changeLocation(((input.getMouseX() - (screen.getWidth()*screen.getScale())/2)/3) -8 , ((input.getMouseY() - (screen.getHeight()*screen.getScale())/2)/3) - 8);
            alreadyTP = true;
        }
    }

    public void reset(){
        animator.resetAnimation();
        alreadyTP = false;
    }
}
