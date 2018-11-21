package bruno.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import bruno.entity.Mage;
import bruno.entity.SuperMage;

public class Level4 extends Level {

	public Level4(String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {
		try {
			System.out.print("Trying to read level file from: " + path + "...");
			BufferedImage image = ImageIO.read(Level1.class.getResource(path));
			System.out.println("Succeded!");
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Exception!");
			System.exit(1);
		}
		int xa = 0; int ya = 0;
		for (int i = 0; i < 5; i++) {
			xa+= 100; ya+= 100;
			entities.add(new Mage(xa, ya));
		}
		for (int i = 0; i < 1; i++) {
			xa+= 10; ya+= 300;
			entities.add(new SuperMage(xa, ya));
		}
	}

}
