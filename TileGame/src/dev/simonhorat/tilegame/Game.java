package dev.simonhorat.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.simonhorat.tilegame.display.Display;
import dev.simonhorat.tilegame.gfx.Assets;
import dev.simonhorat.tilegame.gfx.ImageLoader;
import dev.simonhorat.tilegame.gfx.SpriteSheet;
import dev.simonhorat.tilegame.input.KeyManager;
import dev.simonhorat.tilegame.states.GameState;
import dev.simonhorat.tilegame.states.MenuState;
import dev.simonhorat.tilegame.states.States;

public class Game implements Runnable {

	private Display display;

	public int width, height;
	public String title;
	private boolean running = false;

	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private States gameState;
	private States menuState;
	
	//Input
	private KeyManager keyManager;


	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}

	private void init() {

		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		States.setState(gameState);
		menuState = new MenuState(this);
		
	}
	

	private void update() {
		
		keyManager.update();
		
		if (States.getState() != null)
			States.getState().update();


	}
	


	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null) {
			display.getCanvas().createBufferStrategy(1);
			return;
		}
		g = bs.getDrawGraphics();
		
		// Draw here
		g.clearRect(0, 0, width, height);
		
		// End of drawing
		
		if (States.getState() != null)
			States.getState().render(g);
		
		bs.show();
		g.dispose();

	}

	public void run() {

		init();
		
		
		// Führe "while" nur FPS x pro Sekunde aus
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();


		while (running) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if (delta >= 1) {
			update();
			render();
			delta--;
			}


		}

		stop();

	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
