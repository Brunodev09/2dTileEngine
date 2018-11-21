package bruno.entity;

import java.util.Random;

import bruno.gfx.Screen;
import bruno.gfx.Sprite;
import bruno.main.Main;

public class GreenZombie extends Mob {
	private int speed;
	private int xz, yz;
	private Sprite sprite;
	public static int health = 100;
	private Random rand = new Random();
	
	public GreenZombie(int xz, int yz) {
		this.xz = xz;
		this.yz = yz;
		speed = 1;
		sprite = Sprite.greenZombie;
	}
	
	public void update() {
		if (xz <= Player.x) {
			xz+= speed;
			xFlip = false;
		}
		if (xz >= Player.x) {
			xz-= speed;
			xFlip = true;
		}
		if (yz <= Player.y) yz+= speed;
		if (yz >= Player.y) yz-= speed;
		
		if (xz == Player.x && yz == Player.y) {
			health-= 10;
			Player.health-=5;
			if (rand.nextInt(8) == 1) Main.hit.play();
		}
	}
	
	public void render(Screen screen) {
		screen.renderMob(xz, yz, sprite, xFlip, yFlip);
	}
}
