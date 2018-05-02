package game.entities.ability.ability_managers;

import game.animators.ability_animators.TeleportAnimator;
import game.entities.mob.Enemy;
import game.entities.mob.Mob;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;

import java.util.Random;

public class AITeleportManager extends AbilityManager{
    private Mob mob;
    private TeleportAnimator teleportAnimator;

    private boolean alreadyTP;

    public AITeleportManager(Screen screen, Mob mob){
        super(screen, 6);
        timeBetweenAnim = (Enemy.ENEMY_TELEPORT_SPEED)/numOfAnim;
        this.mob = mob;
        teleportAnimator = new TeleportAnimator(screen, 6, Sprite.teleportSprite, this, timeBetweenAnim);
        inAnimation = false;
    }

    @Override
    public void castAbility(int x, int y) {

        //TODO fix bugs and teleport to meaningful location
        if (!alreadyTP){
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());
            int xa = (random.nextInt() % 200)+ 200;
            int ya = (random.nextInt() % 200)+ 200;

            System.out.println("tele " + xa + " " + ya);

            mob.changeLocation(xa, ya);
            alreadyTP = true;
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void renderSprite(double x, double y) {
        teleportAnimator.renderSprite((int)x, (int)y);
    }

    public void reset(){
        teleportAnimator.resetAnimation();
        alreadyTP = false;
    }
}
