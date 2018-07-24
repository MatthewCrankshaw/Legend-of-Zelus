package game.graphics.ui;

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

    public enum MenuLevel{NONE ,PAUSE, SETTINGS}

    protected Screen screen;
    protected Player player;
    protected ArrayList<Enemy> enemies;
    private AiManager ai;

    private MenuLevel currentManuLevel;

    private CircleProgressBar healthBar, manaBar;
    private RectangleProgressBar experienceBar;
    private RectangleProgressBar abilityBar;
    private ArrayList<RectangleProgressBar> enemyHealthBars;
    private UIButton menuButton;

    private final String[] pauseButtonNames = {"Paused", "Settings", "Back", "Quit"};
    private final String[] settingsButtonNames = {"Settings", "Draw Positions", "Draw Paths", "Back"};
    private ArrayList<UIButton> pauseButtons;
    private ArrayList<UIButton> settingsButtons;

    //Menu options
    private boolean showEnemyPath, showPositions;

    private boolean gamePaused;

    public UserInterface(Screen screen, Player player, AiManager ai){
        this.screen = screen;
        this.player = player;
        this.enemies = ai.getEnemies();
        this.ai = ai;
        gamePaused = false;
        init();
    }

    public void init(){

        currentManuLevel = MenuLevel.PAUSE;
        showEnemyPath = true;
        showPositions = true;

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
        menuButton = new UIButton(screen,  10, 10, 30, 20, "ESC", true);
        menuButton.setColour(0xaa0000, 0xffff00);

        //========================================================================================
        //setup all menu buttons
        //========================================================================================
        pauseButtons = setupMenuButtons(pauseButtonNames);
        settingsButtons = setupMenuButtons(settingsButtonNames);

        enemyHealthBars = setupEnemyHealthBars();
    }

    public void tick(){
        healthBar.setCurrentBarPercent(player.getMaxLife(),player.getCurrentLife());
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());
        experienceBar.setCurrentBarPercentage(player.getMaxExperience(), player.getCurrentExperience());
        abilityBar.setCurrentBarPercentage(100, 50);
        for(int i = 0; i < enemies.size(); i++){
            enemyHealthBars.get(i).setPos((int)enemies.get(i).getX() - 3, (int)enemies.get(i).getY() - 8);
            enemyHealthBars.get(i).setCurrentBarPercentage(enemies.get(i).getMaxLife(), enemies.get(i).getLife());
        }
    }

    public void render() {
        if(!gamePaused){
            healthBar.render();
            manaBar.render();
            experienceBar.render();
            abilityBar.render();
            menuButton.render();

            showPlayerPositions();
            showEnemyPaths();
            showEnemyHealthBars();
        }else{
            switch (currentManuLevel){
                case PAUSE:
                    for (UIButton b : pauseButtons){
                        b.render();
                    }
                    break;
                case SETTINGS:
                    for (UIButton b : settingsButtons){
                        b.render();
                    }
                    break;
            }
        }
    }

    public int checkInputEvent(int x, int y){
        //return 0 means no buttons pressed
        //return 1 means a button was pressed but no action by InputHandler necessary
        //return -1 means game was paused

        if (menuButton.isPressed(x, y)){
            setGamePaused(true);
            return -1;
        }

        if(currentManuLevel == MenuLevel.PAUSE) {
            for (UIButton b : pauseButtons) {
                if (b.isPressed(x, y)) {
                    if (b.getLabel().equals("Settings")) {
                        currentManuLevel = MenuLevel.SETTINGS;
                    } else if (b.getLabel().equals("Back")) {
                        setGamePaused(false);
                        return -1;
                    } else if (b.getLabel().equals("Quit")) {
                        System.out.println("INFO: User has exited program!");
                        System.exit(0);
                    }
                }
            }
        }else if(currentManuLevel == MenuLevel.SETTINGS) {
            for (UIButton b : settingsButtons) {
                if (b.isPressed(x, y)) {
                    if (b.getLabel().equals("Back")) {
                        currentManuLevel = MenuLevel.PAUSE;
                    }else if(b.getLabel().equals("Draw Paths")){
                        showEnemyPath = !showEnemyPath;
                        setGamePaused(false);
                        return -1;
                    }else if(b.getLabel().equals("Draw Positions")){
                        showPositions = !showPositions;
                        setGamePaused(false);
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    private ArrayList<UIButton> setupMenuButtons(String[] buttonNames){
        int width, height;
        boolean clickable;
        ArrayList<UIButton> buttons = new ArrayList<>();
        for(int i = 0; i < buttonNames.length; i++){
            width = screen.getWidth() / 5;
            height = screen.getHeight() /20;
            clickable = true;
            if(i == 0){
                width = screen.getWidth() / 3;
                height = screen.getHeight() / 17;
                clickable = false;
            }
            int xpos = (screen.getWidth()/2) - (width/2);
            int ypos = screen.getHeight()/2 + ((height+5)*i) - ((buttonNames.length*20)/2 + height);
            buttons.add(new UIButton(screen, xpos, ypos, width, height, buttonNames[i], clickable));
            buttons.get(i).setTextSize(2);
            buttons.get(i).setColour(0xaa0000, 0xffff00);
        }
        return buttons;
    }

    private ArrayList<RectangleProgressBar> setupEnemyHealthBars(){
        ArrayList<RectangleProgressBar> enemybars = new ArrayList<>();
        for(int i = 0; i < enemies.size(); i++) {
            enemybars.add(new RectangleProgressBar(screen, (int)enemies.get(i).getX(), (int)enemies.get(i).getY(), 20, 6));
            enemybars.get(i).setFixed(true);
            enemybars.get(i).setBarColours(0x000055, 0xffffff);
        }
        return enemybars;
    }

    private void showPlayerPositions(){
        if(!showPositions) return;

        screen.renderString(screen.getWidth() - 116, 0, "P1: " + (int)(player.getX()/8) + " " + (int)(player.getY()/8), false, 0x0000aa, 1, false);
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
            screen.renderString(x, yp, s, false, 0xaa0000, 1, false);
        }
    }

    private void showEnemyPaths(){
        if (!showEnemyPath) return;
        for (int i = 0; i < enemies.size(); i++) {
            ArrayList<Point> path = ai.getAIPath(i);
            screen.renderConnectedLine(path, 8, 8, 0xaa0000, true);
        }
    }

    private void showEnemyHealthBars(){
        for(int i = 0; i < enemies.size(); i++){
            enemyHealthBars.get(i).render();
        }
    }

    public void setGamePaused(boolean paused){
        if (!paused) currentManuLevel = MenuLevel.NONE;
        if(this.gamePaused != paused){
            currentManuLevel = MenuLevel.PAUSE;
        }
        this.gamePaused = paused;
    }

    public boolean isGamePaused(){
        return this.gamePaused;
    }
}
