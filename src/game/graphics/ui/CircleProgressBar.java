package game.graphics.ui;

import game.graphics.Screen;

public class CircleProgressBar {

    private Screen screen;
    private int currentBarPercent;
    private int barFillColour, barBorderColour;
    private int screenPosX, screenPosY;
    private int sizeRadius;
    private String label;

    public CircleProgressBar(Screen screen, int xPos, int yPos, int size, String label) {
        this.screen = screen;
        this.screenPosX = xPos;
        this.screenPosY = yPos;
        this.sizeRadius = size;
        this.label = label;

        currentBarPercent = 100;
        barFillColour = 0x000000;
        barBorderColour = 0x000000;
    }

    public void render() {
        this.screen.renderString(screenPosX, screenPosY - sizeRadius - 16, label, true, 0x660000, false);
        this.screen.renderCircle(screenPosX, screenPosY, sizeRadius, currentBarPercent, barFillColour, barBorderColour, true, false);
    }

    public void setCurrentBarPercent(int max, int current) {
        this.currentBarPercent = (100 * current) / max;
    }

    public void setBarColours(int barFillColour, int barBorderColour) {
        this.barFillColour = barFillColour;
        this.barBorderColour = barBorderColour;
    }
}
