package game.animators.mob_animators;

import game.animators.Animator;
import game.entities.mob.Mob;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.tile.Tile;

/**
 * Created by Matthew.c on 06/02/2017.
 */
public abstract class MobAnimator extends Animator {


    protected  Screen screen;
    protected Mob mob;
    protected Sprite[][] mobSprite;
    protected Sprite currentSprite;

    public MobAnimator(Screen screen, int numOfAnims, Sprite[][] mobSprite, Mob mob){
        super(numOfAnims);
        this.screen = screen;
        this.mobSprite = mobSprite;
        this.mob = mob;
    }


    public abstract void renderSprite(int x, int y);


}
