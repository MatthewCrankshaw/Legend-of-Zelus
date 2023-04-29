package game.ai;

import game.entities.Spawner;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;

import game.graphics.ui.UserInterface;
import game.levels.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class AiManager {
    private Level level;
    private Screen screen;
    private UserInterface ui;
    private PathFinder pathFinder;
    private Spawner spawner;
    private long pathFindLastTime;
    private float pathFindInterval; //in seconds
    private long enemySpawnLastTime;
    private float enemySpawnInterval;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Queue<Point>> moveSets;

    public AiManager(Level level, Screen screen, UserInterface ui, Spawner spawner, PathFinder pathFinder, ArrayList<Enemy> enemies){
        this.level = level;
        this.screen = screen;
        this.ui = ui;
        this.spawner = spawner;
        this.pathFinder = pathFinder;
        this.enemies = enemies;

        pathFindLastTime = System.currentTimeMillis();
        pathFindInterval = 0.6f;

        enemySpawnLastTime = System.currentTimeMillis();
        enemySpawnInterval = 5.5f;

        moveSets = pathFinder.getEnemyPaths(enemies);
    }

    public void tick() {
        //Recalculate the path every so often as described by the pathFindInterval
        long currentTime = System.currentTimeMillis();
        if ((currentTime - enemySpawnLastTime) / 1000.0f >= enemySpawnInterval) {
            spawnZombieAI();
            enemySpawnLastTime = currentTime;
            moveSets = pathFinder.getEnemyPaths(enemies);
        }

        currentTime = System.currentTimeMillis();
        if ((currentTime - pathFindLastTime) / 1000.0f >= pathFindInterval) {
            moveSets = pathFinder.getEnemyPaths(enemies);
            pathFindLastTime = currentTime;
        }

        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isAlive()) {
                level.removeEntity(enemies.get(i));
                ui.addLabel(screen, (int) enemies.get(i).getX(), (int) enemies.get(i).getY(), "10 Exp", true, true, 1000, 0xffffff00);
                enemies.remove(i);
                continue;
            }

            int currentTileX, currentTileY;
            currentTileX = (int) enemies.get(i).getX();
            currentTileY = (int) enemies.get(i).getY();

            if (moveSets.get(i) == null || moveSets == null || moveSets.isEmpty() || moveSets.get(i).isEmpty())
                continue;

            Point tilexy = moveSets.get(i).peek();

            if (tilexy.x == currentTileX && tilexy.y == currentTileY) {
                if (moveSets.get(i).size() > 1) {
                    moveSets.get(i).remove();
                }
            } else {
                if (tilexy.x > currentTileX) {
                    enemies.get(i).moveRight();
                } else if (tilexy.x < currentTileX) {
                    enemies.get(i).moveLeft();
                }

                if (tilexy.y > currentTileY) {
                    enemies.get(i).moveDown();
                } else if (tilexy.y < currentTileY) {
                    enemies.get(i).moveUp();
                }
            }
        }
    }

    public ArrayList<Point> getAIPath(int character) {
        if(moveSets.get(character) == null){return null;}
        return new ArrayList<>(moveSets.get(character));
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    private void spawnZombieAI(){
        Random rand = new Random();
        int x = Math.abs(rand.nextInt() % 300);
        int y = Math.abs(rand.nextInt() % 500);
        int type = Math.abs(rand.nextInt() % 3);

        switch (type){
            case 0:
                enemies.addAll(spawner.spawnEnemies(x, y, Spawner.Type.ENEMY_ZOMBIE, 1));
                break;
            case 1:
                enemies.addAll(spawner.spawnEnemies(x, y, Spawner.Type.ENEMY_WIZARD, 1));
                break;
            case 2:
                enemies.addAll(spawner.spawnEnemies(x, y, Spawner.Type.ENEMY_DEATH_KEEPER, 1));
                break;
        }
    }
}
