package game.graphics.sprite;


import game.graphics.SpriteSheet;
import game.graphics.files.Image;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Sprite {
    int size;

    int sheetPosX;

    int sheePosY;

    Image image;

    private SpriteSheet sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet){
        this.size = size;
        this.sheetPosX = x * size;
        this.sheePosY = y * size;

        this.image = new Image();
        this.image.setPixels(new int[size * size]);
        this.image.setWidth(size);
        this.image.setHeight(size);

        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int colour){
        this.size = size;
        this.image = new Image();
        this.image.setPixels(new int[size * size]);
        this.image.setWidth(size);
        this.image.setHeight(size);
        setColour(colour);
    }

    protected void setColour(int colour){
        for(int i = 0; i < size * size; i++) {
            this.image.setPixel(i, 0, colour);
        }
    }

    protected void load(){
        for (int y = 0; y < this.image.getWidth(); y++) {
            for (int x = 0; x < this.image.getHeight(); x++) {
                int value = sheet.getPixels()[(x+this.sheetPosX) + (y+this.sheePosY) * sheet.getWidth()];
                this.image.setPixel(x, y, value);
            }
        }
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
