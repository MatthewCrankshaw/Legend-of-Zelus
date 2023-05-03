package game.graphics.sprite;


import game.graphics.SpriteSheet;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class Sprite {
    public final int SIZE;
    protected int x,y;
    public int [] pixels;
    private SpriteSheet sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        load();
    }


    public Sprite(int size, int colour){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    public void setColour(int colour){
        for(int i = 0; i < SIZE  * SIZE; i ++) {
            pixels[i] = colour;
        }
    }

    public void load(){
        for (int y =0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x+y*SIZE] = sheet.getPixels()[(x+this.x) + (y+this.y) * sheet.SIZE];
            }
        }
    }
}
