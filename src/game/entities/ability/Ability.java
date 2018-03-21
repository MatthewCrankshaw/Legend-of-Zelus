package game.entities.ability;

import game.entities.Entity;
import game.graphics.Screen;
import game.levels.Level;

/**
 * Created by Matthew.c on 01/02/2017.
 */
public abstract class Ability extends Entity {

    protected boolean exploding;
    protected boolean fizzlingOut;

    public Ability(Level level, Screen screen){
        super(level);
        exploding = false;
        fizzlingOut = false;
    }

    public boolean isExploding(){
        return exploding;
    }

    public boolean isFizzlingOut(){
        return fizzlingOut;
    }

    public abstract void explode(Screen screen);

    public abstract void fizzleOut(Screen screen);


}
