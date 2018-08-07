package game.ai;

import java.awt.*;

public class Node {

    private Point position;
    Node parent;
    private float heuristic;
    private float g;


    public Node(Point pnt, float heuristic, float g, Node parent){
        this.position = pnt;
        this.heuristic = heuristic;
        this.g = g;
        this.parent = parent;
    }

    public Point getPosition() {
        return position;
    }

    public float getHeuristic() {
        return heuristic;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public float getFCost(){
        return g + heuristic;
    }

    public boolean compareNode(Node n){
        if(this.position.equals(n.getPosition())){
            return true;
        }else{
            return false;
        }
    }
}
