package bruno.gfx;

import bruno.tiles.Tile;

public class Screen {

	public int width, height, xOffset, yOffset;
	public int[] pixels;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int x = 0; x < tile.sprite.SIZE; x++) {
			int xa = x + xp;
			for (int y = 0; y < tile.sprite.SIZE; y++) {
				int ya = y + yp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue; 
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int color = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				if (color != 0xffff00ff) pixels[xa + ya * width] = color;
			}
		}
	}
	
	public void renderMob(int xp, int yp, Sprite sprite, boolean xFlip, boolean yFlip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int x = 0; x < sprite.SIZE; x++) {
			int xa = x + xp;
			int xs = x;
			if (xFlip) xs = (sprite.SIZE - 1) - x;
			for (int y = 0; y < sprite.SIZE; y++) {
				int ya = y + yp;
				int ys = y;
				if (yFlip) ys = (sprite.SIZE - 1) - y;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue; 
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int color = sprite.pixels[xs + ys * sprite.SIZE];
				if (color != 0xffff00ff) pixels[xa + ya * width] = color;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
