package game.entities.mob;

import game.InputHandler;
import game.animators.mob_animators.CharacterAnimator;
import game.entities.ability.ability_managers.FireballManager;
import game.entities.ability.ability_managers.TeleportManager;
import game.graphics.Screen;
import game.levels.Level;
import game.levels.tile.TileManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Matthew.c on 21/01/2017.
 */
public class Player extends Mob {

    private InputHandler input;

    private int currentLife, maxLife, lifeRegeneration;
    private int currentMana, maxMana, manaRegeneration;
    private int currentExperience, maxExperience;

    public static int TELEPORT_CAST_SPEED = 700;
    public static int FIREBALL_CAST_SPEED = 200;

    private FireballManager fireballManager;
    private TeleportManager teleportManager;
    private CharacterAnimator characterAnimator;


    public Player(int x, int y, Level level, Screen screen, InputHandler input, FireballManager fireballManager, TeleportManager teleportManager, CharacterAnimator animator, TileManager tileManager){
        super(level, screen, "Player", 2, 1, tileManager);

        maxLife = 1000;
        currentLife = 500;
        lifeRegeneration = 1;

        maxMana = 1000;
        currentMana = 500;
        manaRegeneration = 1;

        maxExperience = 1000;
        currentExperience = 500;

        this.x = x;
        this.y = y;
        this.input = input;

        this.fireballManager = fireballManager;
        this.teleportManager = teleportManager;
        this.teleportManager.registerCallback((Point point) -> {
            this.x = point.getX();
            this.y = point.getY();
            return point;
        });

        this.characterAnimator = animator;
    }

    @Override
    public void tick() {

        //Mana Regeneration
        currentMana+=manaRegeneration;
        if(currentMana > maxMana){
            currentMana = maxMana;
        }
        //lifeRegeneration
        currentLife+=lifeRegeneration;
        if(currentLife > maxLife){
            currentLife = maxLife;
        }

        fireballManager.tick();
        teleportManager.tick();

        int xa = 0;
        int ya = 0;
        if (input.isKeyPressed(KeyEvent.VK_UP)) {
            ya--;
        }
        if (input.isKeyPressed(KeyEvent.VK_DOWN)) {
            ya++;
        }
        if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
            xa--;
        }
        if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            xa++;
        }
        if (input.isKeyPressed(KeyEvent.VK_SPACE) && currentMana > FireballManager.MANA_COST){
            if(!fireballManager.isInAnimation()) {
                currentMana -= FireballManager.MANA_COST;
            }
            fireballManager.setInAnimation(true);
        }
        if (input.isKeyPressed(KeyEvent.VK_E) && currentMana > TeleportManager.MANA_COST) {
            if (!teleportManager.isInAnimation()) {
                currentMana -= TeleportManager.MANA_COST;
                teleportManager.reset();
                teleportManager.setInAnimation(true);
            }

        }
        if(!fireballManager.isInAnimation() && !teleportManager.isInAnimation()) {
            if (xa != 0 || ya != 0) {
                move(xa, ya);
                moving = true;
            } else {
                moving = false;
            }
        }
    }

    @Override
    public void render(Screen screen) {
        //render the character animations according to state
        if (!teleportManager.isInAnimation() && fireballManager.isInAnimation()) {
            fireballManager.renderSprite(x,y);
        }else if (teleportManager.isInAnimation()){
            teleportManager.renderSprite(x,y);
        }else{
            characterAnimator.renderSprite((int)x,(int)y, this.isMoving(), this.getMovingDir(), this.isSwimming(), tileManager);
        }
        //render Pre-existing fireballs
        fireballManager.renderFireballs(tileManager);
    }

    public int getCurrentLife(){
        return currentLife;
    }

    public int getMaxLife(){
        return maxLife;
    }

    public int getCurrentMana(){
        return currentMana;
    }

    public int getMaxMana(){
        return maxMana;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getMaxExperience() {
        return maxExperience;
    }
}
