package game.graphics.ui;

import game.graphics.FrameState;
import game.graphics.Screen;

import java.awt.geom.Point2D;

public class UIButton {
    private Screen screen;
    private int screenX, screenY;
    private int buttonWidth, buttonHeight;
    private int fillColour, borderColour, clickedFillColour;
    private String label;
    private boolean clickable;
    private int textSize;
    private long pressedTime;
    private Text text;


    public UIButton(Screen screen, int xpos, int ypos, int width, int height, String label, boolean clickable) {
        this.screen = screen;
        this.screenX = xpos;
        this.screenY = ypos;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.label = label;
        this.clickable = clickable;
        this.textSize = 1;
        this.text = new Text(
                this.label,
                new Point2D.Float(screenX + buttonWidth / 2, screenY + (buttonHeight / 2) - (buttonHeight / 4)),
                0xaaaa00,
                false
        );

        fillColour = 0xffffff;
        borderColour = 0x000000;
        clickedFillColour = 0x000000;

        pressedTime = 0;
    }

    public void render() {
        int changeColour = fillColour;
        if (System.currentTimeMillis() - pressedTime < 100) {
            changeColour = clickedFillColour;
        }

        this.screen.renderRectangle(screenX, screenY, buttonWidth, buttonHeight, 100, changeColour, borderColour, false);
        this.screen.renderString(text);
    }

    public boolean isPressed(int x, int y) {
        if (clickable) {
            int xp = x / FrameState.getScale();
            int yp = y / FrameState.getScale();

            if (xp < screenX || xp > screenX + buttonWidth) return false;
            if (yp < screenY || yp > screenY + buttonHeight) return false;

            pressedTime = System.currentTimeMillis();
        } else {
            return false;
        }
        return true;
    }

    public void setColour(int fillColour, int borderColour) {
        this.fillColour = fillColour;
        this.borderColour = borderColour;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setClickedFillColour(int clickedFillColour) {
        this.clickedFillColour = clickedFillColour;
    }

    public String getLabel() {
        return label;
    }
}
