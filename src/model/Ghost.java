package model;

/**
 * Fantome poursuivant Pac-Man
 * @author Fabien Morival
 */
public class Ghost extends Actor {
	
	public final static float DEFAULT_GHOST_FREQUENCY = 0.33f;
	
	public Ghost(Tile tile) {
		
		super(tile);
		this.setFrequency(DEFAULT_GHOST_FREQUENCY);
	}
	
	public Ghost(Tile tile, Behaviour behaviour) {
		
		super(tile, behaviour);
		this.setFrequency(DEFAULT_GHOST_FREQUENCY);
	}

	@Override
	public String getType() {
		
		return "ghost";
	}
	
	@Override
	public String toString () {
		
		return "!";
	}
}
