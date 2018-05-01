package game.entities.mob;

import game.animators.Animator;
import game.animators.mob_animators.PlayerAnimator;
import game.entities.ability.ability_managers.TeleportManager;
import game.graphics.Screen;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;
import game.levels.tile.Tile;

import java.util.Random;

public class Enemy extends Mob{

    private PlayerAnimator playerAnimator;
    private int movX, movY = 0;

    public Enemy(int x, int y, Level level, Screen screen, String name, int speed, Animator animator){
        super(level, screen, name, speed);
        this.x  = x;
        this.y = y;
        playerAnimator = new PlayerAnimator(screen, 4, PlayerSprite.playerSprites, this);
    }

    @Override
    public void tick() {

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


        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }

        movX = 0;
        movY = 0;

        }else {
            Random random = new Random();

            random.setSeed(System.currentTimeMillis());

            int xa = random.nextInt() % 200 + 100;
            int ya = random.nextInt() % 200 + 100;

            x = xa;
            y = ya;

            isStuck();
        }

    }

    @Override
    public void render(Screen screen) {
        playerAnimator.renderSprite((int)x,(int)y);
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
