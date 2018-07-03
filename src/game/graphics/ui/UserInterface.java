package game.graphics.ui;

import game.Game;
import game.ai.AiManager;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Matthew.c on 02/03/2017.
 */
public class UserInterface {

    public enum MenuLevel{PAUSE, SETTINGS}

    protected Screen screen;
    protected Player player;
    protected ArrayList<Enemy> enemies;
    private AiManager ai;

    private MenuLevel currentManuLevel;

    private CircleProgressBar healthBar, manaBar;
    private RectangleProgressBar experienceBar;
    private RectangleProgressBar abilityBar;

    private boolean gamePaused;

    public UserInterface(Screen screen, Player player, ArrayList<Enemy> enemies, AiManager ai){
        this.screen = screen;
        this.player = player;
        this.enemies = enemies;
        this.ai = ai;
        gamePaused = false;
        init();
    }

    public void init(){

        currentManuLevel = MenuLevel.PAUSE;

        //setup health bar
        healthBar = new CircleProgressBar(screen, 60, screen.height - 60, 50, "Life");
        healthBar.setBarFillColour(0xff0000);
        healthBar.setBarBorderColour(0xffffff);
        healthBar.setCurrentBarPercent(player.getMaxLife(), player.getCurrentLife());

        //setup mana bar
        manaBar = new CircleProgressBar(screen,screen.width - 60, screen.height - 60, 50, "Mana");
        manaBar.setBarFillColour(0x0000ff);
        manaBar.setBarBorderColour(0xffffff);
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());

        //setup experience bar
        experienceBar = new RectangleProgressBar(screen, 120, screen.height - 20, screen.width - 240, 10, "Experience");
        experienceBar.setBarFillColour(0x444444);
        experienceBar.setBarBorderColour(0x990099);

        //setup ability bar
        abilityBar = new RectangleProgressBar(screen, Game.WIDTH/2 - 50, 16, 100, 10, "Ability");
        abilityBar.setBarFillColour(0x00ff00);
        abilityBar.setBarBorderColour(0x007700);
    }

    public void tick(){
        healthBar.setCurrentBarPercent(player.getMaxLife(),player.getCurrentLife());
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());
        experienceBar.setCurrentBarPercentage(player.getMaxExperience(), player.getCurrentExperience());
        abilityBar.setCurrentBarPercentage(100, 50);
    }

    public void render() {
        if(!gamePaused){
            healthBar.render();
            manaBar.render();
            experienceBar.render();
            abilityBar.render();

            showPlayerPositions(0x000055);
            showEnemyPaths(0x000055);
        }else{
            switch (currentManuLevel){
                case PAUSE:
                    screen.renderString((Game.WIDTH/2), (Game.HEIGHT/2) - (Game.HEIGHT/4), "--- Paused ---", true,0x777777);
                    screen.renderString((Game.WIDTH/2)- (5*8), (Game.HEIGHT/2) - (Game.HEIGHT/4) + 16, "1. Quit", false,0x777777);
                    screen.renderString((Game.WIDTH/2)- (5*8), (Game.HEIGHT/2) - (Game.HEIGHT/4) + 24, "2. Settings", false,0x777777);
                    break;
                case SETTINGS:
                    screen.renderString((Game.WIDTH/2), (Game.HEIGHT/2) - (Game.HEIGHT/4), "--- Settings---", true,0x777777);
                    screen.renderString((Game.WIDTH/2)- (5*8), (Game.HEIGHT/2) - (Game.HEIGHT/4) + 16, "1. Draw Player and Enemy Positions", false,0x777777);
                    screen.renderString((Game.WIDTH/2)- (5*8), (Game.HEIGHT/2) - (Game.HEIGHT/4) + 24, "2. Draw Enemy Paths", false,0x777777);
                    break;
            }
        }
    }

    private void showPlayerPositions(int colour){
        screen.renderString(0, 0, "P1: " + (int)(player.getX()/8) + " " + (int)(player.getY()/8), false, colour);
        for (int i = 0; i < enemies.size(); i++) {
            int x = 0;
            int y = 0;
            if (i >= 40) {
                x += 8;
                y = 0;
            }
            int xp = x * (14);
            int yp = y + (8 * ((i % 40) + 1));

            double playerPosX = player.getX() / 8;
            double playerPosY = player.getY() / 8;
            double enemyPosX = enemies.get(i).getX() / 8;
            double enemyPosY = enemies.get(i).getY() / 8;

            int dist = (int) Math.sqrt((Math.pow((playerPosX - enemyPosX), 2.0)) + (Math.pow((playerPosY - enemyPosY), 2.0)));
            String s = "E" + (i + 1) + ": " + (int) enemyPosX + " " + (int) enemyPosY + " " + dist;
            screen.renderString(xp, yp, s, false, colour);
        }
    }

    private void showEnemyPaths(int colour){
        //Todo will need to be replaced with proper paths when path finding is working
        for(int i = 0; i < enemies.size(); i++){
            Point path[] = ai.getAIPath(i);
            screen.renderConnectedLine(path, 8, 8, 0x00ff00, true);
        }
    }

    public void setGamePaused(boolean paused){
        this.gamePaused = paused;
    }

    public void sendUserInput(int number){
        System.out.println("User input " + number);
    }
}
