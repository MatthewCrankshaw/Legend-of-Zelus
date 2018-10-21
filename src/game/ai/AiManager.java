package game.ai;

import game.entities.Spawner;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.graphics.ui.UserInterface;
import game.levels.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AiManager {

    private Level level;
    private Screen screen;
    private UserInterface ui;
    private PathFinder pathFinder;

    private long pathFindLastTime;
    private float pathFindInterval; //in seconds

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<ArrayList<Point>> moveSet;
    private ArrayList<Integer> moveCounter;

    public AiManager(Player friendPlayer, Level level, Screen screen, UserInterface ui){
            this.level = level;
            this.screen = screen;
            this.ui = ui;
            initializeEnemies();
            this.moveCounter = new ArrayList<>();
            this.pathFinder = new PathFinder(friendPlayer, enemies, level);
            pathFindLastTime = System.currentTimeMillis();
            pathFindInterval = 0.1f;
            moveSet = pathFinder.getEnemyPaths();
        }

    public void tick(){
        //Recalculate the path every so often as described by the pathFindInterval
        long currentTime = System.currentTimeMillis();
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
                ui.addLabel(screen, (int)enemies.get(i).getX(), (int)enemies.get(i).getY(),"10 Exp", true, true, 1000, 0xff000000);
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
        int numberOfZombies = 2;
        int numberOfEnemyWiz = 1;

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
