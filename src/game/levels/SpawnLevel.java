package game.levels;

import game.levels.tile.TileManager;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Matthew.c on 29/01/2017.
 */
public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevelFromFile(String path){
        try{
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResourceAsStream(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            TileManager.tiles = new int[w*h];
            image.getRGB(0,0,w,h,TileManager.tiles, 0, w);
        }catch (IOException e) {
            System.err.println("Level file could not be found:");
            e.printStackTrace();
        }
    }

}
