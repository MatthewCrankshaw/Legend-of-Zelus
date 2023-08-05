package game.graphics;
import game.Renderer;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.graphics.sprite.SpriteRenderer;
import game.levels.tile.Tile;
import game.levels.tile.TileConstants;
import game.levels.tile.animated_tiles.AnimatedTile;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Screen {
    private int screenWidth, screenHeight;
    private int scale;
    public int[] pixels;
    private boolean AAFilterEnabled, MedianBlurEnabled, SmoothingFilterEnabled;
    protected SpriteRegistry spriteRegistry;

    protected Renderer renderer;

    public Screen(int width, int height, int scale, SpriteRegistry spriteRegistry, Renderer renderer){
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.spriteRegistry = spriteRegistry;
        this.renderer = renderer;
        this.pixels = new int[width * height];

        this.AAFilterEnabled = false;
        this.MedianBlurEnabled = false;
        this.SmoothingFilterEnabled = false;
    }

    public void clear(){
        Arrays.fill(pixels, 0);
    }

    public void renderSprite(Point2D position, Sprite sprite, boolean fixed, int colour, int spriteScale){
        pixels = this.renderer.renderSprite(pixels, position, sprite, fixed, colour, spriteScale);
    }

    public void renderString(int xp, int yp, String string, boolean center, int colour, int scale, boolean fixed){
        pixels = this.renderer.renderString(pixels, xp, yp, string, center, colour, scale, fixed);
    }

    public void renderTile(Point2D position, Tile tile){
        pixels = this.renderer.renderTile(pixels, position, tile);
    }

    public void renderAnimatedTile(int xp, int yp, AnimatedTile animTile){
        pixels = this.renderer.renderAnimatedTile(pixels, xp, yp, animTile, width, height);
    }

    public void renderLine(double xp1, double yp1, double xp2, double yp2, int colour, boolean fixed){
        pixels = this.renderer.renderLine(pixels, xp1, yp1, xp2, yp2, width, height, colour, fixed);
    }

    public void renderConnectedLine(ArrayList<Point> points, int xOffset, int yOffset, int colour, boolean fixed){
        pixels = this.renderer.renderConnectedLine(pixels, points, xOffset, yOffset, width, height, colour, fixed);
    }

    public void renderRectangle(int xp, int yp, int width, int height, int currentPercent, int colourFill, int colourBorder, boolean fixed){
        pixels = this.renderer.renderRectangle(pixels, xp, yp, width, height, this.width, this.height, currentPercent, colourFill, colourBorder, fixed);
    }

    public void renderCircle(int xp, int yp, int radius, int fill, int colour, int borderColour, boolean filled, boolean fixed){
        pixels = this.renderer.renderCircle(pixels, xp, yp, width, height, this.width, radius, fill, colour, borderColour, filled, fixed);
    }

    public void renderPlayer(int xp, int yp , int pixelsLong, int pixelshigh, int scale, Sprite sprite){
        pixels = this.renderer.renderPlayer(pixels, xp, yp, width, height, pixelsLong, pixelshigh, scale, sprite);
    }

    public void setOffset(float x, float y){
        this.renderer.setOffset(x, y);
    }

    public int getScale() {
        return scale;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    //===================================================================
    // Filters
    //===================================================================

    public int[] getPixels(int[] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = getPixel(i);
        }
        return pixels;
    }

    protected int getPixel(int i) {
        if(SmoothingFilterEnabled){
            pixels[i] = this.renderer.applyPixelSmoothingFilter(pixels, i, width, height);
        }
        if(MedianBlurEnabled){
            pixels[i] = this.renderer.applyPixelsMedianBlur(pixels, i, width, height);
        }
        if(AAFilterEnabled){
            pixels[i] = this.renderer.applyPixelSimpleAAFilter(pixels, i, width, height);
        }
        return pixels[i];
    }

    public void switchAAFilterEnabled(){
        if (AAFilterEnabled){
            AAFilterEnabled = false;
        }else{
            AAFilterEnabled = true;
        }
    }

    public void switchMedianBlurEnabled(){
        if(MedianBlurEnabled){
            MedianBlurEnabled = false;
        }else{
            MedianBlurEnabled = true;
        }
    }

    public void switchSmoothingFilterEnabled(){
        if(SmoothingFilterEnabled){
            SmoothingFilterEnabled = false;
        }else{
            SmoothingFilterEnabled = true;
        }
    }

    public void setSmoothingFilterEnabled(boolean smoothingFilterEnabled) {
        SmoothingFilterEnabled = smoothingFilterEnabled;
    }
}
