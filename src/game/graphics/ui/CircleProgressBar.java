package game.graphics.ui;

import game.graphics.Screen;

import java.awt.geom.Point2D;

public class CircleProgressBar {
    private Screen screen;
    private Circle circle;
    private int sizeRadius;
    private Text text;

    public CircleProgressBar(Screen screen, int xPos, int yPos, int size, String label) {
        this.screen = screen;
        this.sizeRadius = size;
        text = new Text(label, new Point2D.Float(xPos, yPos - sizeRadius - 16), 0x660000, false);
        circle = new Circle(xPos, yPos, size);
    }

    public void render() {
        this.screen.renderString(text);
        this.screen.renderCircle(circle, false);
    }

    public void setCurrentBarPercent(int max, int current) {
        this.circle.setCurrentBarPercent(max, current);
    }
}
