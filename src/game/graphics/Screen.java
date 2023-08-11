package game.graphics;

import game.Renderer;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.levels.tile.Tile;
import game.levels.tile.animated_tiles.AnimatedTile;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Screen extends Canvas {
    private static final String NAME = "Never Lost - Matthew Crankshaw - 14303742";
    protected SpriteRegistry spriteRegistry;
    protected Renderer renderer;
    protected BufferStrategy strategy;
    private Dimension2D screenSize;
    private boolean AAFilterEnabled, MedianBlurEnabled, SmoothingFilterEnabled;

    public Screen(Dimension2D screenSize, SpriteRegistry spriteRegistry, Renderer renderer) {
        this.screenSize = screenSize;

        setMinimumSize((Dimension) screenSize);
        setMaximumSize((Dimension) screenSize);
        setPreferredSize((Dimension) screenSize);

        JFrame frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.spriteRegistry = spriteRegistry;
        this.renderer = renderer;

        this.AAFilterEnabled = false;
        this.MedianBlurEnabled = false;
        this.SmoothingFilterEnabled = false;
    }

    public void clear() {
        this.renderer.clear();
    }

    public void renderSprite(Point2D position, Sprite sprite, boolean fixed, int colour, int spriteScale) {
        this.renderer.renderSprite(position, getFrameSize(), sprite, fixed, colour, spriteScale);
    }

    public void renderString(int xp, int yp, String string, boolean center, int colour, boolean fixed) {
        this.renderer.renderString(getFrameSize(), xp, yp, string, center, colour, fixed);
    }

    public void renderTile(Point2D position, Tile tile) {
        this.renderer.renderTile(getFrameSize(), position, tile);
    }

    public void renderAnimatedTile(int xp, int yp, AnimatedTile animTile) {
        int width = (int) this.getFrameSize().getWidth();
        int height = (int) this.getFrameSize().getHeight();
        this.renderer.renderAnimatedTile(xp, yp, animTile, width, height);
    }

    public void renderLine(double xp1, double yp1, double xp2, double yp2, int colour, boolean fixed) {
        int width = (int) this.getFrameSize().getWidth();
        int height = (int) this.getFrameSize().getHeight();
        this.renderer.renderLine(xp1, yp1, xp2, yp2, width, height, colour, fixed);
    }

    public void renderConnectedLine(ArrayList<Point> points, int xOffset, int yOffset, int colour, boolean fixed) {
        int width = (int) this.getFrameSize().getWidth();
        int height = (int) this.getFrameSize().getHeight();
        this.renderer.renderConnectedLine(points, xOffset, yOffset, width, height, colour, fixed);
    }

    public void renderRectangle(int xp, int yp, int width, int height, int currentPercent, int colourFill, int colourBorder, boolean fixed) {
        int frameWidth = (int) this.getFrameSize().getWidth();
        int frameHeight = (int) this.getFrameSize().getHeight();
        this.renderer.renderRectangle(xp, yp, width, height, frameWidth, frameHeight, currentPercent, colourFill, colourBorder, fixed);
    }

    public void renderCircle(int xp, int yp, int radius, int fill, int colour, int borderColour, boolean filled, boolean fixed) {
        int frameWidth = (int) this.getFrameSize().getWidth();
        int frameHeight = (int) this.getFrameSize().getHeight();
        this.renderer.renderCircle(xp, yp, frameWidth, frameHeight, radius, fill, colour, borderColour, filled, fixed);
    }

    public void renderPlayer(int xp, int yp, int pixelsLong, int pixelshigh, int scale, Sprite sprite) {
        this.renderer.renderPlayer(xp, yp, this.getWidth(), this.getHeight(), pixelsLong, pixelshigh, scale, sprite);
    }

    public void setOffset(float x, float y) {
        this.renderer.setOffset(x, y);
    }


    //===================================================================
    // Filters
    //===================================================================

    protected int getPixel(int i) {
        int width = (int) this.getFrameSize().getWidth();
        int height = (int) this.getFrameSize().getHeight();
        if (SmoothingFilterEnabled) {
            this.renderer.applyPixelSmoothingFilter(i, width, height);
        }
        if (MedianBlurEnabled) {
            this.renderer.applyPixelsMedianBlur(i, width, height);
        }
        if (AAFilterEnabled) {
            this.renderer.applyPixelSimpleAAFilter(i, width, height);
        }
        return this.renderer.getPixel(i);
    }

    public void switchAAFilterEnabled() {
        if (AAFilterEnabled) {
            AAFilterEnabled = false;
        } else {
            AAFilterEnabled = true;
        }
    }

    public void switchMedianBlurEnabled() {
        if (MedianBlurEnabled) {
            MedianBlurEnabled = false;
        } else {
            MedianBlurEnabled = true;
        }
    }

    public void switchSmoothingFilterEnabled() {
        if (SmoothingFilterEnabled) {
            SmoothingFilterEnabled = false;
        } else {
            SmoothingFilterEnabled = true;
        }
    }

    public void setSmoothingFilterEnabled(boolean smoothingFilterEnabled) {
        SmoothingFilterEnabled = smoothingFilterEnabled;
    }

    public Dimension2D getScreenSize() {
        return this.screenSize;
    }

    public void bufferStrategy() {
        strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(3);
            strategy = getBufferStrategy();
            return;
        }
    }

    public void draw() {
        Graphics g = strategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.drawImage(this.renderer.getImage(), 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
        strategy.show();
    }

    protected Dimension2D getFrameSize() {
        return new Dimension(
                this.renderer.getImage().getWidth(),
                this.renderer.getImage().getHeight()
        );
    }

    public int getScale() {
        return this.renderer.getScale();
    }
}
