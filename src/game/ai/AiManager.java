package game.ai;

import game.entities.Spawner;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AiManager {

    private Level level;
    private Screen screen;
    private PathFinder pathFinder;

    private long pathFindLastTime, spawnLastTime;
    private float pathFindInterval, spawnInterval; //in seconds

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<ArrayList<Point>> moveSet;
    private ArrayList<Integer> moveCounter;
    //private int moveCounter[];

    private int numberOfZombies = 2;
    private int numberOfEnemyWiz = 1;

    public AiManager(Player friendPlayer, Level level, Screen screen){
            this.level = level;
            this.screen = screen;
            initializeEnemies();
            this.moveCounter = new ArrayList<>();
            this.pathFinder = new PathFinder(friendPlayer, enemies, level);
            pathFindLastTime = System.currentTimeMillis();
            pathFindInterval = 0.3f;
            spawnInterval = 2.0f;
            moveSet = pathFinder.getEnemyPaths();
        }

    public void tick(){

        long currentTime = System.currentTimeMillis();
        if((currentTime - spawnLastTime)/1000.0f >= spawnLastTime){
            spawnLastTime = currentTime;
            level.spawnEnemiesInLevel(-10, -10, Spawner.Type.ENEMY_WIZARD,1);
        }


        //Recalculate the path every so often as described by the pathFindInterval
        currentTime = System.currentTimeMillis();
        if((currentTime - pathFindLastTime)/1000.0f  >= pathFindInterval) {
            moveSet = pathFinder.getEnemyPaths();
            pathFindLastTime = currentTime;
            for(int i = 0; i < enemies.size(); i++){
                moveCounter.add(0);
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
            int tileX = moveSet.get(i).get(moveCounter.get(i)).x;
            int tileY = moveSet.get(i).get(moveCounter.get(i)).y;

            if(tileX == currentTileX && tileY == currentTileY){
                if (moveCounter.get(i) >= moveSet.get(i).size() - 1){
                    moveCounter.set(i, 0);
                }else{
                    moveCounter.set(i, moveCounter.get(i));
                }
            }else {
                if (moveSet.isEmpty()){
                    continue;
                }
                tileX = moveSet.get(i).get(moveCounter.get(i)).x;
                tileY = moveSet.get(i).get(moveCounter.get(i)).y;

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
