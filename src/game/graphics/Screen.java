package game.graphics;

import game.graphics.sprite.Sprite;
import game.levels.tile.Tile;
import game.levels.tile.animated_tiles.AnimatedTile;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Screen {

    public int width, height;

    public double xOffset;
    public double yOffset;

    public int[] pixels;


    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear(){
        for (int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < sprite.SIZE; y ++) {
            int ya = y + yp;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[x+y*sprite.SIZE];
                if (col != 0xffff00ff){
                    pixels[xa+ya*width] = sprite.pixels[x + y * sprite.SIZE];
                }
            }
        }
    }

    public void renderTile(int xp, int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < tile.currentSprite.SIZE; y ++) {
            int ya = y + yp;
            for(int x = 0; x < tile.currentSprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.currentSprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa+ya*width] = tile.currentSprite.pixels[x+ y * tile.currentSprite.SIZE];
            }
        }
        tile.tick();
    }

    public void renderProjectile(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0 ; y < sprite.SIZE; y ++) {
            int ya = y + yp;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[x+y*sprite.SIZE];
                if (col != 0xffff00ff){
                    pixels[xa+ya*width] = sprite.pixels[x + y * sprite.SIZE];
                }
            }
        }
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
                int col = sprite[animIndex].pixels[x+y*Tile.TILE_SIZE*2];
                if (col != 0xffff00ff){
                    pixels[xa+ya*width] = sprite[animIndex].pixels[x + y * Tile.TILE_SIZE*2];
                }
            }
        }
    }

    public void renderRectangle(int xp, int yp, int width, int height, int colour, boolean fixed){
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int x = xp; x< xp + width; x++) {
            if (x < 0 | x >= this.width || yp >= this.height) continue;
            if (yp > 0) pixels[x + yp * this.width] = colour;
            if (yp + height >= this.height) continue;
            if (yp + height > 0) pixels[x+(yp + height) *this.width] = colour;
        }

        for (int y = yp; y <= yp + height; y++) {
            if (xp >= this.width || y < 0 || y >= this.height) continue;
            if (xp > 0) pixels[xp + y * this.width] = colour;
            if (xp + width >= this.width) continue;
            if (xp + width > 0) pixels[(xp + width) + y * this.width] = colour;
        }

    }

    public void renderPlayer(int xp, int yp , int pixelsLong, int pixelshigh, Sprite sprite){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0 ; y < pixelshigh; y ++) {
            int ya = y + yp;
            for(int x = 0; x < pixelsLong; x++) {
                int xa = x + xp;
                if (xa < -Tile.TILE_SIZE*2 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[x + y * Tile.TILE_SIZE*2];
                if (col != 0xffff00ff){
                    pixels[xa+ya*width] = sprite.pixels[x + y * 16];
                }
            }
        }
    }

    public void setOffset(double x, double y){
        this.xOffset = x;
        this.yOffset = y;
    }
}
