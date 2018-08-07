package game.ai;

import game.entities.Spawner;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.levels.Level;

import java.awt.*;
import java.util.ArrayList;

public class AiManager {

    private Level level;
    private Screen screen;
    private PathFinder pathFinder;

    private long pathFindLastTime;
    private float pathFindInterval; //in seconds

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<ArrayList<Point>> moveSet;
    private int moveCounter[];

    private int numberOfZombies = 3;
    private int numberOfEnemyWiz = 3;

    public AiManager(Player friendPlayer, Level level, Screen screen){
        this.level = level;
        this.screen = screen;
        initializeEnemies();
        this.moveCounter = new int[numberOfEnemyWiz + numberOfZombies];
        this.pathFinder = new PathFinder(friendPlayer, enemies, level);
        pathFindLastTime = System.currentTimeMillis();
        pathFindInterval = 0.3f;
        moveSet = pathFinder.getEnemyPaths();
    }

    public void tick(){
        //Recalculate the path every so often as described by the pathFindInterval
        long currentTime = System.currentTimeMillis();
        if((currentTime - pathFindLastTime)/1000.0f  >= pathFindInterval) {
            moveSet = pathFinder.getEnemyPaths();
            pathFindLastTime = currentTime;
            for(int i = 0; i < enemies.size(); i++){
                moveCounter[i] = 0;
            }
        }

        for(int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isAlive()){
                level.removeEntity(enemies.get(i));
                enemies.remove(i);
                continue;
            }
            int currentTileX, currentTileY;
            currentTileX = (int)enemies.get(i).getX();
            currentTileY = (int)enemies.get(i).getY();

            if(moveSet.isEmpty() || moveSet.get(i) == null || moveSet.get(i).isEmpty() ) continue;
            int tileX = moveSet.get(i).get(moveCounter[i]).x;
            int tileY = moveSet.get(i).get(moveCounter[i]).y;

            if(tileX == currentTileX && tileY == currentTileY){
                if (moveCounter[i] >= moveSet.get(i).size() - 1){
                    moveCounter[i] = 0;
                }else{
                    moveCounter[i]++;
                }
            }else {
                if (moveSet.isEmpty()){
                    continue;
                }
                tileX = moveSet.get(i).get(moveCounter[i]).x;
                tileY = moveSet.get(i).get(moveCounter[i]).y;

                if (tileX > currentTileX) {
                    enemies.get(i).moveRight();
                } else if (tileX < currentTileX) {
                    enemies.get(i).moveLeft();
                }

                if (tileY > currentTileY) {
                    enemies.get(i).moveDown();
                } else if (tileY < currentTileY) {
                    enemies.get(i).moveUp();
                }
            }
        }
    }

    private void initializeEnemies(){
        enemies.addAll(level.spawnEnemiesInLevel(-20, -20, Spawner.Type.ENEMY_ZOMBIE, numberOfZombies));
        enemies.addAll(level.spawnEnemiesInLevel(-20, -20, Spawner.Type.ENEMY_WIZARD, numberOfEnemyWiz));
    }

    public ArrayList<Point> getAIPath(int character) {
        return moveSet.get(character);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
