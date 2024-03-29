package game.graphics;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * Created by Matthew.c on 25/01/2017.
 */
public class FrameState {
    protected static int scale;
    protected static int[] pixels;
    protected static Point2D.Float offset = new Point2D.Float(0, 0);
    protected static Dimension2D frameSize = new Dimension();

    public static int getScale() {
        return FrameState.scale;
    }

    public static void setScale(int scale) {
        FrameState.scale = scale;
    }

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

    public static Dimension2D getFrameSize() {
        return FrameState.frameSize;
    }

    public static void setFrameSize(Dimension2D frameSize) {
        FrameState.frameSize = frameSize;
    }
}
