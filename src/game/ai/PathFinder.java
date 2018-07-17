package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.levels.Level;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PathFinder {

    private Player player;
    private ArrayList<Enemy> enemies;
    private Level level;
    private float heuristic[] = new float[]{1.2f ,1 ,1.2f,
                                            1 ,1 ,1,
                                            1.2f ,1 ,1.2f};

    private int maxDistance;

    private ArrayList<Point> visitedList;

    public PathFinder(Player player, ArrayList<Enemy> enemies, Level level){
        this.player = player;
        this.enemies = enemies;
        this.level = level;
        init();
    }

    private void init() {
        maxDistance = 20;
        visitedList = new ArrayList<>();
    }

    public ArrayList<ArrayList<Point>> pathFinder(){
        ArrayList<ArrayList<Point> > paths = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();

        for(int enemyNum = 0; enemyNum < enemies.size(); enemyNum++){
            //Visited List for each enemy
            ArrayList<Point>visitedList = new ArrayList<>();

            boolean pathFound = false;
            int currentDistance = 0;

            Point playerPos = new Point((int)player.getX(), (int)player.getY());
            Point startingEnemyPos = new Point((int)enemies.get(enemyNum).getX(),(int)enemies.get(enemyNum).getY());

            while(currentDistance <= maxDistance){

                if(pathFound){
                    break;
                }

                Point centerE = new Point((startingEnemyPos.x+8)/8, (startingEnemyPos.y+8)/8);
                Point centerP = new Point((playerPos.x+8)/8, (playerPos.y+8)/8);


                //Array of the next points around the enemy that they can move
                Point[] nextStep;
                //Array of the cost to move to that each new step around character
                ArrayList<Double> nextStepDist = new ArrayList<>();

                //create array of next steps around character
                nextStep = new Point[]{
                        new Point(centerE.x + 1, centerE.y + 1), new Point(centerE.x, centerE.y + 1), new Point(centerE.x - 1, centerE.y + 1),
                        new Point(centerE.x + 1, centerE.y), new Point(centerE.x, centerE.y), new Point(centerE.x - 1, centerE.y),
                        new Point(centerE.x + 1, centerE.y - 1), new Point(centerE.x, centerE.y - 1), new Point(centerE.x - 1, centerE.y - 1)
                };

                //calculate all of the distances
                for(int i = 0; i < 9; i++){
                    double dist = euclidDist(centerP, nextStep[i]) + heuristic[i];
                    nextStepDist.add(dist);
                }

                ArrayList<Integer> dist = sortDistances(nextStepDist);
                int dir = dist.get(0);
                Point p;

                switch(dir){
                    case 0:
                        p = new Point((startingEnemyPos.x+8), (startingEnemyPos.y+8));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    case 1:
                        p = new Point((startingEnemyPos.x), (startingEnemyPos.y+8));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    case 2:
                        p = new Point((startingEnemyPos.x-8), (startingEnemyPos.y+8));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    case 3:
                        p = new Point((startingEnemyPos.x+8), (startingEnemyPos.y));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    case 4:
                        p = new Point((startingEnemyPos.x), (startingEnemyPos.y));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        pathFound = true;
                        break;
                    case 5:
                        p = new Point((startingEnemyPos.x-8), (startingEnemyPos.y));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    case 6:
                        p = new Point((startingEnemyPos.x+8), (startingEnemyPos.y-8));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    case 7:
                        p = new Point((startingEnemyPos.x), (startingEnemyPos.y-8));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    case 8:
                        p = new Point((startingEnemyPos.x-8), (startingEnemyPos.y-8));
                        points.add(p);
                        visitedList.add(new Point(p.x/8, p.y/8));
                        break;
                    default:
                        p = new Point((startingEnemyPos.x), (startingEnemyPos.y));
                        break;
                }
                currentDistance++;
                startingEnemyPos = p;
            }

            //If the player is too far away give up and
            if(currentDistance >= maxDistance){
                points.clear();
                points.add(new Point((int)enemies.get(enemyNum).getX(), (int)enemies.get(enemyNum).getY()));
                paths.add(points);
            }else{
                paths.add(points);
            }
        }
        return paths;
    }

    private boolean inVisitedList(Point p){
        for (Point vis : visitedList){
            if(vis.x == p.x && vis.y == p.y){
                System.out.println("visited");
                return true;
            }
        }
        return false;
    }

    private int euclidDist(Point a, Point b){
        return (int) Math.sqrt((Math.pow((b.x - a.x), 2.0)) + (Math.pow((b.y - a.y), 2.0)));
    }

    private ArrayList<Integer> sortDistances(ArrayList<Double> distArray){
        ArrayList<Integer> indexList = new ArrayList<>();
        int initSize = distArray.size();
        for(int i = 0; i < initSize; i++){
            int index = findMinDist(distArray);
            indexList.add(index);
            distArray.remove(index);
        }
        return indexList;
    }

    private int findMinDist(ArrayList<Double> distArray){
        int index = 0;
        double min = Double.MAX_VALUE;
        for(int i = 0; i < distArray.size(); i++){
            if(distArray.get(i) <= min){
                index = i;
                min = distArray.get(i);
            }
        }
        return index;
    }
}
