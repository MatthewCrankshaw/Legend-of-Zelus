package game.graphics;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.SpriteRegistry;
import game.levels.tile.Tile;
import game.levels.tile.TileConstants;
import game.levels.tile.animated_tiles.AnimatedTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Screen {

    private int width, height;
    private int scale;
    private double xOffset;
    private double yOffset;
    public int[] pixels;
    private boolean AAFilterEnabled, MedianBlurEnabled, SmoothingFilterEnabled;
    protected SpriteRegistry spriteRegistry;

    public Screen(int width, int height, int scale, SpriteRegistry spriteRegistry){
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.spriteRegistry = spriteRegistry;
        this.pixels = new int[width * height];

        this.AAFilterEnabled = false;
        this.MedianBlurEnabled = false;
        this.SmoothingFilterEnabled = false;
    }

    public void clear(){
        for (int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed, int colour, int spriteScale){
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for(int y = 0; y < sprite.SIZE*spriteScale; y ++) {
            int ya = y + yp;
            for(int x = 0; x < sprite.SIZE*spriteScale; x++) {
                int xa = x + xp;
                if (xa < -sprite.SIZE*spriteScale || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;

                //Set the colour -1 for original colour on sprite
                int col = sprite.pixels[(x/spriteScale) + (y/spriteScale) * sprite.SIZE];

                if(sprite.pixels[(x/spriteScale) + (y/spriteScale) * sprite.SIZE] != 0xffff00ff){
                    col = colour;
                }

                if(colour == -1) {
                    col = sprite.pixels[(x/spriteScale) + (y/spriteScale) * sprite.SIZE];
                }

                if (col != 0xffff00ff){
                    pixels[xa+ya*width] = col;
                }
            }
        }
    }

    public void renderString(int xp, int yp, String string, boolean center, int colour, int scale, boolean fixed){
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == ' '){
                continue;
            }
            int len = string.length();
            int centerOffset;
            if(center){
                centerOffset = ((len*8)/2) * scale;
            }else centerOffset = 0;
            renderSprite(xp + (i*8*scale) - centerOffset, yp, spriteRegistry.getCharacterSprite(string.charAt(i)), false, colour, scale);
        }
    }

    public void renderTile(int xp, int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < tile.getCurrentSprite().SIZE; y ++) {
            int ya = y + yp;
            for(int x = 0; x < tile.getCurrentSprite().SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.getCurrentSprite().SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa+ya*width] = tile.getCurrentSprite().pixels[x+ y * tile.getCurrentSprite().SIZE];
            }
        }
        tile.tick();
    }

    public void renderAnimatedTile(int xp, int yp, AnimatedTile animTile, Sprite[] sprite){

        int animIndex = animTile.getCurrentAnimationIndex();
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0 ; y < 16; y ++) {
            int ya = y + yp;
            for(int x = 0; x < 16; x++) {
                int xa = x + xp;
                if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite[animIndex].pixels[x+y* TileConstants.TILE_SIZE*2];
                if (col != 0xffff00ff){
                    pixels[xa+ya*width] = sprite[animIndex].pixels[x + y * TileConstants.TILE_SIZE*2];
                }
            }
        }
    }

    public void renderLine(double xp1, double yp1, double xp2, double yp2, int colour, boolean fixed){

        if(fixed) {
            xp1 -= xOffset; yp1 -= yOffset; xp2 -= xOffset; yp2 -= yOffset;
        }

        double x = Math.abs(xp2 - xp1);
        double y = Math.abs(yp2 - yp1);

        double max = Math.max(x, y);

        //Inside try catch block because there is a possibility to have divide by zero
        try {
            x /= max;
            y /= max;
        }catch (ArithmeticException e){
            //If divide by zero then just return
            //We don't need to draw the line
            return;
        }

        for(int i = 0; i < max; i++){
            if(xp1 < 0 || xp1 >= width || yp1 < 0 || yp1 >= height) break;
            if(xp2 < 0 || xp2 >= width || yp2 < 0 || yp2 >= height) break;
            pixels[(int)xp1 + (this.width * (int)yp1)] = colour;
            if(xp1 < xp2) {
                xp1 += x;
            }else{
                xp1 -= x;
            }
            if(yp1 < yp2){
                yp1 += y;
            }else{
                yp1 -= y;
            }
        }
    }

    public void renderConnectedLine(ArrayList<Point> points, int xOffset, int yOffset, int colour, boolean fixed){
        for(int i = 0; i < points.size() - 1; i++){
            renderLine(points.get(i).x + xOffset, points.get(i).y+ yOffset,points.get(i+1).x+ xOffset,points.get(i+1).y+ yOffset, colour, fixed);
        }
    }

    public void renderRectangle(int xp, int yp, int width, int height, int currentPercent, int colourFill, int colourBorder, boolean fixed){
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for(int y = yp; y <= yp + height; y++) {
            for (int x = xp; x <= (xp + width); x++) {
               if(x >= this.width || y >= this.height || x < 0 || y < 0) continue;
                if(y == yp || y == yp+height || x == xp || x == xp+width) {
                    pixels[x + (this.width*y) ] = colourBorder;
                }else{
                    if(x <= (currentPercent * 0.01 * width) + xp){
                        pixels[x + (this.width * y)] = colourFill;
                    }
                }
            }
        }
    }

    public void renderCircle(int xp, int yp, int radius, int fill, int colour, int borderColour, boolean filled, boolean fixed){
        //whether the circle moves with the screen or is relative to the ground
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        //fills from bottom to top
        //0 being empty 100 being full
        int fillAmount = (radius * fill) / 100;

        for(int y = -radius; y <= radius; y++){
            for(int x = -radius; x <=radius*2; x++){
                if(y+xp < 0 || y+xp >= width || x+yp < 0 || x+yp >= height) continue;

                if(x >= radius - (fillAmount*2)) {
                    if (filled) {
                        if (Math.round(Math.sqrt(y * y + x * x)) < radius) {
                            pixels[(y + xp) + (this.width * (x + yp))] = colour;
                        }
                    }
                }

                if(Math.round(Math.sqrt(y*y + x*x)) == radius){
                    pixels[(y+xp) + (this.width * (x+yp))] = borderColour;
                }

            }
        }
    }

    public void renderPlayer(int xp, int yp , int pixelsLong, int pixelshigh, int scale, Sprite sprite){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0 ; y < pixelshigh*scale; y++) {
            int ya = y + yp;
            for(int x = 0; x < pixelsLong*scale; x++) {
                int xa = x + xp;
                if (xa < -pixelsLong || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[x/scale + y/scale * 16];
                if (col != 0xffff00ff){
                    pixels[xa+ya*width] = sprite.pixels[x/scale + y/scale * 16];
                }
            }
        }
    }

    public void setOffset(double x, double y){
        this.xOffset = x;
        this.yOffset = y;
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

    public double getxOffset() {
        return xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }


    //===================================================================
    // Filters
    //===================================================================

    public int getPixels(int i) {
        if(SmoothingFilterEnabled){
            getPixelsSmoothingFilter(i);
        }
        if(MedianBlurEnabled){
            getPixelsMedianBlur(i);
        }
        if(AAFilterEnabled){
            getPixelsSimpleAAFilter(i);
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

    private void getPixelsSmoothingFilter(int i){
        int row;
        row = i/ width;
        int col = i% width;

        if(row == 0 || col == 0 || row == height - 1 || col == width -1) return;

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
        kernel[6] = pixels[((row + 1) * width)+ (col - 1)];
        kernel[7] = pixels[((row + 1) * width) + (col)];
        kernel[8] = pixels[((row + 1) * width) + (col + 1)];

        for(int a = 0; a < 9; a++){
            rkernel[a] = (kernel[a] >> 16) & 0xff;
            gkernel[a] = (kernel[a] >> 8) & 0xff;
            bkernel[a] = kernel[a] & 0xff;
        }

        int avgr = 0, avgg = 0, avgb = 0, rtot = 0, gtot = 0, btot = 0;
        for(int a = 0; a < 9; a++){
            rtot += rkernel[a];
            gtot += gkernel[a];
            btot += bkernel[a];
        }

        avgr = rtot/9;
        avgg = gtot/9;
        avgb = btot/9;

        pixels[i] = new Color(avgr, avgg, avgb).getRGB();
    }


    private void getPixelsSimpleAAFilter(int i) {

        int row = i/width;
        int col = i%width;

        if(row == 0 || col == 0 || row == height - 1 || col == width -1) return;

        int[] kernel = new int[9];
        int[] rkernel = new int[9];
        int[] gkernel = new int[9];
        int[] bkernel = new int[9];
        float[] weight = {0.3f, 0.3f, 0.3f, 0.3f,6.6f,0.3f,0.3f,0.3f,0.3f};

        kernel[0] = pixels[((row - 1) * width) + (col - 1)];
        kernel[1] = pixels[((row - 1) * width) + (col)];
        kernel[2] = pixels[((row - 1) * width) + (col + 1)];
        kernel[3] = pixels[((row) * width) + (col - 1)];
        kernel[4] = pixels[((row) * width) + (col)];
        kernel[5] = pixels[((row) * width) + (col + 1)];
        kernel[6] = pixels[((row + 1) * width)+ (col - 1)];
        kernel[7] = pixels[((row + 1) * width) + (col)];
        kernel[8] = pixels[((row + 1) * width) + (col + 1)];

        for(int a = 0; a < 9; a++){
            rkernel[a] = (kernel[a] >> 16) & 0xff;
            gkernel[a] = (kernel[a] >> 8) & 0xff;
            bkernel[a] = kernel[a] & 0xff;
        }

        int avgr, avgg, avgb, rtot = 0, gtot = 0, btot = 0;
                for(int a = 0; a < 9; a++){
            rtot += rkernel[a] * weight[a];
            gtot += gkernel[a] * weight[a];
            btot += bkernel[a] * weight[a];
        }

        avgr = rtot/9;
        avgg = gtot/9;
        avgb = btot/9;

        pixels[i] = new Color(avgr, avgg, avgb).getRGB();
    }

    private void getPixelsMedianBlur(int i){
        int row = i/width;
        int col = i%width;

        if(row == 0 || col == 0 || row == height - 1 || col == width -1) return;
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
        kernel[6] = pixels[((row + 1) * width)+ (col - 1)];
        kernel[7] = pixels[((row + 1) * width) + (col)];
        kernel[8] = pixels[((row + 1) * width) + (col + 1)];

        for(int a = 0; a < 9; a++){
            rkernel[a] = (kernel[a] >> 16) & 0xff;
            gkernel[a] = (kernel[a] >> 8) & 0xff;
            bkernel[a] = kernel[a] & 0xff;
        }

        Arrays.sort(rkernel);
        Arrays.sort(gkernel);
        Arrays.sort(bkernel);

       pixels[i] = new Color(rkernel[4], gkernel[4], bkernel[4]).getRGB();
    }
}
