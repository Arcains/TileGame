package dev.simonhorat.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage player, grass, dirt, stone, tree;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		player = sheet.crop(0, 0, width, height);
		grass = sheet.crop(width, 0, width, height);
		dirt = sheet.crop(width*2, 0, width, height);
		stone = sheet.crop(width*3, 0, width, height);
		tree = sheet.crop(0, height, width, height);
	}
	
	

}
