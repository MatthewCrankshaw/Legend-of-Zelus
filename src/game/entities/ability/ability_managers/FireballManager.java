package game.entities.ability.ability_managers;

import game.InputHandler;
import game.animators.ability_animators.FireballAnimator;
import game.entities.Spawner;
import game.entities.ability.Ability;
import game.entities.ability.ability_managers.AbilityManager;
import game.entities.ability.projectiles.FireballProjectile;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class FireballManager extends AbilityManager {

    private Level level;
    private InputHandler input;
    private FireballAnimator fireballAnimator;
    private long lastAbilityCreated;

    private boolean readyToShoot;

    public static int MANA_COST = 40;


    public FireballManager(Screen screen, InputHandler input, Level level){
        super(screen, 5);
        timeBetweenAnim = Player.FIREBALL_CAST_SPEED/numOfAnim;
        this.input = input;
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
        addAbilityInstance(new FireballProjectile(level, screen, x, y, getDir(), Sprite.greenballSprites));
    }

    public double getDir(){
        int mouseX, mouseY;

        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

        //fix this
        double dx = mouseX - (screen.getWidth()*screen.getScale())/2;
        double dy = mouseY - (screen.getHeight()*screen.getScale())/2;

        return Math.atan2(dy, dx);
    }

    public void renderFireballs(){
        for (int i = 0; i < abilityList.size(); i++) {
            if (abilityList.get(i).isExploding()) {
                abilityList.get(i).explode();
                level.spawnEntitiesInLevel((int)abilityList.get(i).getX()+4, (int)abilityList.get(i).getY()+4, Spawner.Type.PARTICAL, 100, Sprite.particle_green);
                abilityList.remove(abilityList.get(i));
            }else if (!abilityList.get(i).isAlive()) {
                abilityList.get(i).fizzleOut();
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
