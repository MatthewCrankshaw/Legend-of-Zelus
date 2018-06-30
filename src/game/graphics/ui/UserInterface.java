package game.graphics.ui;

import game.Game;
import game.entities.mob.Player;
import game.graphics.Screen;

/**
 * Created by Matthew.c on 02/03/2017.
 */
public class UserInterface {

    protected Screen screen;
    protected Player player;

    private CircleProgressBar healthBar, manaBar;
    private RectangleProgressBar experienceBar;
    private RectangleProgressBar abilityBar;

    private boolean gamePaused;

    public UserInterface(Screen screen, Player player){
        this.screen = screen;
        this.player = player;
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
    }

    public void setGamePaused(boolean paused){
        this.gamePaused = paused;
    }
}
