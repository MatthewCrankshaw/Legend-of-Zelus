package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;

import java.util.ArrayList;

public class AiManager {

    private Player friendPlayer;
    private ArrayList<Enemy> characters;

    public AiManager(Player friendPlayer, ArrayList<Enemy> characters){
        this.friendPlayer = friendPlayer;
        this.characters = characters;
    }

    public void tick(){
        for(int i = 0; i < characters.size(); i++) {

            int r = 8*8+1;

            int theta = 360 * i / characters.size();

            float x = (float) (r * Math.cos(Math.toRadians(theta)) + friendPlayer.getX());
            float y = (float) (r * Math.sin(Math.toRadians(theta)) + friendPlayer.getY());


            if (x > characters.get(i).getX() && (int) x != (int) characters.get(i).getX()) {
                characters.get(i).moveRight();
            } else if (x < characters.get(i).getX() && (int) x != (int) characters.get(i).getX()) {
                characters.get(i).moveLeft();
            }

            if (y > characters.get(i).getY() && (int) y != (int) characters.get(i).getY()) {
                characters.get(i).moveDown();
            } else if (y < characters.get(i).getY() && (int) y != (int) characters.get(i).getY()) {
                characters.get(i).moveUp();
            }
        }
    }
}
