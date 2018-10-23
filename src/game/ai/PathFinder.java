package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.levels.Level;

import java.awt.*;
import java.util.ArrayList;

public class PathFinder {

    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<ArrayList<Point> > paths;
    private Level level;

    public PathFinder(Player player, ArrayList<Enemy> enemies, Level level){
        this.player = player;
        this.enemies = enemies;
        this.level = level;
        this.paths = new ArrayList<>();
    }

    // Loop through all enemies and calculate their path
    public ArrayList<ArrayList<Point>> getEnemyPaths(){
        paths.clear();
        for(Enemy e : enemies) {
            ArrayList<Point> p;
            p = aStar(e);
            paths.add(p);
        }
        return paths;
    }

                // A* algorithm for finding a path
                private ArrayList<Point> aStar(Enemy enemy){
                    ArrayList<Node> openSet = new ArrayList<>();
                    ArrayList<Node> closeSet = new ArrayList<>();

                    Point start = pixelPosToChunkPos((int)enemy.getX(), (int)enemy.getY());
                    Point goal = pixelPosToChunkPos((int)player.getX(), (int)player.getY());

                    Node startNode = new Node(start, calculateHeuristic(start, goal), 0, null);
                    openSet.add(startNode);

                    int maxIter = 500, currentIter = 0;
                    while(!openSet.isEmpty() && currentIter < maxIter){
                        currentIter++;
                        Node currentNode = openSet.get(lowestFcostNodeId(openSet));
                        if(currentNode.getPosition().equals(goal)){
                            //Goal Found
                            return constructPath(currentNode);

                        }
                        openSet.remove(lowestFcostNodeId(openSet));
                        closeSet.add(currentNode);

                        // Look at each neighbour
                        ArrayList<Node> neighbours = getNeighbors(currentNode, goal);
                        for(Node n : neighbours) {
                if (!isNodeInList(n, closeSet)) {
                    if (!isNodeInList(n, openSet)) {
                        openSet.add(n);
                    } else {
                        Node openN = getNodeFromList(n, openSet);
                        if (n.getG() < openN.getG()) {
                            openN.setG(n.getG());
                            openN.setParent(n.getParent());
                        }
                    }
                }
            }
        }
        return null; // no path found
    }

    // Pixel position is top left of 16 pixel character
    private Point pixelPosToChunkPos(int x, int y){
        Point p = new Point();
        p.x = x / 16;
        p.y = y / 16;
        return p;
    }

    // Gives the top left hand corner position of character
    private Point chunkPosToPixelPos(Point chunkPos){
        Point p = new Point();
        p.x = (chunkPos.x * 16);
        p.y = (chunkPos.y * 16);
        return p;
    }

    private ArrayList<Point> constructPath(Node n){
        ArrayList<Node> pathNodes = new ArrayList<>();
        ArrayList<Point> pathPoints = new ArrayList<>();
        pathNodes.add(n);
        while(n.getParent() != null){
            n = n.getParent();
            pathNodes.add(n);
        }

        //reverse the order and start on the second chunk to avoid back and forth movement
        for(int i = pathNodes.size() -2; i > 0; i--){
            pathPoints.add(chunkPosToPixelPos(pathNodes.get(i).getPosition()));
        }

        return pathPoints;
    }

    private int calculateHeuristic(Point a, Point b){
        return (int) Math.sqrt((Math.pow((b.x - a.x), 2.0)) + (Math.pow((b.y - a.y), 2.0)));
    }

    private boolean isChunkBlocked(Point chunkPos){
        Point p = new Point();
        p.x = (chunkPos.x * 16) + 4;
        p.y = (chunkPos.y * 16) + 4;
        if (level.tileCollision(p.x, p.y, 16, 0,0,0,0, -1)){
            return true;
        }
        p.x = (chunkPos.x * 16) + 12;
        p.y = (chunkPos.y * 16) + 4;
        if (level.tileCollision(p.x, p.y, 16, 0,0,0,0, -1)){
            return true;
        }
        p.x = (chunkPos.x * 16) + 4;
        p.y = (chunkPos.y * 16) + 12;
        if (level.tileCollision(p.x, p.y, 16, 0,0,0,0, -1)){
            return true;
        }
        p.x = (chunkPos.x * 16) + 12;
        p.y = (chunkPos.y * 16) + 12;
        if (level.tileCollision(p.x, p.y, 16, 0,0,0,0, -1)){
            return true;
        }
        return false;
    }

    private int lowestFcostNodeId(ArrayList<Node> nodes){
        int id = 0;
        float lowestFCost = 1000000.0f;
        for(int i = 0; i < nodes.size(); i ++){
            if(lowestFCost > nodes.get(i).getFCost()){
                lowestFCost = nodes.get(i).getFCost();
                id = i;
            }
        }
        return id;
    }

    private boolean isNodeInList(Node thisNode, ArrayList<Node> nodes){
        for(Node n : nodes){
            if(thisNode.compareNode(n)){
                return true;
            }
        }
        return false;
    }

    private Node getNodeFromList(Node thisNode, ArrayList<Node> nodes){
        for(Node n: nodes){
            if (thisNode.getPosition().equals(n.getPosition())){
                return n;
            }
        }
        return null; // should not happen
    }

    // check if a path is already taken by another character or an enemy is in the way
    private boolean isPathTaken(Point pos){
        for(int i = 0; i < paths.size(); i++){
            if(paths == null)return false;
            if(paths.get(i) == null) return false;
            for(int j = 0; j < paths.get(i).size(); j++){
                if (pos.equals(new Point(paths.get(i).get(j).x/16, paths.get(i).get(j).y/16))){
                    return true;
                }
            }

            for(Enemy e : enemies){
                if(pos.equals(pixelPosToChunkPos((int)e.getX(), (int)e.getY()))){
                    return true;
                }
            }
        }
        return false;
    }

    // Get all of the neighbor nodes that are not blocked
    private ArrayList<Node> getNeighbors(Node node, Point goal){
        ArrayList<Node> neighbors = new ArrayList<>();
        Point neighborPos;

        //top left
        neighborPos = new Point(node.getPosition().x - 1, node.getPosition().y - 1);
        if(!isChunkBlocked(neighborPos) && !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1.414f, node));
        }
        //top mid
        neighborPos = new Point(node.getPosition().x, node.getPosition().y - 1);
        if(!isChunkBlocked(neighborPos) && !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1f, node));
        }
        //top right
        neighborPos = new Point(node.getPosition().x+1, node.getPosition().y - 1);
        if(!isChunkBlocked(neighborPos)&& !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1.414f, node));
        }
        //mid left
        neighborPos = new Point(node.getPosition().x-1, node.getPosition().y);
        if(!isChunkBlocked(neighborPos)&& !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1f, node));
        }
        //mid right
        neighborPos = new Point(node.getPosition().x+1, node.getPosition().y);
        if(!isChunkBlocked(neighborPos)&& !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1f, node));
        }
        //bot left
        neighborPos = new Point(node.getPosition().x-1, node.getPosition().y + 1);
        if(!isChunkBlocked(neighborPos)&& !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1.414f, node));
        }
        //bot mid
        neighborPos = new Point(node.getPosition().x, node.getPosition().y + 1);
        if(!isChunkBlocked(neighborPos)&& !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1f, node));
        }
        //bot right
        neighborPos = new Point(node.getPosition().x+1, node.getPosition().y + 1);
        if(!isChunkBlocked(neighborPos)&& !isPathTaken(neighborPos)) {
            neighbors.add(new Node(neighborPos, calculateHeuristic(neighborPos, goal), node.getG() + 1.414f, node));
        }
        return neighbors;
    }
}
