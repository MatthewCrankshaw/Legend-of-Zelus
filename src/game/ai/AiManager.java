package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AiManager {

    private Level level;
    private Screen screen;
    private PathFinder pathFinder;

    private long pathFindLastTime;
    private float pathFindInterval; //in seconds

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<ArrayList<Point>> moveSet;
    private int moveCounter[];

    private int numberOfEnemies = 3;

    public AiManager(Player friendPlayer, Level level, Screen screen){
        this.level = level;
        this.screen = screen;
        initializeEnemies();
        this.moveCounter = new int[numberOfEnemies];
        this.pathFinder = new PathFinder(friendPlayer, enemies, level);
        pathFindLastTime = System.currentTimeMillis();
        pathFindInterval = 0.2f;
        moveSet = pathFinder.pathFinder();
    }

    public void tick(){
        //Recalculate the path every so often as described by the pathFindInterval
        long currentTime = System.currentTimeMillis();
        if((currentTime - pathFindLastTime)/1000.0f  >= pathFindInterval) {
            moveSet = pathFinder.pathFinder();
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
        for(int i = 0; i < numberOfEnemies; i++){
            Random rand = new Random();
            rand.setSeed(System.currentTimeMillis());
            enemies.add(new Enemy((rand.nextInt() % 200), (rand.nextInt() % 200), level, screen, "name", 1, PlayerSprite.enemySprites));
            level.add(enemies.get(i));
        }
    }

    public ArrayList<Point> getAIPath(int character) {
        return moveSet.get(character);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
