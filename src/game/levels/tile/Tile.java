package game.levels.tile;

import game.graphics.Screen;
import game.graphics.sprite.Sprite;
import game.graphics.sprite.environment_sprites.FloorTileSprite;
import game.graphics.sprite.mob_sprites.PlayerSprite;
import game.levels.tile.animated_tiles.AnimatedTile;
import game.levels.tile.animated_transition_tiles.SandToWaterTiles;
import game.levels.tile.static_tiles.BasicSolidTile;
import game.levels.tile.static_tiles.BasicTile;
import game.levels.tile.static_tiles.VoidTile;
import game.levels.tile.transition_tiles.DirtToGrassTiles;
import game.levels.tile.transition_tiles.GrassToDirtTiles;
import game.levels.tile.transition_tiles.GrassToSandTiles;

/**
 * Created by Matthew.c on 26/01/2017.
 */
public abstract class Tile {
    protected int x, y;
    private float speedImparement;
    private boolean solid;

    protected Sprite currentSprite;
    protected Sprite[] animatedSprite;
    public static VoidTile voidTile = new VoidTile(FloorTileSprite.voidSprite);
    public static BasicSolidTile stone = new BasicSolidTile(FloorTileSprite.stone);
    public static BasicTile grass = new BasicTile(FloorTileSprite.grass);
    public static BasicTile woodFloor = new BasicTile(FloorTileSprite.woodFloor);
    public static BasicTile sandStone = new BasicTile(FloorTileSprite.sandStone);
    public static GrassToSandTiles grassToSandTiles = new GrassToSandTiles();
    public static SandToWaterTiles sandToWaterTiles = new SandToWaterTiles();
    public static DirtToGrassTiles dirtToGrassTiles = new DirtToGrassTiles();
    public static GrassToDirtTiles grassToDirtTiles = new GrassToDirtTiles();
    public static AnimatedTile mud = new AnimatedTile(FloorTileSprite.mud, false, 0.1f, 1000);
    public static AnimatedTile swimming = new AnimatedTile(PlayerSprite.swimming, false, 0.0f, 500  );

    public Tile(Sprite currentSprite, boolean solid, float speedImparement){
        this.currentSprite = currentSprite;
        this.solid = solid;
        this.speedImparement = speedImparement;
    }

    public Tile(Sprite[] animatedsprite, boolean solid, float speedImparement){
        this.animatedSprite = animatedsprite;
        this.solid = solid;
        this.speedImparement = speedImparement;
    }

    public Sprite getCurrentSprite() {
        return this.currentSprite;
    }

    public abstract void render(int x, int y, Screen screen);

    public abstract void tick();

    public boolean isSolid(){
        return solid;
    }

    public float getSpeedImparement(){ return speedImparement; }
}
