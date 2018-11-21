package bruno.entity;

import java.util.Random;
import bruno.gfx.Screen;
import bruno.level.Level;

public abstract class Entity {
	public static int x;
	public static int y;
	private boolean removed = false;
	public Level level;
	protected Random rand = new Random();

	public void init(Level level) {
		this.level = level;
	}

	public void update() {
	}

	public void render(Screen screen) {
	}

	public boolean isRemoved() {
		return removed;
	}
	
}
