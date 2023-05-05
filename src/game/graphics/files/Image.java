package game.graphics.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    protected int[] pixels;

    protected int width;

    protected int height;

    public int[] getPixels(){
        return this.pixels;
    }

    public void setPixels(int[] pixels){
        this.pixels = pixels;
    }

    public void setPixel(int x, int y, int value){
        this.pixels[x+y*getWidth()] = value;
    }

    public int getPixel(int x, int y){
        return this.pixels[x+y*getWidth()];
    }

    public int getWidth(){
        return this.width;
    }

    public void setWidth(int size){
        this.width = size;
    }

    public int getHeight(){
        return this.height;
    }

    public void setHeight(int size){
        this.height = size;
    }
}
