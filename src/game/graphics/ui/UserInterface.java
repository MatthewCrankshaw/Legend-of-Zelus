package game.graphics.ui;

import game.entities.mob.Player;
import game.graphics.Screen;

/**
 * Created by Matthew.c on 02/03/2017.
 */
public class UserInterface {

    protected Screen screen;
    protected Player player;

    private CircleStatusBar healthBar, manaBar;

    public UserInterface(Screen screen, Player player){
        this.screen = screen;
        this.player = player;
        init();
    }

    public void init(){
        //setup health bar
        healthBar = new CircleStatusBar(screen, 60, screen.height - 60, 50);
        healthBar.setBarFillColour(0xff0000);
        healthBar.setCurrentBarPercent(player.getMaxLife(), player.getCurrentLife());

        //setup mana bar
        manaBar = new CircleStatusBar(screen,screen.width - 60, screen.height - 60, 50);
        manaBar.setBarFillColour(0x0000ff);
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());
    }

    public void tick(){
        healthBar.setCurrentBarPercent(player.getMaxLife(),player.getCurrentLife());
        manaBar.setCurrentBarPercent(player.getMaxMana(), player.getCurrentMana());
    }

    public void render(){
        healthBar.render();
        manaBar.render();
    }

}
