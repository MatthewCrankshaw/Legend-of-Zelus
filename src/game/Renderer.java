package game;

import game.graphics.CircleRenderer;
import game.graphics.FrameState;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.graphics.sprite.SpriteRenderer;
import game.graphics.ui.Circle;
import game.levels.tile.Tile;
import game.levels.tile.animated_tiles.AnimatedTile;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Renderer {
    protected CircleRenderer circleRenderer;
    protected SpriteRenderer spriteRenderer;
    protected SpriteRegistry spriteRegistry;
    private BufferedImage image;

    public Renderer(CircleRenderer circleRenderer, SpriteRenderer renderer, SpriteRegistry registry, BufferedImage image) {
        this.circleRenderer = circleRenderer;
        this.spriteRenderer = renderer;
        this.spriteRegistry = registry;
        this.image = image;
    }

    public void renderSprite(Point2D position, Sprite sprite, boolean fixed, int colour, int spriteScale) {
        this.spriteRenderer.render(sprite, position, fixed, colour, spriteScale);
    }

    public void renderTile(Point2D position, Tile tile) {
        this.spriteRenderer.render(tile.getCurrentSprite(), position, true, -1, 1);
        tile.tick();
    }

    public void renderAnimatedTile(int xp, int yp, AnimatedTile animTile) {

        Sprite sprite = animTile.getCurrentSprite();
        xp -= (int) FrameState.getOffset().getX();
        yp -= (int) FrameState.getOffset().getY();
        for (int y = 0; y < 16; y++) {
            int ya = y + yp;
            for (int x = 0; x < 16; x++) {
                int xa = x + xp;
                if (xa < -16 || xa >= FrameState.getFrameSize().getWidth() || ya < 0 || ya >= FrameState.getFrameSize().getHeight())
                    break;
                if (xa < 0) xa = 0;
                int col = sprite.getPixel(x, y);
                if (col != 0xffff00ff) {
                    FrameState.setPixel(xa + ya * (int) FrameState.getFrameSize().getWidth(), sprite.getPixel(x, y));
                }
            }
        }
    }

    public void renderLine(double xp1, double yp1, double xp2, double yp2, int colour, boolean fixed) {

        if (fixed) {
            xp1 -= FrameState.getOffset().getX();
            yp1 -= FrameState.getOffset().getY();
            xp2 -= FrameState.getOffset().getX();
            yp2 -= FrameState.getOffset().getY();
        }

        double x = Math.abs(xp2 - xp1);
        double y = Math.abs(yp2 - yp1);

        double max = Math.max(x, y);

        //Inside try catch block because there is a possibility to have divide by zero
        try {
            x /= max;
            y /= max;
        } catch (ArithmeticException e) {
            //If divide by zero then just return
            //We don't need to draw the line
            return;
        }

        for (int i = 0; i < max; i++) {
            if (xp1 < 0 || xp1 >= FrameState.getFrameSize().getWidth() || yp1 < 0 || yp1 >= FrameState.getFrameSize().getHeight())
                break;
            if (xp2 < 0 || xp2 >= FrameState.getFrameSize().getWidth() || yp2 < 0 || yp2 >= FrameState.getFrameSize().getHeight())
                break;
            FrameState.setPixel((int) xp1 + (int) (FrameState.getFrameSize().getWidth() * (int) yp1), colour);
            if (xp1 < xp2) {
                xp1 += x;
            } else {
                xp1 -= x;
            }
            if (yp1 < yp2) {
                yp1 += y;
            } else {
                yp1 -= y;
            }
        }
    }

    public void renderConnectedLine(ArrayList<Point> points, int xOffset, int yOffset, int colour, boolean fixed) {
        for (int i = 0; i < points.size() - 1; i++) {
            renderLine(
                    points.get(i).x + xOffset,
                    points.get(i).y + yOffset,
                    points.get(i + 1).x + xOffset,
                    points.get(i + 1).y + yOffset,
                    colour,
                    fixed
            );
        }
    }

    public void renderRectangle(int xp, int yp, int width, int height, int currentPercent, int colourFill, int colourBorder, boolean fixed) {
        if (fixed) {
            xp -= (int) FrameState.getOffset().getX();
            yp -= (int) FrameState.getOffset().getY();
        }

        int screenWidth = (int) FrameState.getFrameSize().getWidth();
        int screenHeight = (int) FrameState.getFrameSize().getHeight();

        for (int y = yp; y <= yp + height; y++) {
            for (int x = xp; x <= (xp + width); x++) {
                if (x >= screenWidth || y >= screenHeight || x < 0 || y < 0) continue;
                if (y == yp || y == yp + height || x == xp || x == xp + width) {
                    FrameState.setPixel(x + (screenWidth * y), colourBorder);
                } else {
                    if (x <= (currentPercent * 0.01 * width) + xp) {
                        FrameState.setPixel(x + (screenWidth * y), colourFill);
                    }
                }
            }
        }
    }

    public void renderCircle(Circle circle, boolean fixed) {
        circleRenderer.render(circle, fixed);
    }

    public void renderPlayer(int xp, int yp, int width, int height, int pixelsLong, int pixelshigh, int scale, Sprite sprite) {
        xp -= (int) FrameState.getOffset().getX();
        yp -= (int) FrameState.getOffset().getY();
        for (int y = 0; y < pixelshigh * scale; y++) {
            int ya = y + yp;
            for (int x = 0; x < pixelsLong * scale; x++) {
                int xa = x + xp;
                if (xa < -pixelsLong || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.getPixel(x, y, scale);
                if (col != 0xffff00ff) {
                    FrameState.setPixel(xa + ya * width, sprite.getPixel(x, y, scale));
                }
            }
        }
    }

    public int applyPixelSmoothingFilter(int i, int width, int height) {
        int row;
        row = i / width;
        int col = i % width;

        if (row == 0 || col == 0 || row == height - 1 || col == width - 1) return FrameState.getPixel(i);

        int[] kernel = new int[9];
        int[] rkernel = new int[9];
        int[] gkernel = new int[9];
        int[] bkernel = new int[9];

        kernel[0] = FrameState.getPixel(((row - 1) * width) + (col - 1));
        kernel[1] = FrameState.getPixel(((row - 1) * width) + (col));
        kernel[2] = FrameState.getPixel(((row - 1) * width) + (col + 1));
        kernel[3] = FrameState.getPixel(((row) * width) + (col - 1));
        kernel[4] = FrameState.getPixel(((row) * width) + (col));
        kernel[5] = FrameState.getPixel(((row) * width) + (col + 1));
        kernel[6] = FrameState.getPixel(((row + 1) * width) + (col - 1));
        kernel[7] = FrameState.getPixel(((row + 1) * width) + (col));
        kernel[8] = FrameState.getPixel(((row + 1) * width) + (col + 1));

        for (int a = 0; a < 9; a++) {
            rkernel[a] = (kernel[a] >> 16) & 0xff;
            gkernel[a] = (kernel[a] >> 8) & 0xff;
            bkernel[a] = kernel[a] & 0xff;
        }

        int avgr = 0, avgg = 0, avgb = 0, rtot = 0, gtot = 0, btot = 0;
        for (int a = 0; a < 9; a++) {
            rtot += rkernel[a];
            gtot += gkernel[a];
            btot += bkernel[a];
        }

        avgr = rtot / 9;
        avgg = gtot / 9;
        avgb = btot / 9;

        return new Color(avgr, avgg, avgb).getRGB();
    }


    public int applyPixelSimpleAAFilter(int i, int width, int height) {

        int row = i / width;
        int col = i % width;

        if (row == 0 || col == 0 || row == height - 1 || col == width - 1) return FrameState.getPixel(i);

        int[] kernel = new int[9];
        int[] rkernel = new int[9];
        int[] gkernel = new int[9];
        int[] bkernel = new int[9];
        float[] weight = {0.3f, 0.3f, 0.3f, 0.3f, 6.6f, 0.3f, 0.3f, 0.3f, 0.3f};

        kernel[0] = FrameState.getPixel(((row - 1) * width) + (col - 1));
        kernel[1] = FrameState.getPixel(((row - 1) * width) + (col));
        kernel[2] = FrameState.getPixel(((row - 1) * width) + (col + 1));
        kernel[3] = FrameState.getPixel(((row) * width) + (col - 1));
        kernel[4] = FrameState.getPixel(((row) * width) + (col));
        kernel[5] = FrameState.getPixel(((row) * width) + (col + 1));
        kernel[6] = FrameState.getPixel(((row + 1) * width) + (col - 1));
        kernel[7] = FrameState.getPixel(((row + 1) * width) + (col));
        kernel[8] = FrameState.getPixel(((row + 1) * width) + (col + 1));

        for (int a = 0; a < 9; a++) {
            rkernel[a] = (kernel[a] >> 16) & 0xff;
            gkernel[a] = (kernel[a] >> 8) & 0xff;
            bkernel[a] = kernel[a] & 0xff;
        }

        int avgr, avgg, avgb, rtot = 0, gtot = 0, btot = 0;
        for (int a = 0; a < 9; a++) {
            rtot += rkernel[a] * weight[a];
            gtot += gkernel[a] * weight[a];
            btot += bkernel[a] * weight[a];
        }

        avgr = rtot / 9;
        avgg = gtot / 9;
        avgb = btot / 9;

        return new Color(avgr, avgg, avgb).getRGB();
    }

    public int applyPixelsMedianBlur(int i, int width, int height) {
        int row = i / width;
        int col = i % width;

        if (row == 0 || col == 0 || row == height - 1 || col == width - 1) return FrameState.getPixel(i);
        int[] kernel = new int[9];
        int[] rkernel = new int[9];
        int[] gkernel = new int[9];
        int[] bkernel = new int[9];

        kernel[0] = FrameState.getPixel(((row - 1) * width) + (col - 1));
        kernel[1] = FrameState.getPixel(((row - 1) * width) + (col));
        kernel[2] = FrameState.getPixel(((row - 1) * width) + (col + 1));
        kernel[3] = FrameState.getPixel(((row) * width) + (col - 1));
        kernel[4] = FrameState.getPixel(((row) * width) + (col));
        kernel[5] = FrameState.getPixel(((row) * width) + (col + 1));
        kernel[6] = FrameState.getPixel(((row + 1) * width) + (col - 1));
        kernel[7] = FrameState.getPixel(((row + 1) * width) + (col));
        kernel[8] = FrameState.getPixel(((row + 1) * width) + (col + 1));

        for (int a = 0; a < 9; a++) {
            rkernel[a] = (kernel[a] >> 16) & 0xff;
            gkernel[a] = (kernel[a] >> 8) & 0xff;
            bkernel[a] = kernel[a] & 0xff;
        }

        Arrays.sort(rkernel);
        Arrays.sort(gkernel);
        Arrays.sort(bkernel);

        return new Color(rkernel[4], gkernel[4], bkernel[4]).getRGB();
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
