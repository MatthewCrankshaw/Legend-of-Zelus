package game.graphics.sprite;


import game.graphics.SpriteSheet;
import java.util.ArrayList;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class FontSprite extends Sprite{


    private static ArrayList<FontSprite> CHAR_SPRITES = loadCharSprites();

    private FontSprite(int size, int x, int y, SpriteSheet sheet) {
        super(size, x, y, sheet);
    }

    private static ArrayList<FontSprite> loadCharSprites(){
        ArrayList<FontSprite> font = new ArrayList<>();
        //Letters
        for(int i = 0; i < 26; i++){
            font.add( new FontSprite(8, i, 30, SpriteSheet.spriteSheet));
        }
        //Digits and Signs
        for(int i = 0; i < 26; i++){
            font.add(new FontSprite(8, i, 31, SpriteSheet.spriteSheet));
        }
        return font;
    }

    public static FontSprite getCharacterSprite(char letter){
        String charOrder = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,:;'\"!?$%()-=+/";

        letter = Character.toUpperCase(letter);
        int i = charOrder.indexOf(letter);
        if(i == -1){
            System.out.println("Error: invalid character for FontSprite. Character:" + letter);
            System.exit(1);
        }
        return CHAR_SPRITES.get(i);
    }
}
