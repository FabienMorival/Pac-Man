package controller;

import java.util.Observable;
import java.util.Observer;

import console.GridView;
import loader.MapLoader;
import model.Actor;
import model.AlternativeBehaviour;
import model.Behaviour;
import model.Entity;
import model.Ghost;
import model.Grid;
import model.ManualBehaviour;
import model.PacMan;
import model.RandomBehaviour;
import model.ShortestPathBehaviour;
import model.Way;
import time.Time;
import time.TimeBehaviour;
import time.Timer;

public class ClassicLevelManager implements LevelManager, Observer {

	private Grid level;
	
	private Runnable gameOverEvent = () -> {};
	
	private Behaviour pacmanBehaviour, blinkyBehaviour, pinkyBehaviour, inkyBehaviour, clydeBehaviour;
	
	public ClassicLevelManager (Grid level) {
		
		this.level = level;
		this.setup();
	}
	
	private void setup () {
		
		this.pacmanBehaviour = new ManualBehaviour();
		
		AlternativeBehaviour blinkyBehaviour = new AlternativeBehaviour(new RandomBehaviour(), new ShortestPathBehaviour());
		blinkyBehaviour.setTimeBeforeSwitch(10, 15);
		blinkyBehaviour.setWeights(new float[] { 1, 2 });
		this.blinkyBehaviour = blinkyBehaviour;
		
		this.pinkyBehaviour = new RandomBehaviour();
		
		this.inkyBehaviour = new RandomBehaviour();
		
		this.clydeBehaviour = new RandomBehaviour();
	}
	
	@Override
	public void start () {
		
		level.init();
		
		PacMan pacman = level.findPacMan();
		
		pacman.setBehaviour(pacmanBehaviour);
		pacman.addObserver(this);
		
		Time.capture(level);
		Time.start();

		this.setupLevel();
		
		try {
			Thread.sleep(16000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Time.stop();
	}
	
	private void setupBehaviour (Actor actor, Behaviour behaviour) {
		
		actor.setDefaultBehaviour(behaviour);
		actor.resetDefaultBehaviour();
	}
	
	private void setupLevel () {

		setupBehaviour((Actor) level.findEntityByName(MapLoader.BLINKY_NAME), blinkyBehaviour);
		setupGhostFreedom((Actor) level.findEntityByName(MapLoader.PINKY_NAME), pinkyBehaviour, 5);
		setupGhostFreedom((Actor) level.findEntityByName(MapLoader.INKY_NAME), inkyBehaviour, 6);
		setupGhostFreedom((Actor) level.findEntityByName(MapLoader.CLYDE_NAME), clydeBehaviour, 10);
	}
	
	private void setupGhostFreedom (Actor actor, Behaviour behaviour, float seconds) {

		new Timer(seconds, () -> actor.move(Way.N)).start();
		new Timer(seconds + Ghost.DEFAULT_GHOST_SPEED, () -> {
			actor.move(Way.N);
			setupBehaviour(actor, behaviour);
		}).start();
	}
	
	@Override
	public void onGameOver (Runnable action) {
		
		this.gameOverEvent = action;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (level.getScoreBoard().isGameOver())
			Time.stop();
		else {
			level.reset();
			this.setupLevel();
		}
	}
}
