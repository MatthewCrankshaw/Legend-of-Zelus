package game.entities.ability.ability_managers;

import game.InputHandler;
import game.entities.ability.Ability;
import game.graphics.Screen;
import game.levels.Level;


import java.util.ArrayList;

/**
 * Created by Matthew.c on 01/02/2017.
 */
public abstract class AbilityManager {
    protected Screen screen;
    protected Level level;
    protected InputHandler input;

    protected boolean inAnimation;
    protected int timeBetweenAnim;
    protected int numOfAnim;

    public static ArrayList<Ability> abilityList = new ArrayList<>();

    public AbilityManager(Screen screen, InputHandler input, Level level, int numOfAnim){
        this.screen = screen;
        this.input = input;
        this.level = level;
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
