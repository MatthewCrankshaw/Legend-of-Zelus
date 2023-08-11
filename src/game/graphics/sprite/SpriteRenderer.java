package game.graphics.sprite;


import game.graphics.SpriteSheet;
import game.graphics.files.Image;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteRenderer {

    public int[] render(int[] pixels, Sprite sprite, Dimension2D screenDimensions, Point2D position, Point2D offset, boolean fixed, int colour, int scale){
        if(fixed) {
            position.setLocation(
                position.getX() - offset.getX(),
                position.getY() - offset.getY()
            );
        }

        for(int y = 0; y < sprite.getSize()*scale; y ++) {
            int ya = y + (int)position.getY();
            for(int x = 0; x < sprite.getSize()*scale; x++) {
                int xa = x + (int)position.getX();
                if (xa < -sprite.getSize()*scale || xa >= screenDimensions.getWidth() || ya < 0 || ya >= screenDimensions.getHeight()) break;
                if (xa < 0) xa = 0;

                //Set the colour -1 for original colour on sprite
                int col = sprite.getPixel(x , y, scale);

                if(sprite.getPixel(x, y, scale) != 0xffff00ff){
                    col = colour;
                }

                if(colour == -1) {
                    col = sprite.getPixel(x, y, (int)scale);
                }

                if (col != 0xffff00ff){
                    pixels[xa+ya*(int)screenDimensions.getWidth()] = col;
                }
            }
        }
        return pixels;
    }
}
