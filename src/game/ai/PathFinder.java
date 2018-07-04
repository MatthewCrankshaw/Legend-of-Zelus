package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;

import java.awt.*;
import java.util.ArrayList;

public class PathFinder {

    private Player player;
    private ArrayList<Enemy> enemies;

    private float heuristic[] = new float[]{1.5f    ,1      ,1.5f,
                                            1       ,1000   ,1,
                                            1.5f    ,1      ,1.5f};

    private ArrayList<Point> points = new ArrayList<>();

    //path variable for each enemy
    private ArrayList<ArrayList<Point> > paths = new ArrayList<>();
    //Visited List for each enemy
    private ArrayList<ArrayList<Point> > visitedList;

    int maxDistance;

    public PathFinder(Player player, ArrayList<Enemy> enemies){
        this.player = player;
        this.enemies = enemies;
        init();
    }

    private void init() {
        maxDistance = 200;

        points.add(new Point(9*8, 12*8));
        points.add(new Point(10*8, 11*8));
        points.add(new Point( 11*8, 11*8));
        points.add(new Point(12*8,13*8));
        points.add(new Point(12*8,13*8));
        points.add(new Point(11*8,14*8));
        points.add(new Point(10*8,15*8));
        points.add(new Point(12*8,16*8));
    }

    public ArrayList<ArrayList<Point>> pathFinder(){
        for(int i = 0; i < enemies.size(); i++){
            int currentDistance = 0;
            while(currentDistance <= maxDistance){
                Point playerPos;
                Point enemyPos;

                playerPos = new Point((int)player.getX()/8, (int)player.getY()/8);
                enemyPos = new Point((int)enemies.get(i).getX()/8,(int)enemies.get(i).getY()/8);

                paths.add(points);

                break;
            }
        }
        return paths;
    }


}
