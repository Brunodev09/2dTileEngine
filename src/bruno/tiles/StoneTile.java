package bruno.tiles;

import bruno.gfx.Screen;
import bruno.gfx.Sprite;

public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * 16, y * 16, this);
	}
	
	public boolean isSolid() {
		return true;
	}

}
