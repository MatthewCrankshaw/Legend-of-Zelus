package game.graphics.ui;

import game.Game;
import game.entities.mob.Enemy;
import game.entities.mob.Player;
import game.graphics.Screen;

import java.util.ArrayList;

/**
 * Created by Matthew.c on 02/03/2017.
 */
public class UserInterface {

    protected Screen screen;
    protected Player player;
    protected ArrayList<Enemy> enemies;

    private CircleProgressBar healthBar, manaBar;
    private RectangleProgressBar experienceBar;
    private RectangleProgressBar abilityBar;

    private boolean gamePaused;

    public UserInterface(Screen screen, Player player, ArrayList<Enemy> enemies){
        this.screen = screen;
        this.player = player;
        this.enemies = enemies;
        gamePaused = false;
        init();
    }

    public void init(){
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

    public void render(){
        healthBar.render();
        manaBar.render();
        experienceBar.render();
        abilityBar.render();
        if(gamePaused){
            screen.renderString((Game.WIDTH/2), (Game.HEIGHT/2) - (Game.HEIGHT/4), "--- Paused ---", true,0x660000);
            screen.renderString((Game.WIDTH/2)- (5*8), (Game.HEIGHT/2) - (Game.HEIGHT/4) + 16, "1. Quit", false,0x660000);
            screen.renderString((Game.WIDTH/2)- (5*8), (Game.HEIGHT/2) - (Game.HEIGHT/4) + 24, "2. Settings", false,0x660000);
        }
        screen.renderString(0, 0, "P1: " + (int)(player.getX()/8) + " " + (int)(player.getY()/8), false, 0x000000);
        for (int i = 0; i < enemies.size(); i++){
            int x = 0;
            int y = 0;
            if (i >= 40){
                x += 8;
                y = 0;
            }
            String s = "E"+(i+1)+ ": " + (int)(enemies.get(i).getX()/8) + " " + (int)(enemies.get(i).getY()/2);
            screen.renderString(x * (14), y + (8*((i%40)+1)), s, false, 0x000000);
        }
    }

    public void setGamePaused(boolean paused){
        this.gamePaused = paused;
    }
}
