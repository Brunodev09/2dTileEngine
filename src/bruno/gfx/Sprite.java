package bruno.gfx;

import java.awt.Color;

public class Sprite {

	public int x, y;
	public final int SIZE;
	public int[] pixels;
	private SpriteSheet sheet;
	
//	World 1 tiles
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite warp = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, Color.white.getRGB());
	
// World 2 tiles
	public static Sprite grass2 = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite stone2 = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite lava = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite flower2 = new Sprite(16, 3, 1, SpriteSheet.tiles);
	
//	Mob sprites	
	public static Sprite playerSprite = new Sprite(16, 0, 7, SpriteSheet.tiles);
	public static Sprite redSnake = new Sprite(16, 0, 6, SpriteSheet.tiles);
	public static Sprite blueSnake = new Sprite(16, 1, 6, SpriteSheet.tiles);
	public static Sprite orangeSnake = new Sprite(16, 2, 6, SpriteSheet.tiles);
	public static Sprite greenZombie = new Sprite(16, 3, 6, SpriteSheet.tiles);
	public static Sprite blueZombie = new Sprite(16, 4, 6, SpriteSheet.tiles);
	public static Sprite yellowZombie = new Sprite(16, 5, 6, SpriteSheet.tiles);
	public static Sprite mage = new Sprite(16, 1, 7, SpriteSheet.tiles);
	public static Sprite superMage = new Sprite(16, 2, 7, SpriteSheet.tiles);
	public static Sprite blueGhoul = new Sprite(16, 3, 7, SpriteSheet.tiles);
	public static Sprite redGhoul = new Sprite(16, 4, 7, SpriteSheet.tiles);
	public static Sprite greenGhoul = new Sprite(16, 5, 7, SpriteSheet.tiles);
	
	
// Collectables sprites
	public static Sprite scroll = new Sprite(16, 6, 7, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		pixels = new int[size * size];
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[size * size];
		setColor(color);
	}
	
	public void setColor(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
