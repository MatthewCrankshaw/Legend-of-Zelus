package game.graphics.ui;

import game.graphics.Screen;

public class UIButton {
    private Screen screen;
    private int screenX, screenY;
    private int buttonWidth, buttonHeight;
    private int fillColour, borderColour, clickedFillColour;
    private String label;

    private long pressedTime;

    public UIButton(Screen screen, int xpos, int ypos, int width, int height, String label){
        this.screen = screen;
        this.screenX = xpos;
        this.screenY = ypos;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.label = label;

        fillColour = 0xffffff;
        borderColour = 0x000000;
        clickedFillColour = 0x000000;

        pressedTime = 0;
    }

    public void render(){
        int changeColour = fillColour;
        if (System.currentTimeMillis() - pressedTime < 100){
            changeColour = clickedFillColour;
        }

        this.screen.renderRectangle(screenX, screenY, buttonWidth, buttonHeight,100, changeColour, borderColour,false);
        this.screen.renderString(screenX+buttonWidth/2, screenY + (buttonHeight/2) - (buttonHeight/4), label, true, 0xaaaa00);
    }

    public boolean isPressed(int x, int y){
        int xp = x/screen.getScale();
        int yp = y/screen.getScale();

        if(xp < screenX || xp > screenX + buttonWidth) return false;
        if(yp < screenY || yp > screenY + buttonHeight) return false;

        pressedTime = System.currentTimeMillis();
        return true;
    }

    public void setColour(int fillColour, int borderColour){
        this.fillColour = fillColour;
        this.borderColour = borderColour;
    }

    public void setClickedFillColour(int clickedFillColour) {
        this.clickedFillColour = clickedFillColour;
    }
}
