package game.levels.tile;

import game.graphics.Screen;
import game.graphics.sprite.environment_sprites.FloorTileSprite;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.environment_sprites.SandToWaterSprites;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.tile.animated_tiles.AnimatedTile;
import game.levels.tile.static_tiles.BasicSolidTile;
import game.levels.tile.static_tiles.BasicTile;
import game.levels.tile.static_tiles.VoidTile;
import game.levels.tile.transition_tiles.DirtToGrassTiles;
import game.levels.tile.transition_tiles.GrassToDirtTiles;
import game.levels.tile.transition_tiles.GrassToSandTiles;
import game.levels.tile.animated_transition_tiles.SandToWaterTiles;

/**
 * Created by Matthew.c on 26/01/2017.
 */
public abstract class Tile {
    public int x, y;
    public static final int TILE_SIZE = 8;
    public static final int TILE_SHIFT_BIT = 3;
    private boolean solid;
    private boolean emitter;

    public Sprite currentSprite;
    public Sprite[] animatedSprite;

    //terrain tiles
    public static VoidTile voidTile = new VoidTile(FloorTileSprite.voidSprite);
    public static BasicSolidTile stone = new BasicSolidTile(FloorTileSprite.stone);
    public static BasicTile grass = new BasicTile(FloorTileSprite.grass);
    public static BasicTile woodFloor = new BasicTile(FloorTileSprite.woodFloor);
    public static BasicTile sand = new BasicTile(FloorTileSprite.sand);
    public static BasicTile sandStone = new BasicTile(FloorTileSprite.sandStone);

    //transition terrain tiles
    public static GrassToSandTiles grassToSandTiles = new GrassToSandTiles();
    public static SandToWaterTiles sandToWaterTiles = new SandToWaterTiles();
    public static DirtToGrassTiles dirtToGrassTiles = new DirtToGrassTiles();
    public static GrassToDirtTiles grassToDirtTiles = new GrassToDirtTiles();


    //animated terrain tiles
    public static AnimatedTile water = new AnimatedTile(FloorTileSprite.water, false, false, 700);
    public static AnimatedTile mud = new AnimatedTile(FloorTileSprite.mud, false, false, 1000);

    //animated player tiles
    public static AnimatedTile swimming = new AnimatedTile(PlayerSprite.swimming, false, false, 500  );




    public Tile(Sprite currentSprite, boolean solid, boolean emitter){
        this.currentSprite = currentSprite;
        this.solid = solid;
        this.emitter = emitter;
    }

    public Tile(Sprite[] animatedsprite, boolean solid, boolean emitter){
        this.animatedSprite = animatedsprite;
        this.solid = solid;
        this.emitter = emitter;
    }

    public abstract void render(int x, int y, Screen screen);

    public abstract void tick();

    public boolean isSolid(){
        return solid;
    }

    public boolean isEmitter(){
        return emitter;
    }




}
