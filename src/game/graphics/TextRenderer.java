package game.graphics;

import game.Renderer;
import game.graphics.sprite.SpriteRegistry;
import game.graphics.ui.Text;

import java.awt.geom.Point2D;

public class TextRenderer {
    protected SpriteRegistry registry;
    protected Renderer renderer;

    public TextRenderer(SpriteRegistry registry, Renderer renderer) {
        this.registry = registry;
        this.renderer = renderer;
    }

    public void render(Text text) {
        int xp = (int) text.getPosition().getX();
        int yp = (int) text.getPosition().getY();

        if (text.getFixed()) {
            xp -= (int) FrameState.getOffset().getX();
            yp -= (int) FrameState.getOffset().getY();
        }
        for (int i = 0; i < text.getText().length(); i++) {
            if (text.getText().charAt(i) == ' ') {
                continue;
            }
            int len = text.getText().length();
            int centerOffset = ((len * 8) / 2) * FrameState.getScale();
            Point2D position = new Point2D.Float(xp + (i * 8 * FrameState.getScale()) - centerOffset, yp);
            this.renderer.renderSprite(position, this.registry.getCharacterSprite(text.getText().charAt(i)), false, text.getColour(), FrameState.getScale());
        }
    }
}
