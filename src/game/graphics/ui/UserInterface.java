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
    private UIButton menuButton;

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

        int circleBarSize = screen.getHeight() / 10;

        //setup health bar
        healthBar = new CircleProgressBar(screen, circleBarSize + circleBarSize/10, screen.getHeight()-circleBarSize -(circleBarSize/10), circleBarSize, "Life");
        healthBar.setBarColours(0xff0000, 0xffffff);
        healthBar.setCurrentBarPercent(player.getMaxLife(), player.getCurrentLife());

        //setup mana bar
        manaBar = new CircleProgressBar(screen,screen.getWidth() - circleBarSize - circleBarSize/10, screen.getHeight() - circleBarSize - circleBarSize/10, circleBarSize, "Mana");
        manaBar.setBarColours(0x0000ff, 0xffffff);
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());

        //setup experience bar
        experienceBar = new RectangleProgressBar(screen, screen.getWidth()/6, screen.getHeight() - 20, (screen.getWidth()/6)*4, 10, "Experience");
        experienceBar.setBarColours(0x444444,0x990099);

        //setup ability bar
        abilityBar = new RectangleProgressBar(screen, screen.getWidth()/2 - 50, 16, 100, 10, "Ability");
        abilityBar.setBarColours(0x00ff00, 0x007700);

        //setup menu button
        menuButton = new UIButton(screen,  10, 10, 30, 20, "ESC");
        menuButton.setColour(0xaa0000, 0xffff00);
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
            menuButton.render();

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
        screen.renderString(screen.getWidth() - 116, 0, "P1: " + (int)(player.getX()/8) + " " + (int)(player.getY()/8), false, colour);
        for (int i = 0; i < enemies.size(); i++) {
            int x = screen.getWidth() - 116;
            int y = 0;
            int yp = y + (8*(i+1));

            double playerPosX = player.getX() / 8;
            double playerPosY = player.getY() / 8;
            double enemyPosX = enemies.get(i).getX() / 8;
            double enemyPosY = enemies.get(i).getY() / 8;

            int dist = (int) Math.sqrt((Math.pow((playerPosX - enemyPosX), 2.0)) + (Math.pow((playerPosY - enemyPosY), 2.0)));
            String s = "E" + (i + 1) + ": " + (int) enemyPosX + " " + (int) enemyPosY + " " + dist;
            screen.renderString(x, yp, s, false, colour);
        }
    }

    private void showEnemyPaths(int colour){
        for(int i = 0; i < enemies.size(); i++){
            ArrayList<Point> path = ai.getAIPath(i);
            screen.renderConnectedLine(path, 8, 8, colour, true);
        }
    }

    public int checkInputEvent(int x, int y){
        //return 0 means no buttons pressed
        //return 1 means a button was pressed but no action by InputHandler necessary
        //return -1 means game was paused

        if (menuButton.isPressed(x, y)){
            setGamePaused(true);
            return -1;
        }else{
            setGamePaused(false);
        }
        return 0;
    }

    public void setGamePaused(boolean paused){
        this.gamePaused = paused;
    }

    public boolean isGamePaused(){
        return this.gamePaused;
    }

    public void sendUserInput(int number){
        System.out.println("User input " + number);
    }
}
