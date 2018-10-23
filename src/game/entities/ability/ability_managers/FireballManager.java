package game.entities.ability.ability_managers;

import game.InputHandler;
import game.animators.ability_animators.FireballAnimator;
import game.entities.Spawner;
import game.entities.ability.Ability;
import game.entities.ability.projectiles.FireballProjectile;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class FireballManager extends AbilityManager {
    private FireballAnimator fireballAnimator;
    private long lastAbilityCreated;
    private Sprite fireballSprite[];
    private Spawner spawner;

    private boolean readyToShoot;

    public static int MANA_COST = 40;


    public FireballManager(Screen screen, InputHandler input, Level level, Sprite fireballSprite[], Sprite attackSprites[]){
        super(screen, input, level, 5);
        this.timeBetweenAnim = Player.FIREBALL_CAST_SPEED/numOfAnim;
        this.input = input;
        this.inAnimation = false;
        this.readyToShoot = true;
        this.fireballSprite = fireballSprite;
        this.fireballAnimator = new FireballAnimator(screen, 4, attackSprites, this);
        this.spawner = new Spawner(level, screen);
    }

    public void tick(){
        for (Ability a: abilityList) {
            a.tick();
        }
        checkReadyToShoot();
    }

    public void checkReadyToShoot(){
        long currentTime = System.currentTimeMillis();

        if (!readyToShoot && (currentTime - lastAbilityCreated) > Player.FIREBALL_CAST_SPEED){
            readyToShoot = true;
        }else {
            readyToShoot = false;
        }
    }

    public void renderSprite(double x, double y){
        fireballAnimator.renderSprite((int) x, (int) y);
    }

    @Override
    public void castAbility(int x, int y) {
        addAbilityInstance(new FireballProjectile(level, screen, x, y, getDir(), fireballSprite));
    }

    public double getDir(){
        int mouseX, mouseY;

        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

        double dx = mouseX - (screen.getWidth()*screen.getScale())/2;
        double dy = mouseY - (screen.getHeight()*screen.getScale())/2;

        return Math.atan2(dy, dx);
    }

    public void renderFireballs(){
        for (int i = 0; i < abilityList.size(); i++) {
            if (abilityList.get(i).isExploding()) {
                abilityList.get(i).explode();
                spawner.spawnEntities((int)abilityList.get(i).getX()+4, (int)abilityList.get(i).getY()+4, Spawner.Type.PARTICAL, 100, Sprite.particle_red );
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
            readyToShoot = false;
        }
    }
}
