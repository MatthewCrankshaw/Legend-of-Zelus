package game.graphics.ui;

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

    public UserInterface(Screen screen, Player player){
        this.screen = screen;
        this.player = player;
        init();
    }

    public void init(){
        //setup health bar
        healthBar = new CircleProgressBar(screen, 60, screen.height - 60, 50, "Life");
        healthBar.setBarFillColour(0xff0000);
        healthBar.setCurrentBarPercent(player.getMaxLife(), player.getCurrentLife());

        //setup mana bar
        manaBar = new CircleProgressBar(screen,screen.width - 60, screen.height - 60, 50, "Mana");
        manaBar.setBarFillColour(0x0000ff);
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());

        //setup experience bar
        experienceBar = new RectangleProgressBar(screen, 120, screen.height - 20, screen.width - 240, 10, "Experience");
        experienceBar.setBarFillColour(0x444444);
        experienceBar.setBarBorderColour(0x990099);
    }

    public void tick(){
        healthBar.setCurrentBarPercent(player.getMaxLife(),player.getCurrentLife());
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());
        experienceBar.setCurrentBarPercentage(player.getMaxExperience(), player.getCurrentExperience());
    }

    public void render(){
        healthBar.render();
        manaBar.render();
        experienceBar.render();
    }

}
