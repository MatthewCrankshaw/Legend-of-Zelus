package game.entities;

import game.animators.mob_animators.CharacterAnimator;
import game.entities.mob.Enemy;
import game.entities.particles.Particle;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Matthew.c on 08/02/2017.
 */
public class Spawner{

    private Screen screen;
    private Level level;

    public enum Type {
        ENEMY_WIZARD, ENEMY_ZOMBIE, ENEMY_DEATH_KEEPER, PARTICAL, ENVIRONMENT_TREE
    }

    public Spawner(Level level, Screen screen){
        this.level = level;
        this.screen = screen;
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

    public void spawnEnvironmentEntitiesInArea(int x, int y, int width, int height, Type type, int amount, Sprite environmentSprite){
        for (int i = 0; i < amount; i++){
            switch (type){
                case ENVIRONMENT_TREE:
                    Random rand = new Random();
                    int randX = rand.nextInt(width) + x;
                    int randY = rand.nextInt(height) + y;

                    Entity entity = new EnvironmentEntity(level, Sprite.tree_sprite, randX, randY, 2);
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
                    animator = new CharacterAnimator(screen, 4, PlayerSprite.enemySprites, 120, 1);
                    e = new Enemy(x, y, level, screen, "Name", 1, animator);
                    enemies.add(e);
                    level.add(e);
                    break;
                case ENEMY_ZOMBIE:
                    animator = new CharacterAnimator(screen, 4, PlayerSprite.zombieSprites, 120, 1);
                    e = new Enemy(x, y, level, screen, "Name", 1, animator);
                    enemies.add(e);
                    level.add(e);
                    break;
                case ENEMY_DEATH_KEEPER:
                    animator = new CharacterAnimator(screen, 4, PlayerSprite.deathkeeperSprites, 120, 1);
                    e = new Enemy(x, y, level, screen, "Name", 1, animator);
                    enemies.add(e);
                    level.add(e);
                    break;
            }
        }
        return enemies;
    }
}
