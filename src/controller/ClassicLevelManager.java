package controller;

import java.util.Observable;
import java.util.Observer;

import console.GridView;
import loader.MapLoader;
import model.AlternativeBehaviour;
import model.Behaviour;
import model.Ghost;
import model.Grid;
import model.ManualBehaviour;
import model.PacMan;
import model.RandomBehaviour;
import model.ShortestPathBehaviour;
import model.Time;
import model.TimeBehaviour;
import model.Way;

public class ClassicLevelManager implements LevelManager, Observer {

	private Grid level;
	
	private Runnable gameOverEvent = () -> {};
	
	public ClassicLevelManager (Grid level) {
		
		this.level = level;
	}
	
	@Override
	public void start () {
		
		level.init();
		
		PacMan pacman = level.findPacMan();
		
		ManualBehaviour pacmanBehaviour = new ManualBehaviour();
		pacman.setBehaviour(pacmanBehaviour);
		pacman.addObserver(this);
		pacman.setBehaviour(new RandomBehaviour());
		
		AlternativeBehaviour blinkyBehaviour = new AlternativeBehaviour(new RandomBehaviour(), new ShortestPathBehaviour());
		blinkyBehaviour.setTimeBeforeSwitch(10, 15);
		blinkyBehaviour.setWeights(new float[] { 1, 2 });
		
		Behaviour pinkyBehaviour = new RandomBehaviour();

		Behaviour inkyBehaviour = new RandomBehaviour();

		Behaviour clydeBehaviour = new RandomBehaviour();
		
		Time.capture(level);
		Time.start();
		
		Ghost blinky = (Ghost) level.findEntityByName(MapLoader.BLINKY_NAME);
		blinky.setDefaultBehaviour(blinkyBehaviour);
		blinky.resetDefaultBehaviour();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Time.stop();
	}
	
	@Override
	public void onGameOver (Runnable action) {
		
		this.gameOverEvent = action;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (level.getScoreBoard().isGameOver())
			Time.stop();
		else
			level.reset();
	}
}
