package game.entities.mob;

import game.InputHandler;

import game.animators.mob_animators.PlayerAnimator;
import game.entities.ability.ability_managers.TeleportManager;
import game.entities.ability.ability_managers.projectile_managers.FireballManager;
import game.graphics.Screen;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 21/01/2017.
 */
public class Player extends Mob {

    private InputHandler input;

    private int life;
    private int damage;

    public static int TELEPORT_CAST_SPEED = 500;
    public static int FIREBALL_CAST_SPEED = 200;
    public static int PLAYER_ANIMATION_SPEED = 100;

    private FireballManager fireballManager;
    private TeleportManager teleportManager;
    private PlayerAnimator playerAnimator;


    public Player(int x, int y, Level level, Screen screen, InputHandler input){
        super(level, screen, "Player", 2);
        this.x = x;
        this.y = y;
        this.input = input;

        fireballManager = new FireballManager(screen, input, level);
        teleportManager = new TeleportManager(screen, this, input);
        playerAnimator = new PlayerAnimator(screen, 4, PlayerSprite.playerSprites, this);
    }

    @Override
    public void tick() {
        fireballManager.tick();
        teleportManager.tick();

        Tile.swimming.tick();

        int xa = 0;
        int ya = 0;
        if (input.up.isPressed()) {
            ya--;
        }
        if (input.down.isPressed()) {
            ya++;
        }
        if (input.left.isPressed()) {
            xa--;
        }
        if (input.right.isPressed()) {
            xa++;
        }
        if (input.space.isPressed()) {
            fireballManager.setInAnimation(true);
        }
        if (input.e_teleport.isPressed()) {
            if (!teleportManager.isInAnimation()) {
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
            playerAnimator.renderSprite((int)x,(int)y);
        }
        //render Pre-existing fireballs
        fireballManager.renderFireballs();
    }

}
