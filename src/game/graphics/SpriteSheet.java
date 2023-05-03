package game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteSheet {
    protected String path;
    public final int SIZE;
    protected int[] pixels;

    public SpriteSheet(String path, int size){
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    public void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int[] getPixels(){
        return this.pixels;
    }
}
