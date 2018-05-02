package game.entities.ability.ability_managers;

import game.Game;
import game.InputHandler;
import game.animators.ability_animators.TeleportAnimator;
import game.entities.mob.Mob;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class TeleportManager extends AbilityManager{

    private Mob mob;
    private TeleportAnimator teleportAnimator;

    private boolean alreadyTP;

    public TeleportManager(Screen screen, Mob mob, InputHandler input){
        super(screen, 6);
        timeBetweenAnim = Player.TELEPORT_CAST_SPEED/numOfAnim;
        this.input = input;
        this.mob = mob;
        teleportAnimator = new TeleportAnimator(screen, 6, Sprite.teleportSprite, this, timeBetweenAnim);
        inAnimation = false;
    }

    @Override
    public void renderSprite(double x, double y) {
        teleportAnimator.renderSprite((int)x, (int)y);
    }

    @Override
    public void castAbility(int x, int y) {
        if (!alreadyTP){
            mob.changeLocation(((input.getMouseX() - (Game.WIDTH*Game.SCALE)/2)/3) -8 , ((input.getMouseY() - (Game.HEIGHT*Game.SCALE)/2)/3) - 8);
            alreadyTP = true;
        }
    }

    public void reset(){
        teleportAnimator.resetAnimation();
        alreadyTP = false;
    }

    @Override
    public void tick() {
    }
}
