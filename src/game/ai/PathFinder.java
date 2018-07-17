package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;
import game.levels.Level;

import java.awt.*;
import java.util.ArrayList;

public class PathFinder {

    private Player player;
    private ArrayList<Enemy> enemies;
    private Level level;
    private float heuristic[] = new float[]{1.2f ,1 ,1.2f,
                                            1 ,1 ,1,
                                            1.2f ,1 ,1.2f};

    private int maxDistance;

    public PathFinder(Player player, ArrayList<Enemy> enemies, Level level){
        this.player = player;
        this.enemies = enemies;
        this.level = level;
        init();
    }

    private void init() {
        maxDistance = 100;
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
                double[] nextStepDist = new double[9];

                //create array of next steps around character
                nextStep = new Point[]{
                        new Point(centerE.x + 1, centerE.y + 1), new Point(centerE.x, centerE.y + 1), new Point(centerE.x - 1, centerE.y + 1),
                        new Point(centerE.x + 1, centerE.y), new Point(centerE.x, centerE.y), new Point(centerE.x - 1, centerE.y),
                        new Point(centerE.x + 1, centerE.y - 1), new Point(centerE.x, centerE.y - 1), new Point(centerE.x - 1, centerE.y - 1)
                };

                //calculate all of the distances
                for(int i = 0; i < 9; i++){
                    nextStepDist[i] = euclidDist(centerP, nextStep[i]) + heuristic[i];
                }


                int dir = findMinDist(nextStepDist);
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
            paths.add(points);
        }
        return paths;
    }

    private int euclidDist(Point a, Point b){
        return (int) Math.sqrt((Math.pow((b.x - a.x), 2.0)) + (Math.pow((b.y - a.y), 2.0)));
    }

    private int findMinDist(double[] distArray){
        int index = 0;
        double min = Double.MAX_VALUE;
        for(int i = 0; i < distArray.length; i++){
            if(distArray[i] <= min){
                index = i;
                min = distArray[i];
                //System.out.println("min: " + min);
            }
        }
        return index;
    }
}
