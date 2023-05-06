package game.graphics.sprite;


import game.graphics.SpriteSheet;
import game.graphics.files.Image;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteLoader {
    public Sprite load(int size, int xPos, int yPos, SpriteSheet sheet){
        int sheetPosX = xPos * size;
        int sheetPosY = yPos * size;

        Image image = new Image();
        image.setPixels(new int[size * size]);
        image.setWidth(size);
        image.setHeight(size);

        for (int y = 0; y < image.getWidth(); y++) {
            for (int x = 0; x < image.getHeight(); x++) {
                int value = sheet.getPixels()[(x+sheetPosX) + (y+sheetPosY) * sheet.getWidth()];
                image.setPixel(x, y, value);
            }
        }

        return new Sprite(image);
    }

    public Sprite loadColour(int size, int colour){
        Image image = new Image();
        image.setPixels(new int[size * size]);
        image.setWidth(size);
        image.setHeight(size);

        for(int i = 0; i < size * size; i++) {
            image.setPixel(i, 0, colour);
        }

        return new Sprite(image);
    }
}
