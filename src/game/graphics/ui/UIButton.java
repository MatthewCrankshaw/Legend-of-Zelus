package game.graphics.ui;

import game.graphics.Screen;

public class UIButton {
    private Screen screen;
    private int screenX, screenY;
    private int buttonWidth, buttonHeight;
    private int fillColour, borderColour;
    private String label;

    public UIButton(Screen screen, int xpos, int ypos, int width, int height, String label){
        this.screen = screen;
        this.screenX = xpos;
        this.screenY = ypos;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.label = label;

        fillColour = 0xffffff;
        borderColour = 0x000000;
    }

    public void render(){
        this.screen.renderRectangle(screenX, screenY, buttonWidth, buttonHeight,100, fillColour, borderColour,false);
        this.screen.renderString(screenX+buttonWidth/2, screenY + (buttonHeight/2) - (buttonHeight/4), label, true, 0xaaaa00);
    }

    public boolean isPressed(int x, int y){
        System.out.println("x: " + x + " y " + y);
        if(x*2 < screenX || x*2 > screenX + buttonWidth) return false;
        if(y*2 < screenY || y*2 > screenY + buttonHeight) return false;
        return true;
    }

    public void setColour(int fillColour, int borderColour){
        this.fillColour = fillColour;
        this.borderColour = borderColour;
    }
}
