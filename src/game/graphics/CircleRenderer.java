package game.graphics;

import game.graphics.ui.Circle;

public class CircleRenderer {
    public void render(Circle circle, boolean fixed) {
        int xp = circle.getScreenPosX();
        int yp = circle.getScreenPosY();
        int radius = circle.getSizeRadius();
        int fill = circle.getCurrentBarPercent();
        int fillColour = circle.getBarFillColour();
        int borderColour = circle.getBarBorderColour();

        if (fixed) {
            xp -= (int) FrameState.getOffset().getX();
            yp -= (int) FrameState.getOffset().getY();
        }

        //fills from bottom to top
        //0 being empty 100 being full
        int fillAmount = (radius * fill) / 100;

        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius * 2; x++) {
                if (y + xp < 0 || y + xp >= FrameState.getFramesize().getWidth() || x + yp < 0 || x + yp >= FrameState.getFramesize().getHeight())
                    continue;

                if (x >= radius - (fillAmount * 2)) {
                    if (Math.round(Math.sqrt(y * y + x * x)) < radius) {
                        FrameState.setPixel((y + xp) + ((int) FrameState.getFramesize().getWidth() * (x + yp)), fillColour);
                    }
                }

                if (Math.round(Math.sqrt(y * y + x * x)) == radius) {
                    FrameState.setPixel((y + xp) + ((int) FrameState.getFramesize().getWidth() * (x + yp)), borderColour);
                }

            }
        }
    }
}
