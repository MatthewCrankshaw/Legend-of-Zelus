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

    public AbilityManager(Screen screen, InputHandler input, int numOfAnim){
        this.screen = screen;
        this.input = input;
        this.numOfAnim = numOfAnim; // number of animation frames in the whole animation
        inAnimation = false;
    }

    public abstract void tick();

    public abstract void renderSprite(double x, double y);

    public abstract void castAbility(int x, int y);

    public double getDir(){
        int mouseX, mouseY;

        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

        double dx = mouseX - Game.WIDTH*Game.SCALE/2;
        double dy = mouseY - Game.HEIGHT*Game.SCALE/2;

        return Math.atan2(dy, dx);
    }

    public boolean isInAnimation(){
        return inAnimation;
    }

    public void setInAnimation(boolean inAnimation){
        this.inAnimation = inAnimation;
    }
}
