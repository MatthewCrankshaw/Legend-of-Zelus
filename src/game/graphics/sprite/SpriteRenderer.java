package game.graphics.sprite;


import game.graphics.FrameState;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class SpriteRenderer {

    public void render(Sprite sprite, Dimension2D screenDimensions, Point2D position, boolean fixed, int colour, int scale) {
        if (fixed) {
            position.setLocation(
                    position.getX() - FrameState.getOffset().getX(),
                    position.getY() - FrameState.getOffset().getY()
            );
        }

        for (int y = 0; y < sprite.getSize() * scale; y++) {
            int ya = y + (int) position.getY();
            for (int x = 0; x < sprite.getSize() * scale; x++) {
                int xa = x + (int) position.getX();
                if (xa < -sprite.getSize() * scale || xa >= screenDimensions.getWidth() || ya < 0 || ya >= screenDimensions.getHeight())
                    break;
                if (xa < 0) xa = 0;

                //Set the colour -1 for original colour on sprite
                int col = sprite.getPixel(x, y, scale);

                if (sprite.getPixel(x, y, scale) != 0xffff00ff) {
                    col = colour;
                }

                if (colour == -1) {
                    col = sprite.getPixel(x, y, (int) scale);
                }

                if (col != 0xffff00ff) {
                    FrameState.setPixel(xa + ya * (int) screenDimensions.getWidth(), col);
                }
            }
        }
    }
}
