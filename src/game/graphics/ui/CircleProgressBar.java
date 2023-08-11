package game.graphics.ui;

import game.graphics.Screen;

public class CircleProgressBar {
    private Screen screen;
    private Circle circle;
    private int screenPosX, screenPosY;
    private int sizeRadius;
    private String label;

    public CircleProgressBar(Screen screen, int xPos, int yPos, int size, String label) {
        this.screen = screen;
        this.screenPosX = xPos;
        this.screenPosY = yPos;
        this.sizeRadius = size;
        this.label = label;
        circle = new Circle(xPos, yPos, size);
    }

    public void render() {
        this.screen.renderString(screenPosX, screenPosY - sizeRadius - 16, label, true, 0x660000, false);
        this.screen.renderCircle(circle, false);
    }

    public void setCurrentBarPercent(int max, int current) {
        this.circle.setCurrentBarPercent(max, current);
    }
}
