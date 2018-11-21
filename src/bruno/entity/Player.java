package bruno.entity;

import bruno.gfx.Screen;
import bruno.gfx.Sprite;
import bruno.input.Keyboard;


public class Player extends Mob {
	private Keyboard key;
	private Sprite sprite;
	private int speed = 2;
	public static int health = 100;
	public static boolean dead = false;
	
	public Player(Keyboard key) {
		this.key = key;
		sprite = Sprite.playerSprite;
	}
	
	public Player(int x, int y, Keyboard key) {
		Entity.x = x;
		Entity.y = y;
		this.key = key;
		sprite = Sprite.playerSprite;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		if (key.up) ya-= speed;
		if (key.down) ya+= speed;
		if (key.left) xa-= speed;
		if (key.right) xa+= speed;
		
		if(xa != 0 || ya != 0) move(xa, ya);

		if (health <= 0) {
			health = 0;
			dead = true;
		}
	}
	

	public void render(Screen screen) {
//		int xx = x - 16;
//		int yy = y - 16;
		screen.renderMob(x, y, sprite, xFlip, yFlip);
	}
}
