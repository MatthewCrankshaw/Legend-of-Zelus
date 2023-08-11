package game.graphics.ui;

public class Circle {
    private int currentBarPercent, barFillColour, barBorderColour;
    private int screenPosX, screenPosY;
    private int sizeRadius;

    public Circle(int xPos, int yPos, int size) {
        this.screenPosX = xPos;
        this.screenPosY = yPos;
        this.sizeRadius = size;

        currentBarPercent = 100;
        barFillColour = 0xff0000;
        barBorderColour = 0xffffff;
    }

    public void setCurrentBarPercent(int max, int current) {
        this.currentBarPercent = (100 * current) / max;
    }

    public int getScreenPosX() {
        return this.screenPosX;
    }

    public int getScreenPosY() {
        return this.screenPosY;
    }

    public int getSizeRadius() {
        return this.sizeRadius;
    }

    public int getBarFillColour() {
        return this.barFillColour;
    }

    public int getCurrentBarPercent() {
        return this.currentBarPercent;
    }

    public int getBarBorderColour() {
        return this.barBorderColour;
    }
}
