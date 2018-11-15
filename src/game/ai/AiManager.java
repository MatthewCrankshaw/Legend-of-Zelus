package game.ai;

import game.entities.Spawner;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;

import game.graphics.ui.UserInterface;
import game.levels.Level;
import org.omg.PortableServer.POA;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;

public class AiManager {

    private Level level;
    private Screen screen;
    private UserInterface ui;
    private PathFinder pathFinder;
    private Spawner spawner;

    private long pathFindLastTime;
    private float pathFindInterval; //in seconds

    private ArrayList<Enemy> enemies = new ArrayList<>();

//    private ArrayList<ArrayList<Point>> moveSet;
//    private ArrayList<Integer> moveCounter;

    private ArrayList<Queue<Point>> moveSets;

    public AiManager(Player friendPlayer, Level level, Screen screen, UserInterface ui){
        this.level = level;
        this.screen = screen;
        this.ui = ui;
        this.spawner = new Spawner(level, screen);
        initializeEnemies();
        this.pathFinder = new PathFinder(friendPlayer, enemies, level);
        pathFindLastTime = System.currentTimeMillis();
        pathFindInterval = 0.6f;
        moveSets = pathFinder.getEnemyPaths();
    }

    public void tick(){
        //Recalculate the path every so often as described by the pathFindInterval
        long currentTime = System.currentTimeMillis();
        if((currentTime - pathFindLastTime)/1000.0f  >= pathFindInterval) {
            moveSets = pathFinder.getEnemyPaths();
            pathFindLastTime = currentTime;
        }

        for(int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isAlive()){
                level.removeEntity(enemies.get(i));
                ui.addLabel(screen, (int)enemies.get(i).getX(), (int)enemies.get(i).getY(),"10 Exp", true, true, 1000, 0xffffff00);
                enemies.remove(i);
                continue;
            }

            int currentTileX, currentTileY;
            currentTileX = (int)enemies.get(i).getX();
            currentTileY = (int)enemies.get(i).getY();

            if(moveSets.get(i) == null || moveSets == null || moveSets.isEmpty() || moveSets.get(i).isEmpty()) continue;

            Point tilexy = moveSets.get(i).peek();

            if(tilexy.x == currentTileX && tilexy.y == currentTileY){
                if(moveSets.get(i).size() > 1) {
                    moveSets.get(i).remove();
                }
            }else {
                if (tilexy.x> currentTileX) {
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

    private void initializeEnemies(){
        int numberOfZombies = 1;
        int numberOfEnemyWiz = 1;
        int numberOfDeathKeepers = 1;

        enemies.addAll(spawner.spawnEnemies(20, 20, Spawner.Type.ENEMY_ZOMBIE, numberOfZombies));
        enemies.addAll(spawner.spawnEnemies(20, 20, Spawner.Type.ENEMY_WIZARD, numberOfEnemyWiz));
        enemies.addAll(spawner.spawnEnemies(20, 20, Spawner.Type.ENEMY_DEATH_KEEPER, numberOfDeathKeepers));
    }

    public ArrayList<Point> getAIPath(int character) {
        if(moveSets.get(character) == null){return null;}
        return new ArrayList<>(moveSets.get(character));
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
