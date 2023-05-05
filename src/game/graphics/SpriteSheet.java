package game.graphics;

import game.graphics.files.Image;
import game.graphics.files.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteSheet {
    protected ImageLoader loader;
    protected Image image;

    public SpriteSheet(ImageLoader loader, String path){
        this.loader = loader;
        load(path);
    }

    public void load(String path){
        this.image = loader.load(path);
    }

    public int[] getPixels(){
        return this.image.getPixels();
    }

    public int getWidth(){
        return this.image.getWidth();
    }
}
