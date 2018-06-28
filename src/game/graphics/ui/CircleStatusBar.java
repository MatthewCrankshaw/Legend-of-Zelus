package game.graphics.ui;
import game.graphics.Screen;

public class CircleStatusBar{

    private Screen screen;
    private int currentBarPercent;
    private int barFillColour, barBorderColour;
    private int screenPosX, screenPosY;
    private int sizeRadius;

    public CircleStatusBar(Screen screen, int xPos, int yPos, int size){
        this.screen = screen;
        screenPosX = xPos;
        screenPosY = yPos;
        sizeRadius = size;

        currentBarPercent = 100;
        barFillColour = 0x000000;
        barBorderColour = 0x000000;
    }

    public void render(){
        screen.renderCircle(screenPosX, screenPosY, sizeRadius, currentBarPercent, barFillColour, barBorderColour, true, false);
    }

    public void setCurrentBarPercent(int max, int current){
        currentBarPercent = (100 * current) / max;
    }

    public void setBarFillColour(int colour){
        this.barFillColour = colour;
    }

    public void setBarBorderColour(int colour){
        this.barBorderColour = colour;
    }
}