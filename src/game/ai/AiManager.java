package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;

import java.util.ArrayList;
import java.util.Random;

public class AiManager {

    private Player friendPlayer;
    private ArrayList<Enemy> characters;

    public AiManager(Player friendPlayer, ArrayList<Enemy> characters){
        this.friendPlayer = friendPlayer;
        this.characters = characters;
    }

    public void tick(){
        for(int i = 0; i < characters.size(); i++) {

                int r = 70;

                int theta = 360 * i / characters.size();

                float x = (float) (r * Math.cos(Math.toRadians(theta)) + friendPlayer.x);
                float y = (float) (r * Math.sin(Math.toRadians(theta)) + friendPlayer.y);

                if (x > characters.get(i).x && (int) x != (int) characters.get(i).x) {
                    characters.get(i).moveRight();
                } else if (x < characters.get(i).x && (int) x != (int) characters.get(i).x) {
                    characters.get(i).moveLeft();
                }


                if (y > characters.get(i).y && (int) y != (int) characters.get(i).y) {
                    characters.get(i).moveDown();
                } else if (y < characters.get(i).y && (int) y != (int) characters.get(i).y) {
                    characters.get(i).moveUp();
                }

        }
    }
}
