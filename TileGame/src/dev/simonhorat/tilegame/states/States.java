package dev.simonhorat.tilegame.states;

import java.awt.Graphics;

import dev.simonhorat.tilegame.Game;

public abstract class States {
	
	private static States currentState = null;
	
	public static void setState(States states) {
		currentState = states;
	}
	
	public static States getState() {
		return currentState;
	}
	
	
	
	//CLASS
	
	protected Game game;
	
	public States(Game game){
		this.game = game;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);

}
