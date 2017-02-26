package dev.simonhorat.tilegame.states;

import java.awt.Graphics;

import dev.simonhorat.tilegame.Game;
import dev.simonhorat.tilegame.entities.creatures.Player;

public class GameState extends States {
	
	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
		
	}

	@Override
	public void update() {
		player.update();
		
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}
