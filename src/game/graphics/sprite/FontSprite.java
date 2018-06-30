package game.graphics.sprite;


import game.graphics.SpriteSheet;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Matthew.c on 07/02/2017.
 */
public class FontSprite extends Sprite{


    private static ArrayList<FontSprite> charSprites = loadCharSprites();
    //private static ArrayList<FontSprite> digitSprites = loadDigitSprites();

    private static String charOrder = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.,:;'\"!?$%()-=+/";
    private static String digitOrder = "0123456789";

    public FontSprite(int size, int x, int y, SpriteSheet sheet) {
        super(size, x, y, sheet);
    }

    private static ArrayList<FontSprite> loadCharSprites(){
        ArrayList<FontSprite> font = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            font.add( new FontSprite(8, i, 30, SpriteSheet.text));
        }
        for(int i = 0; i < 16; i++){
            font.add(new FontSprite(8, 10 + i, 31, SpriteSheet.text));
        }
        return font;
    }

    public static FontSprite getCharacterSprite(char letter){
        int i = charOrder.indexOf(letter);
        if(i < 0 || i > 43) {
            System.out.println("Error: not a valid character for FontSprite.");
            return charSprites.get('A');
        }
        return charSprites.get(i);
    }
}
