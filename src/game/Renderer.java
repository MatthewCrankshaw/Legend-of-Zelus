package game;

import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.graphics.sprite.SpriteRenderer;
import game.levels.tile.Tile;
import game.levels.tile.animated_tiles.AnimatedTile;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Renderer {
    protected SpriteRenderer spriteRenderer;
    protected SpriteRegistry spriteRegistry;
    private Point2D.Float offset;
    private BufferedImage image;
    private int[] pixels;
    private int scale;

    public Renderer(SpriteRenderer renderer, SpriteRegistry registry, BufferedImage image, int[] pixels, int scale) {
        this.spriteRenderer = renderer;
        this.spriteRegistry = registry;
        this.offset = new Point2D.Float();
        this.image = image;
        this.pixels = pixels;
        this.scale = scale;
    }

    public void setOffset(float x, float y) {
        this.offset.setLocation(x, y);
    }

    public void renderSprite(Point2D position, Dimension2D frameSize, Sprite sprite, boolean fixed, int colour, int spriteScale) {
        this.spriteRenderer.render(pixels, sprite, frameSize, position, offset, fixed, colour, spriteScale);
    }

    public void renderString(Dimension2D frameSize, int xp, int yp, String string, boolean center, int colour, boolean fixed) {
        if (fixed) {
            xp -= (int) this.offset.getX();
            yp -= (int) this.offset.getY();
        }
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                continue;
            }
            int len = string.length();
            int centerOffset;
            if (center) {
                centerOffset = ((len * 8) / 2) * scale;
            } else {
                centerOffset = 0;
            }
            Point2D position = new Point2D.Float(xp + (i * 8 * scale) - centerOffset, yp);
            renderSprite(position, frameSize, spriteRegistry.getCharacterSprite(string.charAt(i)), false, colour, scale);
        }
    }

    public void renderTile(Dimension2D frameSize, Point2D position, Tile tile) {
        this.spriteRenderer.render(pixels, tile.getCurrentSprite(), frameSize, position, offset, true, -1, 1);
        tile.tick();
    }

    public void renderAnimatedTile(int xp, int yp, AnimatedTile animTile, int width, int height) {

        Sprite sprite = animTile.getCurrentSprite();
        xp -= offset.getX();
        yp -= offset.getY();
        for (int y = 0; y < 16; y++) {
            int ya = y + yp;
            for (int x = 0; x < 16; x++) {
                int xa = x + xp;
                if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.getPixel(x, y);
                if (col != 0xffff00ff) {
                    pixels[xa + ya * width] = sprite.getPixel(x, y);
                }
            }
        }
    }

    public void renderLine(double xp1, double yp1, double xp2, double yp2, int width, int height, int colour, boolean fixed) {

        if (fixed) {
            xp1 -= offset.getX();
            yp1 -= offset.getY();
            xp2 -= offset.getX();
            yp2 -= offset.getY();
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
            if (xp1 < 0 || xp1 >= width || yp1 < 0 || yp1 >= height) break;
            if (xp2 < 0 || xp2 >= width || yp2 < 0 || yp2 >= height) break;
            pixels[(int) xp1 + (width * (int) yp1)] = colour;
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

    public void renderConnectedLine(ArrayList<Point> points, int xOffset, int yOffset, int width, int height, int colour, boolean fixed) {
        for (int i = 0; i < points.size() - 1; i++) {
            renderLine(
                    points.get(i).x + xOffset,
                    points.get(i).y + yOffset,
                    points.get(i + 1).x + xOffset,
                    points.get(i + 1).y + yOffset,
                    width,
                    height,
                    colour,
                    fixed
            );
        }
    }

    public void renderRectangle(int xp, int yp, int width, int height, int screenWidth, int screenHeight, int currentPercent, int colourFill, int colourBorder, boolean fixed) {
        if (fixed) {
            xp -= offset.getX();
            yp -= offset.getY();
        }

        for (int y = yp; y <= yp + height; y++) {
            for (int x = xp; x <= (xp + width); x++) {
                if (x >= screenWidth || y >= screenHeight || x < 0 || y < 0) continue;
                if (y == yp || y == yp + height || x == xp || x == xp + width) {
                    pixels[x + (screenWidth * y)] = colourBorder;
                } else {
                    if (x <= (currentPercent * 0.01 * width) + xp) {
                        pixels[x + (screenWidth * y)] = colourFill;
                    }
                }
            }
        }
    }

    public void renderCircle(int xp, int yp, int width, int height, int radius, int fill, int colour, int borderColour, boolean filled, boolean fixed) {
        //whether the circle moves with the screen or is relative to the ground
        if (fixed) {
            xp -= offset.getX();
            yp -= offset.getY();
        }

        //fills from bottom to top
        //0 being empty 100 being full
        int fillAmount = (radius * fill) / 100;

        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius * 2; x++) {
                if (y + xp < 0 || y + xp >= width || x + yp < 0 || x + yp >= height) continue;

                if (x >= radius - (fillAmount * 2)) {
                    if (filled) {
                        if (Math.round(Math.sqrt(y * y + x * x)) < radius) {
                            pixels[(y + xp) + (width * (x + yp))] = colour;
                        }
                    }
                }

                if (Math.round(Math.sqrt(y * y + x * x)) == radius) {
                    pixels[(y + xp) + (width * (x + yp))] = borderColour;
                }

            }
        }
    }

    public void renderPlayer(int xp, int yp, int width, int height, int pixelsLong, int pixelshigh, int scale, Sprite sprite) {
        xp -= offset.getX();
        yp -= offset.getY();
        for (int y = 0; y < pixelshigh * scale; y++) {
            int ya = y + yp;
            for (int x = 0; x < pixelsLong * scale; x++) {
                int xa = x + xp;
                if (xa < -pixelsLong || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.getPixel(x, y, scale);
                if (col != 0xffff00ff) {
                    pixels[xa + ya * width] = sprite.getPixel(x, y, scale);
                }
            }
        }
    }

    public int applyPixelSmoothingFilter(int i, int width, int height) {
        int row;
        row = i / width;
        int col = i % width;

        if (row == 0 || col == 0 || row == height - 1 || col == width - 1) return pixels[i];

        int[] kernel = new int[9];
        int[] rkernel = new int[9];
        int[] gkernel = new int[9];
        int[] bkernel = new int[9];

        kernel[0] = pixels[((row - 1) * width) + (col - 1)];
        kernel[1] = pixels[((row - 1) * width) + (col)];
        kernel[2] = pixels[((row - 1) * width) + (col + 1)];
        kernel[3] = pixels[((row) * width) + (col - 1)];
        kernel[4] = pixels[((row) * width) + (col)];
        kernel[5] = pixels[((row) * width) + (col + 1)];
        kernel[6] = pixels[((row + 1) * width) + (col - 1)];
        kernel[7] = pixels[((row + 1) * width) + (col)];
        kernel[8] = pixels[((row + 1) * width) + (col + 1)];

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

        if (row == 0 || col == 0 || row == height - 1 || col == width - 1) return pixels[i];

        int[] kernel = new int[9];
        int[] rkernel = new int[9];
        int[] gkernel = new int[9];
        int[] bkernel = new int[9];
        float[] weight = {0.3f, 0.3f, 0.3f, 0.3f, 6.6f, 0.3f, 0.3f, 0.3f, 0.3f};

        kernel[0] = pixels[((row - 1) * width) + (col - 1)];
        kernel[1] = pixels[((row - 1) * width) + (col)];
        kernel[2] = pixels[((row - 1) * width) + (col + 1)];
        kernel[3] = pixels[((row) * width) + (col - 1)];
        kernel[4] = pixels[((row) * width) + (col)];
        kernel[5] = pixels[((row) * width) + (col + 1)];
        kernel[6] = pixels[((row + 1) * width) + (col - 1)];
        kernel[7] = pixels[((row + 1) * width) + (col)];
        kernel[8] = pixels[((row + 1) * width) + (col + 1)];

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

        if (row == 0 || col == 0 || row == height - 1 || col == width - 1) return pixels[i];
        int[] kernel = new int[9];
        int[] rkernel = new int[9];
        int[] gkernel = new int[9];
        int[] bkernel = new int[9];

        kernel[0] = pixels[((row - 1) * width) + (col - 1)];
        kernel[1] = pixels[((row - 1) * width) + (col)];
        kernel[2] = pixels[((row - 1) * width) + (col + 1)];
        kernel[3] = pixels[((row) * width) + (col - 1)];
        kernel[4] = pixels[((row) * width) + (col)];
        kernel[5] = pixels[((row) * width) + (col + 1)];
        kernel[6] = pixels[((row + 1) * width) + (col - 1)];
        kernel[7] = pixels[((row + 1) * width) + (col)];
        kernel[8] = pixels[((row + 1) * width) + (col + 1)];

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

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public int getPixel(int i) {
        return this.pixels[i];
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getScale() {
        return this.scale;
    }
}
