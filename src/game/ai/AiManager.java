package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.ArrayList;

public class AiManager {

    private Player friendPlayer;
    private ArrayList<Enemy> characters;

    private PathFinder pathFinder;


    private ArrayList<ArrayList<Point>> moveSet = new ArrayList<>();

    private Point points[] = {
            new Point(9*8, 12*8),
            new Point(10*8, 11*8),
            new Point( 11*8, 11*8),
            new Point(12*8, 12*8),
            new Point(12*8,13*8),
            new Point(11*8,14*8),
            new Point(10*8,15*8),
            new Point(9*8,16*8),
            new Point(10*8,16*8),
            new Point(11*8,16*8),
            new Point(12*8,16*8),
    };

    private int moveCounter[];

    public AiManager(Player friendPlayer, ArrayList<Enemy> characters){
        this.friendPlayer = friendPlayer;
        this.characters = characters;
        this.moveCounter = new int[characters.size()];
        pathFinder = new PathFinder(friendPlayer, characters);
        moveSet = pathFinder.pathFinder();
        for(int i = 0; i < moveCounter.length; i++){
            moveCounter[i] = 0;
        }
    }

    public void tick(){

        for(int i = 0; i < characters.size(); i++) {
            int currentTileX, currentTileY;

            currentTileX = (int)characters.get(i).getX();
            currentTileY = (int)characters.get(i).getY();

            System.out.println("mov " + moveSet.size() + " path " + moveSet.get(i).size() + " counter " + moveCounter[i]);
            int tileX = moveSet.get(i).get(moveCounter[i]).x;
            int tileY = moveSet.get(i).get(moveCounter[i]).y;

            if(tileX == currentTileX && tileY == currentTileY){
                if (moveCounter[i] == moveSet.get(i).size() - 1){
                    characters.get(i).warpLocation(moveSet.get(i).get(0).x,moveSet.get(i).get(0).y);
                    moveCounter[i] = 0;
                }else{
                    moveCounter[i]++;
                }
            }else {
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
