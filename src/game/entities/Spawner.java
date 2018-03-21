package game.entities;

import game.entities.particles.Particles;
import game.graphics.Screen;
import game.levels.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew.c on 08/02/2017.
 */
public class Spawner extends Entity{

    public enum Type {
        MOB, PARTICAL
    }

    private Type type;

    public Spawner(Level level, int x, int y, Type type, int amount){
        super(level);
        this.x = x;
        this.y = y;
        for (int i = 0; i < amount; i++) {
            if (type == Type.PARTICAL) {
                level.add(new Particles(level, x, y, 500));
            }
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {

    }
}
