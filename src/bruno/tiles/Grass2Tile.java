package bruno.tiles;

import bruno.gfx.Screen;
import bruno.gfx.Sprite;

public class Grass2Tile extends Tile {

	public Grass2Tile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * 16, y * 16, this);
	}

}
