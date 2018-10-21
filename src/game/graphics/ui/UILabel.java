package game.graphics.ui;

import game.graphics.Screen;

public class UILabel {
    private Screen screen;
    private int screenX, screenY;
    private String text;
    private int colour;
    private boolean fixed, center;

    private long lifeSpan; // -1 for forever (in millisecs)
    private long timeInstantiated,currentDuration;
    private boolean alive;

    public UILabel(Screen screen, int x, int y, String text, boolean fixed, boolean center, long lifeSpan){
        this.screen = screen;
        this.screenX = x;
        this.screenY = y;
        this.text = text;
        this.fixed = fixed;
        this.center = center;
        this.colour = 0xFF000000;
        this.lifeSpan = lifeSpan;
        this.alive = true;
        this.timeInstantiated = System.currentTimeMillis();
    }

    public void tick(){
        currentDuration = System.currentTimeMillis() - timeInstantiated;
        if(lifeSpan != -1 && currentDuration > lifeSpan) {
            alive = false;
        }
    }

    public void render() {
        screen.renderString(screenX, screenY, text, center, colour, 1, fixed);
    }

    public void setColour(int colour){
        this.colour = colour;
    }

    public boolean isAlive(){
        return alive;
    }

}
