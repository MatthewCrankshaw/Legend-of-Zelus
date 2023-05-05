package game.graphics.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public Image load(String path){
        Image image = new Image();
        BufferedImage buff = loadImageBuffer(path);

        int w = buff.getWidth();
        int h = buff.getHeight();

        int[] pixels = new int[w * h];
        buff.getRGB(0, 0, w, h, pixels, 0, w);

        image.setPixels(pixels);
        image.setWidth(w);
        image.setHeight(h);
        return image;
    }

    protected BufferedImage loadImageBuffer(String path){
        try {
            String fullPath = getFullPath(path);
            File file = new File(fullPath);
            return ImageIO.read(file);
        }catch (IOException e){
            System.out.printf("Unable to load image [%s]\n", path);
        }
        return new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
    }

    protected String getFullPath(String path){
        String dir = System.getProperty("user.dir");
        return dir + "/res" + path;
    }
}
