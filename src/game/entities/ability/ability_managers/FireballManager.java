package game.entities.ability.ability_managers;

import game.InputHandler;
import game.animators.ability_animators.FireballAnimator;
import game.entities.Spawner;
import game.entities.ability.Ability;
import game.entities.ability.projectiles.FireballProjectile;
import game.entities.mob.Player;
import game.graphics.FrameState;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.levels.Level;
import game.levels.tile.TileManager;

/**
 * Created by Matthew.c on 02/02/2017.
 */
public class FireballManager extends AbilityManager {
    public static int MANA_COST = 40;
    private FireballAnimator animator;
    private long lastAbilityCreated;
    private Sprite fireballSprite[];
    private Sprite particleSprite;
    private Spawner spawner;
    private boolean readyToShoot;

    public FireballManager(Screen screen, InputHandler input, Level level, Sprite[] fireballSprite, FireballAnimator animator, Spawner spawner, SpriteRegistry spriteRegistry) {
        super(screen, input, level, 5);
        this.timeBetweenAnim = Player.FIREBALL_CAST_SPEED / numOfAnim;
        this.input = input;
        this.inAnimation = false;
        this.readyToShoot = true;
        this.fireballSprite = fireballSprite;
        this.particleSprite = spriteRegistry.get(SpriteRegistry.SpriteItem.RED_PARTICLE);
        this.animator = animator;
        this.spawner = spawner;
    }

    public void tick() {
        for (Ability a : abilityList) {
            a.tick();
        }
        checkReadyToShoot();
    }

    public void checkReadyToShoot() {
        long currentTime = System.currentTimeMillis();

        if (!readyToShoot && (currentTime - lastAbilityCreated) > Player.FIREBALL_CAST_SPEED) {
            readyToShoot = true;
        } else {
            readyToShoot = false;
        }
    }

    public void renderSprite(double x, double y) {
        animator.renderSprite((int) x, (int) y);
        setInAnimation(animator.isInAnimation());
        if (animator.castAbility()) {
            castAbility((int) x, (int) y);
        }
    }

    @Override
    protected void castAbility(int x, int y) {
        addAbilityInstance(new FireballProjectile(level, screen, x, y, getDir(), fireballSprite));
    }

    public double getDir() {
        int mouseX, mouseY;

        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

        double dx = mouseX - (double) (screen.getWidth() * FrameState.getScale()) / 2;
        double dy = mouseY - (double) (screen.getHeight() * FrameState.getScale()) / 2;

        return Math.atan2(dy, dx);
    }

    public void renderFireballs(TileManager tileManager) {
        for (int i = 0; i < abilityList.size(); i++) {
            if (abilityList.get(i).isExploding()) {
                abilityList.get(i).explode();
                spawner.spawnEntities((int) abilityList.get(i).getX() + 4, (int) abilityList.get(i).getY() + 4, Spawner.Type.PARTICAL, 100, particleSprite);
                abilityList.remove(abilityList.get(i));
            } else if (!abilityList.get(i).isAlive()) {
                abilityList.get(i).fizzleOut();
                abilityList.remove(i);
            } else {
                abilityList.get(i).render(screen);
            }
        }
    }

    public void setParticleSprite(Sprite sprite) {
        this.particleSprite = sprite;
    }

    private void addAbilityInstance(Ability ability) {
        if (readyToShoot) {
            abilityList.add(ability);
            lastAbilityCreated = System.currentTimeMillis();
            readyToShoot = false;
        }
    }
}
