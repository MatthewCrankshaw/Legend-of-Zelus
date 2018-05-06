package game.entities.mob;

import game.animators.Animator;
import game.animators.mob_animators.PlayerAnimator;
import game.graphics.Screen;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;

public class Enemy extends Mob{

    private PlayerAnimator playerAnimator;

    public Enemy(int x, int y, Level level, Screen screen, String name, int speed, Animator animator){
        super(level, screen, name, speed);
        this.x  = x;
        this.y = y;
        playerAnimator = new PlayerAnimator(screen, 4, PlayerSprite.zombieSprites, this);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Screen screen) {
        playerAnimator.renderSprite((int)x,(int)y);
    }
}
