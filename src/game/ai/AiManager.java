package game.ai;

import game.entities.mob.Enemy;
import game.entities.mob.Player;

import java.util.ArrayList;
import java.util.Random;

public class AiManager {

    private Player friendPlayer;
    private ArrayList<Enemy> characters;
    private float pos = 0;

    public AiManager(Player friendPlayer, ArrayList<Enemy> characters){
        this.friendPlayer = friendPlayer;
        this.characters = characters;
    }

    public void tick(){
        for(int i = 0; i < characters.size(); i++) {
            Random rand = new Random();
            rand.setSeed(System.nanoTime());
            int randNum = Math.abs(rand.nextInt()%100);

            int r = 70;

            int theta = 360 * i / characters.size();
            theta += (int)pos;
            pos += 0.02;

            float x = (float) (r * Math.cos(Math.toRadians(theta)) + friendPlayer.x);
            float y = (float) (r * Math.sin(Math.toRadians(theta)) + friendPlayer.y);

            if(randNum < 80) {
                if((int)x == (int) characters.get(i).x){

                }else if (x > characters.get(i).x) {
                    characters.get(i).x++;
                } else if (x < characters.get(i).x) {
                    characters.get(i).x--;
                }
            }

            randNum = Math.abs(rand.nextInt()%100);

            if(randNum < 80) {
                if((int)y == (int) characters.get(i).y){

                }else if (y > characters.get(i).y) {
                    characters.get(i).y++;
                } else if (y < characters.get(i).y) {
                    characters.get(i).y--;
                }
            }
        }
    }
}
