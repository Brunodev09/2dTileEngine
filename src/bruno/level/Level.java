package bruno.level;

import java.util.ArrayList;
import java.util.List;

import bruno.entity.BlueSnake;
import bruno.entity.RedSnake;
import bruno.entity.Entity;
import bruno.gfx.Screen;
import bruno.tiles.Tile;

public class Level {
	
	public int x, y, width, height;
	public int[] tiles;
	public String path;
	private int tile_size = 16;
	
	protected List<Entity> entities = new ArrayList<Entity>();
	
	public static Level level1 = new Level1("/level1.png");
	public static Level level2 = new Level2("/level2.png");
	public static Level level3 = new Level3("/level3.png");
	public static Level level4 = new Level4("/level4.png");
	public static Level level5 = new Level5("/level5.png");
	
	public Level(String path) {
		loadLevel(path);
	}
	
	public Level(int x, int y) {
		generateLevel();
	}
	
	private void generateLevel() {
	}
	
	protected void loadLevel(String path) {
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll / tile_size;
		int x1 = (xScroll + screen.width + tile_size) / tile_size;
		int y0 = yScroll / tile_size;
		int y1 = (yScroll + screen.height + tile_size) / tile_size;
		
		for (int x = x0; x < x1; x++) {
			for (int y = y0; y < y1; y++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		if (RedSnake.health < 0 || BlueSnake.health < 0) {
			RedSnake.health = 0;
			BlueSnake.health = 0;
			for (int i = 0; i < 1; i++){
				entities.remove(i);
				RedSnake.health = 100;
				BlueSnake.health = 100;
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.waterTile;
		if (tiles[x + y * width] == 0xff0026FF) return Tile.waterTile;
		if (tiles[x + y * width] == 0xff808080) return Tile.stoneTile;
		if (tiles[x + y * width] == 0xff4CFF00) return Tile.grassTile;
		if (tiles[x + y * width] == 0xff7f3300) return Tile.dirtTile;
		if (tiles[x + y * width] == 0xff267f00) return Tile.flowerTile;
		if (tiles[x + y * width] == 0xffffffff) return Tile.warpTile;
		if (tiles[x + y * width] == 0xff007f46) return Tile.grass2Tile;
		if (tiles[x + y * width] == 0xffff0000) return Tile.lavaTile;
		if (tiles[x + y * width] == 0xff7f6A00) return Tile.scrollTile;
		return Tile.grassTile;
	}

}
