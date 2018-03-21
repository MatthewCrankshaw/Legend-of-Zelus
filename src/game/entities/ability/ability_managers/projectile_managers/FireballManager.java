package game.entities.ability.ability_managers.projectile_managers;

import game.InputHandler;
import game.animators.ability_animators.FireballAnimator;
import game.entities.ability.Ability;
import game.entities.ability.ability_managers.AbilityManager;
import game.entities.ability.projectiles.FireballProjectile;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class FireballManager extends AbilityManager {

    private Level level;

    private FireballAnimator fireballAnimator;
    private long lastAbilityCreated;

    private boolean readyToShoot;


    public FireballManager(Screen screen, InputHandler input, Level level){
        super(screen, input, 5);
        timeBetweenAnim = Player.FIREBALL_CAST_SPEED/numOfAnim;
        this.level = level;
        inAnimation = false;
        readyToShoot = true;
        fireballAnimator = new FireballAnimator(screen, 4, PlayerSprite.playerAttackSprites, this);
    }

    public void tick(){
        for (Ability a: abilityList) {
            a.tick();
        }
        checkReadyToShoot();
    }

    public void checkReadyToShoot(){
        long currentTime = System.currentTimeMillis();
        if (currentTime- lastAbilityCreated > Player.FIREBALL_CAST_SPEED/numOfAnim + Player.FIREBALL_CAST_SPEED/2){
            readyToShoot = true;
            return;
        }
        readyToShoot = false;
    }

    public void renderSprite(double x, double y){
        fireballAnimator.renderSprite((int) x, (int) y);
    }

    @Override
    public void castAbility(int x, int y) {
        addAbilityInstance(new FireballProjectile(level, screen, x, y, getDir()));
    }

    public void renderFireballs(){
        for (int i = 0; i < abilityList.size(); i++) {
            if (abilityList.get(i).isExploding()) {
                abilityList.get(i).explode(screen);
                abilityList.remove(i);
            }else if (!abilityList.get(i).isAlive()) {
                abilityList.get(i).fizzleOut(screen);
                abilityList.remove(i);
            }else{
                abilityList.get(i).render(screen);
            }
        }
    }

    public void addAbilityInstance(Ability ability){
        if (readyToShoot){
            abilityList.add(ability);
            lastAbilityCreated = System.currentTimeMillis();
        }
    }
}
