package game.entities.ability.ability_managers;

import game.Game;
import game.InputHandler;
import game.entities.ability.Ability;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.levels.Level;

import java.util.ArrayList;

/**
 * Created by Matthew.c on 01/02/2017.
 */
public abstract class AbilityManager {
    protected Screen screen;
    protected InputHandler input;

    protected boolean inAnimation;
    protected int timeBetweenAnim;
    protected int numOfAnim;

    public static ArrayList<Ability> abilityList = new ArrayList<>();

    public AbilityManager(Screen screen, int numOfAnim){
        this.screen = screen;
        this.numOfAnim = numOfAnim; // number of animation frames in the whole animation
        inAnimation = false;
    }

    public abstract void tick();

    public abstract void renderSprite(double x, double y);

    public abstract void castAbility(int x, int y);

    public boolean isInAnimation(){
        return inAnimation;
    }

    public void setInAnimation(boolean inAnimation){
        this.inAnimation = inAnimation;
    }
}
