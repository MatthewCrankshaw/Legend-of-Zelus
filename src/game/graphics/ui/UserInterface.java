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

    public enum MenuLevel{NONE ,PAUSE, SETTINGS, VIDEO}

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
    private final String[] settingsButtonNames = {"Settings", "Video", "Draw Positions", "Draw Paths", "Back"};
    private final String[] videoButtonNames = {"Video", "AA Filter", "Median Blur Filter", "Smoothing Filter", "Back"};

    private ArrayList<UIButton> pauseButtons;
    private ArrayList<UIButton> settingsButtons;
    private ArrayList<UIButton> videoButtons;

    private ArrayList<UILabel> labels;

    //Menu options
    private boolean showEnemyPath, showPositions;

    private boolean gamePaused;

    public UserInterface(Screen screen, Player player){
        this.screen = screen;
        this.player = player;
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
        videoButtons = setupMenuButtons(videoButtonNames);

        labels = new ArrayList<>();
    }

    public void setAiManager(AiManager ai){
        this.enemies = ai.getEnemies();
        this.ai = ai;
        enemyHealthBars = setupEnemyHealthBars();
    }

    public void tick(){
        healthBar.setCurrentBarPercent(player.getMaxLife(),player.getCurrentLife());
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());
        experienceBar.setCurrentBarPercentage(player.getMaxExperience(), player.getCurrentExperience());
        abilityBar.setCurrentBarPercentage(100, 50);

        enemies = ai.getEnemies();

        //If a new enemy has been created then create a new health bar for it.
        if(enemyHealthBars.size() < enemies.size()){
            enemyHealthBars.add(new RectangleProgressBar(screen, (int)enemies.get(enemies.size() - 1).getX() - 3, (int)enemies.get(enemies.size() - 1).getY() - 8, 20, 6));
            enemyHealthBars.get(enemyHealthBars.size()-1).setFixed(true);
            enemyHealthBars.get(enemyHealthBars.size()-1).setBarColours(0x00ff00, 0xffffff);
        }

        for(int i = 0; i < enemies.size(); i++) {
            enemyHealthBars.get(i).setPos((int) enemies.get(i).getX() - 3, (int) enemies.get(i).getY() - 8);
            enemyHealthBars.get(i).setCurrentBarPercentage(enemies.get(i).getMaxLife(), enemies.get(i).getLife());
        }

        checkAliveUiLabels();
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
                case VIDEO:
                    for(UIButton b: videoButtons){
                        b.render();
                    }
            }
        }

        for(UILabel label : labels) {
            label.render();
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
                    switch (b.getLabel()) {
                        case "Settings":
                            currentManuLevel = MenuLevel.SETTINGS;
                            break;
                        case "Back":
                            setGamePaused(false);
                            return -1;
                        case "Quit":
                            System.out.println("INFO: User has exited program!");
                            System.exit(0);
                    }
                }
            }
        }else if(currentManuLevel == MenuLevel.SETTINGS) {
            for (UIButton b : settingsButtons) {
                if (b.isPressed(x, y)) {
                    switch (b.getLabel()) {
                        case "Back":
                            currentManuLevel = MenuLevel.PAUSE;
                            break;
                        case "Video":
                            currentManuLevel = MenuLevel.VIDEO;
                            break;
                        case "Draw Paths":
                            showEnemyPath = !showEnemyPath;
                            setGamePaused(false);
                            return -1;
                        case "Draw Positions":
                            showPositions = !showPositions;
                            setGamePaused(false);
                            return -1;
                    }
                }
            }
        }else if(currentManuLevel == MenuLevel.VIDEO){
            for(UIButton b : videoButtons){
                if(b.isPressed(x, y)){
                    switch (b.getLabel()) {
                        case "Back":
                            currentManuLevel = MenuLevel.SETTINGS;
                            break;
                        case "AA Filter":
                            screen.switchAAFilterEnabled();
                            setGamePaused(false);
                            return -1;
                        case "Median Blur Filter":
                            screen.switchMedianBlurEnabled();
                            setGamePaused(false);
                            return -1;
                        case "Smoothing Filter":
                            screen.switchSmoothingFilterEnabled();
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
            width = screen.getWidth() / 3;
            height = screen.getHeight() /15;
            clickable = true;
            if(i == 0){
                width = screen.getWidth() / 2;
                height = screen.getHeight() / 15;
                clickable = false;
            }
            int xpos = (screen.getWidth()/2) - (width/2);
            int ypos = screen.getHeight()/2 + ((height+5)*i) - ((buttonNames.length*20)/2 + height);
            buttons.add(new UIButton(screen, xpos, ypos, width, height, buttonNames[i], clickable));
            buttons.get(i).setColour(0xaa0000, 0xffff00);
        }
        return buttons;
    }

    private ArrayList<RectangleProgressBar> setupEnemyHealthBars(){
        ArrayList<RectangleProgressBar> enemybars = new ArrayList<>();
        for(int i = 0; i < enemies.size(); i++) {
            enemybars.add(new RectangleProgressBar(screen, (int)enemies.get(i).getX(), (int)enemies.get(i).getY(), 20, 6));
            enemybars.get(i).setFixed(true);
            enemybars.get(i).setBarColours(0x00ff00, 0xffffff);
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


    // ===========================================================
    // Label Management Code
    // ===========================================================

    private void checkAliveUiLabels(){
        ArrayList<UILabel> delLabels = new ArrayList<>();
        for(int i = 0; i < labels.size(); i++) {
            UILabel l = labels.get(i);
            l.tick();
            if(!l.isAlive()){
                delLabels.add(l);
            }
        }

        for(int i = 0; i < delLabels.size(); i++){
            labels.remove(delLabels.get(i));
        }
    }

    public void addLabel(Screen screen, int x, int y, String text, boolean fixed, boolean center, long lifeSpan, int colour){
        UILabel newLabel = new UILabel(screen, x, y, text, fixed, center, lifeSpan);
        newLabel.setColour(colour);
        labels.add(newLabel);
    }

    private void showEnemyPaths(){
        if (!showEnemyPath) return;
        for (int i = 0; i < enemies.size(); i++) {
            ArrayList<Point> path = ai.getAIPath(i);
            if(path == null) continue;
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
