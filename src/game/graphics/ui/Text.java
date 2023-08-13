package game.graphics.ui;

import java.awt.geom.Point2D;

public class Text {
    private String text;
    private Point2D.Float position;
    private int colour;
    private boolean fixed;

    public Text(String text, Point2D.Float position, int colour, boolean fixed) {
        this.text = text;
        this.position = position;
        this.colour = colour;
        this.fixed = fixed;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Point2D.Float getPosition() {
        return this.position;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
    }

    public int getColour() {
        return this.colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public boolean getFixed() {
        return this.fixed;
    }
}
