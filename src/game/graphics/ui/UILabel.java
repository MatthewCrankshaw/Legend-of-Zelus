package game.graphics.ui;

import game.graphics.Screen;

import java.awt.geom.Point2D;

public class UILabel {
    private Screen screen;
    private long lifeSpan; // -1 for forever (in millisecs)
    private long timeInstantiated, currentDuration;
    private boolean alive;
    private Text text;

    public UILabel(Screen screen, int x, int y, String text, boolean fixed, long lifeSpan) {
        this.screen = screen;
        this.lifeSpan = lifeSpan;
        this.alive = true;
        this.timeInstantiated = System.currentTimeMillis();
        this.text = new Text(text, new Point2D.Float(x, y), 0xFF000000, fixed);
    }

    public void tick() {
        currentDuration = System.currentTimeMillis() - timeInstantiated;
        if (lifeSpan != -1 && currentDuration > lifeSpan) {
            alive = false;
        }
    }

    public void render() {
        screen.renderString(text);
    }

    public void setColour(int colour) {
        this.text.setColour(colour);
    }

    public boolean isAlive() {
        return alive;
    }

}
