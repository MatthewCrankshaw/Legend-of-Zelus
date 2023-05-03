package game.entities;

import game.animators.mob_animators.CharacterAnimator;
import game.entities.mob.Enemy;
import game.entities.particles.Particle;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.levels.Level;
import game.levels.tile.TileManager;

import java.util.ArrayList;

/**
 * Created by Matthew.c on 08/02/2017.
 */
public class Spawner {

    private Screen screen;
    private Level level;
    protected TileManager tileManager;
    protected SpriteRegistry spriteRegistry;

    public enum Type {
        ENEMY_WIZARD, ENEMY_ZOMBIE, ENEMY_DEATH_KEEPER, PARTICAL, ENVIRONMENT_TREE
    }

    public Spawner(Level level, Screen screen, TileManager tileManager, SpriteRegistry spriteRegistry){
        this.level = level;
        this.screen = screen;
        this.tileManager = tileManager;
        this.spriteRegistry = spriteRegistry;
    }

    public void spawnEntities(int x, int y, Type type, int amount, Sprite particleSprite){
        for (int i = 0; i < amount; i++) {
            switch (type){
                case PARTICAL:
                    Entity entity = new Particle(level, x, y, 500, particleSprite);
                    level.add(entity);
                    break;
            }
        }
    }

    public ArrayList<Enemy> spawnEnemies(int x, int y, Type type, int amount){
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Enemy e;
            CharacterAnimator animator;
            switch (type){
                case ENEMY_WIZARD:
                    animator = new CharacterAnimator(screen, 4, spriteRegistry.getCharacter(SpriteRegistry.AnimatedCharacterSprites.ENEMY), 120, 1, spriteRegistry);
                    e = new Enemy(x, y, level, screen, "Name", 1, animator, tileManager);
                    enemies.add(e);
                    level.add(e);
                    break;
                case ENEMY_ZOMBIE:
                    animator = new CharacterAnimator(screen, 4, spriteRegistry.getCharacter(SpriteRegistry.AnimatedCharacterSprites.ZOMBIE), 120, 1, spriteRegistry);
                    e = new Enemy(x, y, level, screen, "Name", 1, animator, tileManager);
                    enemies.add(e);
                    level.add(e);
                    break;
                case ENEMY_DEATH_KEEPER:
                    animator = new CharacterAnimator(screen, 4, spriteRegistry.getCharacter(SpriteRegistry.AnimatedCharacterSprites.DEATH_KEEPER), 120, 1, spriteRegistry);
                    e = new Enemy(x, y, level, screen, "Name", 1, animator, tileManager);
                    enemies.add(e);
                    level.add(e);
                    break;
            }
        }
        return enemies;
    }
}
