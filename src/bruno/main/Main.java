package bruno.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import bruno.entity.Player;
import bruno.gfx.Screen;
import bruno.input.Keyboard;
import bruno.level.Level;
import bruno.tiles.Tile;

@SuppressWarnings("serial")
public class Main extends Canvas implements Runnable {
	
	public static int scale = 3;
	public static int width = 800 / scale;
	public static int height = 600 / scale;
	public static String title = "Ludum Dare 30: Connected Worlds";
	
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private Keyboard key;
	private Level level;
	private Player player;
	
	private boolean running = false;
	private boolean menu = true;
	public static boolean win = false;
	
	public static int levelIn = 0;
	public static int scrolls = 0;
	public static int timer = 20000;
	
	public static int cx;
	public static int cy;
	
	private int musicTimer = 0;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	
	//Sounds
	public static Sound music = new Sound("res/music.wav");
	public static Sound hit = new Sound("res/hit.wav");
	public static Sound pick = new Sound("res/pick.wav");

	
	public Main() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		load();
	}
	
	private void load() {
		frame = new JFrame();
		screen = new Screen(width, height);
		key = new Keyboard();
		spawnLoader();
		player = new Player(cx, cy, key);
		levelLoader();
		music.play();
		musicLoader();
		
		addKeyListener(key);
	}
	
	private void musicLoader() {
		musicTimer++;
		if (musicTimer > 760) {
			music.play();
			musicTimer = 0;
			musicTimer++;
		}
	}
	
	private void levelLoader() {
		if (levelIn == 0) level = Level.level1;
		if (levelIn == 1) level = Level.level2;
		if (levelIn == 2) level = Level.level3;
		if (levelIn == 3) level = Level.level4;
		if (levelIn == 4) level = Level.level5;
		player.init(level);
	}
	
	private void spawnLoader() {
		if (levelIn == 0) cx = 50; cy = 10;
		if (levelIn == 1) cx = 50; cy = 10;
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Main Thread");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		requestFocus();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			render();

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println(updates + "ups, " + frames + "fps");
				frame.setTitle(title + " | " + "By BrunoDev");
			}
		}
		stop();
	}
	
	public void update() {
		if (!win || !Player.dead || !menu) musicLoader();
		spawnLoader();
		levelLoader();	
		Tile.init();
		key.update();
		if (!menu && !Player.dead) {
			player.update();
			level.update();
		}
		if (scrolls == 1) {
			levelIn = 1;
		} if (scrolls == 2) {
			levelIn = 2;
		} if (scrolls == 3) {
			levelIn = 3;
		} if (scrolls == 4) {
			levelIn = 4;
		} if (scrolls == 5) {
			levelIn = 5;
		} if (scrolls == 6) {
			win = true;
		}
		if (!Player.dead || !menu || !win) {
			timer--;
			if (timer <= 0) {
				timer = 20000;
				Player.dead = true;
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		int xCam = Player.x - screen.width / 2;
		int yCam = Player.y - screen.height / 2;
		if (!menu && !Player.dead && !win) {
			screen.clear();
			level.render(xCam, yCam, screen);
			player.render(screen);
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if (menu) {
			screen.clear();
			g.setColor(Color.black);
			g.drawRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.white);
			g.setFont(new Font("Verdana", 20, 20));
			g.drawString("Find the magical scroll in each level to continue.", screen.width / 2, screen.height / 2);
			g.drawString("Press 'Enter' to continue", screen.width / 2, screen.height + 300 / 2);
			g.drawString("Move with WASD/Arrow keys. Avoid monsters.", screen.width / 2, screen.height + 50 / 2);
			g.setColor(Color.green);
			g.drawString("Made in less than 48h for Ludum Dare 30 - By BrunoDev", screen.width / 2, screen.height + 790 / 2);
			if (key.enter) menu = false;
		}
		if (!menu && !Player.dead && !win) {
			g.setColor(Color.white);
			g.setFont(new Font("Verdana", 20, 20));			
			g.drawString("Health: " + Player.health, 10, 20);
			g.drawString("Time before world collapses: " + timer, 10, 600);
				if (levelIn == 0) g.drawString("State of the World: Stable", 500, 20);
				else if (levelIn == 1) g.drawString("State of the World: Unstable", 500, 20);
				else if (levelIn == 2) g.drawString("State of the World: Insane", 500, 20);
				else if (levelIn == 3) g.drawString("State of the World: Hell", 500, 20);
				else if (levelIn == 4) g.drawString("State of the World: ???", 500, 20);
		}
		if (win) {
			screen.clear();
			g.setColor(Color.white);
			g.drawRect(0, 0, getWidth(), getHeight());
			g.setFont(new Font("Verdana", 20, 20));
			g.drawString("Congratulations, you collected all the magical", screen.width / 2, screen.height / 2);
			g.drawString("scrolls, and declared peace on the connected worlds.", screen.width / 2, screen.height + 50 / 2);
		}
		if (Player.dead) {
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.red);
			g.setFont(new Font("Verdana", 20, 20));
			g.drawString("You are dead. Press 'X' to go back to the menu.", screen.width / 2, screen.height / 2);
			if (key.x) {
				Player.health = 100;
				Player.dead = false;
				levelIn = 0;
				scrolls = 0;
				menu = true;
			}
		}
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Main game = new Main();
		game.frame.setResizable(false);
		game.frame.setTitle(Main.title);
		game.frame.setIconImage(new ImageIcon("/icon.png").getImage());
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}

}
