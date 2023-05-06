package game.graphics.sprite;


import game.graphics.SpriteSheet;
import game.graphics.files.Image;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Sprite {
    Image image;

    public Sprite(Image image){
        this.image = image;
    }

    public int[] getPixels(){
        return this.image.getPixels();
    }

    public int getPixel(int x, int y) {
        return getPixel(x, y, 1);
    }

    public int getPixel(int x, int y, int scale){
        return this.image.getPixel(x/scale, y/scale);
    }

    public int getSize(){
        return this.image.getWidth();
    }
}
