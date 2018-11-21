package bruno.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public final int SIZE;
	public String path;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/SpriteSheet.png", 128);
	
	public SpriteSheet(String path, int size) {
		SIZE = size;
		this.path = path;
		pixels = new int[size * size];
		load();
	}
	
	private void load() {
		try {
			System.out.print("Trying to read SpriteSheet from file: " + path + "...");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println("Succeded!");
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			System.err.println("Exception!");
			System.exit(1);
		}
	}

}
