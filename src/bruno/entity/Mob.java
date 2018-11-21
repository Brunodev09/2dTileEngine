package bruno.entity;
import java.util.Random;

import bruno.gfx.Screen;
import bruno.gfx.Sprite;
import bruno.main.Main;


public class Mob extends Entity {
	protected Sprite sprite;
	protected Main game;
	protected int dir = 0;
	protected boolean walking = false;
	protected boolean xFlip;
	protected boolean yFlip;
	private int tile_size = 16;
	public static boolean scrollCollision = true;
	private Random rand = new Random();

	
	public void move(int xa, int ya) {
		
		
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) {
			dir = 1;
			xFlip = false;
		} 
		if (xa < 0) {
			dir = 3;
			xFlip = true;
		}
		if (ya > 0) {
			dir = 2;
		}
		if (ya < 0) {
			dir = 0;
		}
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
		
		if (collisionTeleport(xa, ya)) {
			Main.levelIn++;
			if (Main.levelIn >= 5) {
				Main.levelIn = 5;
			}
		}
		
		if (collisionLava(xa, ya)) {
			Player.health--;
			if (rand.nextInt(8) == 1) Main.hit.play();
		}
		
		if (collisionScroll(xa, ya)) {
			Main.scrolls++;
			Main.pick.play();
			scrollCollision = true;
		} else scrollCollision = false;
	}
	
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
	protected void shoot(int x, int y, double dir) {
		
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 12) / tile_size;
			int yt = ((y + ya) + c / 2 * 12) / tile_size;
			if (level.getTile(xt, yt).isSolid()) solid = true;
		}
		return solid;
	}
	
	private boolean collisionTeleport(int xa, int ya) {
		boolean tel = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 12) / tile_size;
			int yt = ((y + ya) + c / 2 * 12) / tile_size;
			if (level.getTile(xt, yt).isTeleport()) tel = true;
		}
		return tel;
	}
	
	private boolean collisionLava(int xa, int ya) {
		boolean hurt = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 12) / tile_size;
			int yt = ((y + ya) + c / 2 * 12) / tile_size;
			if (level.getTile(xt, yt).isHurt()) hurt = true;
		}
		return hurt;
	}
	
	private boolean collisionScroll(int xa, int ya) {
		boolean scroll = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 12) / tile_size;
			int yt = ((y + ya) + c / 2 * 12) / tile_size;
			if (level.getTile(xt, yt).isScroll()) scroll = true;
		}
		return scroll;
	}
}
