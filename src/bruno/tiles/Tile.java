package bruno.tiles;

import bruno.entity.Mob;
import bruno.gfx.Screen;
import bruno.gfx.Sprite;

public class Tile {
	
	public Sprite sprite;
	public static Sprite scrollSprite;
	
	public static Tile grassTile = new GrassTile(Sprite.grass);
	public static Tile stoneTile = new StoneTile(Sprite.stone);
	public static Tile waterTile = new WaterTile(Sprite.water);
	public static Tile dirtTile = new DirtTile(Sprite.dirt);
	public static Tile flowerTile = new FlowerTile(Sprite.flower);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile warpTile = new WarpTile(Sprite.warp);
	public static Tile grass2Tile = new Grass2Tile(Sprite.grass2);
	public static Tile lavaTile = new LavaTile(Sprite.lava);
	public static Tile scrollTile = new ScrollTile(scrollSprite);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
		init();
	}
	
	public static Sprite init() {
		if (Mob.scrollCollision) scrollSprite = Sprite.scroll;
		else if (!Mob.scrollCollision) scrollSprite = Sprite.grass;
		return scrollSprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public boolean isTeleport() {
		return false;
	}
	
	public boolean isHurt() {
		return false;
	}
	
	public boolean isScroll() {
		return false;
	}

}
