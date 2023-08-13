package game.entities.ability.ability_managers;

import game.InputHandler;
import game.animators.ability_animators.TeleportAnimator;
import game.entities.mob.Player;
import game.graphics.FrameState;
import game.graphics.Screen;
import game.levels.Level;

import java.awt.*;
import java.util.function.Function;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class TeleportManager extends AbilityManager {

    public static int MANA_COST = 100;
    protected Function<Point, Point> callback;
    private TeleportAnimator animator;
    private boolean alreadyTP;

    public TeleportManager(Screen screen, InputHandler input, Level level, TeleportAnimator animator) {
        super(screen, input, level, 6);
        this.timeBetweenAnim = Player.TELEPORT_CAST_SPEED / numOfAnim;
        this.input = input;
        this.animator = animator;
        this.inAnimation = false;
    }

    @Override
    public void tick() {
    }

    public void registerCallback(Function<Point, Point> callback) {
        this.callback = callback;
    }

    @Override
    public void renderSprite(double x, double y) {
        animator.renderSprite((int) x, (int) y);
        this.setInAnimation(animator.isInAnimation());
        if (animator.castAbility()) {
            castAbility((int) x, (int) y);
        }
    }

    @Override
    protected void castAbility(int x, int y) {
        if (!alreadyTP) {
            Point point = new Point(
                    ((input.getMouseX() - (screen.getWidth() * FrameState.getScale()) / 2) / 3) - 8,
                    ((input.getMouseY() - (screen.getHeight() * FrameState.getScale()) / 2) / 3) - 8
            );
            this.callback.apply(point);
            alreadyTP = true;
        }
    }

    public void reset() {
        animator.resetAnimation();
        alreadyTP = false;
    }
}
