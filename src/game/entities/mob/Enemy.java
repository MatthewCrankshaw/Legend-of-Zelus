package game.entities.mob;

import game.animators.Animator;
import game.animators.mob_animators.CharacterAnimator;
import game.animators.mob_animators.MobAnimator;
import game.entities.ability.ability_managers.AITeleportManager;
import game.entities.ability.ability_managers.TeleportManager;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;
import game.levels.tile.Tile;

import java.util.Random;

public class Enemy extends Mob{

    public static int ENEMY_TELEPORT_SPEED = 1200;
    private CharacterAnimator characterAnimator;
    private AITeleportManager teleportManager;
    private int movX, movY = 0;

    public Enemy(int x, int y, Level level, Screen screen, String name, int speed, Sprite[][] spriteName){
        super(level, screen, name, speed);
        this.x  = x;
        this.y = y;
        characterAnimator = new CharacterAnimator(screen, 4, spriteName, this, 120);
        teleportManager = new AITeleportManager(screen, this);
    }

    @Override
    public void tick() {

        teleportManager.tick();

        Tile.swimming.tick();
        if (!stuck) {
        int xa = 0;
        int ya = 0;
        if (movY == 1) {
            ya--;
        }
        if (movY == -1) {
            ya++;
        }
        if (movX == -1) {
            xa--;
        }
        if (movX == 1) {
            xa++;
        }

        if (!teleportManager.isInAnimation()) {
            if (xa != 0 || ya != 0) {
                move(xa, ya);
                moving = true;
            } else {
                moving = false;
            }

            movX = 0;
            movY = 0;
        }

        }else {
            if (!teleportManager.isInAnimation()) {
                teleportManager.reset();
                teleportManager.setInAnimation(true);
            }
        }

        isStuck();
    }

    @Override
    public void render(Screen screen) {
        if (teleportManager.isInAnimation()){
            teleportManager.renderSprite(x, y);
        }else{
            characterAnimator.renderSprite((int)x,(int)y);
        }
    }


    public void moveUp(){
        movY = 1;
    }

    public void moveDown(){
        movY = -1;
    }

    public void moveLeft(){
        movX = -1;
    }

    public void moveRight(){
        movX = 1;
    }
}
