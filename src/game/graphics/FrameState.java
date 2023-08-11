package game.graphics;

import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class FrameState {
    protected static int[] pixels;
    protected static Point2D.Float offset = new Point2D.Float(0, 0);

    public static int[] getPixels() {
        return FrameState.pixels;
    }

    public static void setPixels(int[] pixels) {
        FrameState.pixels = pixels;
    }

    public static void setPixel(int index, int colour) {
        FrameState.pixels[index] = colour;
    }

    public static int getPixel(int index) {
        return FrameState.pixels[index];
    }

    public static void clear() {
        Arrays.fill(pixels, 0);
    }

    public static Point2D.Float getOffset() {
        return FrameState.offset;
    }

    public static void setOffset(Point2D.Float offset) {
        FrameState.offset = offset;
    }

    public static void setOffset(int x, int y) {
        FrameState.offset.setLocation(x, y);
    }
}
