package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.levels.Level;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class AiManager {

    private Player friendPlayer;
    private ArrayList<Enemy> characters;
    private Level level;
    private long pathFindLastTime;
    private float pathFindInterval; //in seconds

    private PathFinder pathFinder;

    private ArrayList<ArrayList<Point>> moveSet;

    private int moveCounter[];

    public AiManager(Player friendPlayer, ArrayList<Enemy> characters, Level level){
        this.friendPlayer = friendPlayer;
        this.characters = characters;
        this.moveCounter = new int[characters.size()];
        this.level = level;
        pathFinder = new PathFinder(friendPlayer, characters, level);
        pathFindLastTime = System.currentTimeMillis();
        pathFindInterval = 0.1f;
        moveSet = pathFinder.pathFinder();
        for(int i = 0; i < moveCounter.length; i++){
            moveCounter[i] = 0;
        }
    }

    public void tick(){
        //Recalculate the path every so often as described by the pathFindInterval
        long currentTime = System.currentTimeMillis();
        if((currentTime - pathFindLastTime)/1000.0f  >= pathFindInterval) {
            moveSet = pathFinder.pathFinder();
            pathFindLastTime = currentTime;
            for(int i = 0; i < characters.size(); i++){
                moveCounter[i] = 0;
            }
        }

        for(int i = 0; i < characters.size(); i++) {
            int currentTileX, currentTileY;
            currentTileX = (int)characters.get(i).getX();
            currentTileY = (int)characters.get(i).getY();

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
                    characters.get(i).moveRight();
                } else if (tileX < currentTileX) {
                    characters.get(i).moveLeft();
                }

                if (tileY > currentTileY) {
                    characters.get(i).moveDown();
                } else if (tileY < currentTileY) {
                    characters.get(i).moveUp();
                }
            }
        }
    }

    public ArrayList<Point> getAIPath(int character) {
        return moveSet.get(character);
    }
}

//
