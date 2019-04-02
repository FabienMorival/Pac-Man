package model;

/**
 * Entite controllee par le joueur
 * @author Fabien Morival
 */
public class PacMan extends Actor {
	
	public final static String PACMAN_DEFAULT_NAME = "pac-man";
	
	public final static float DEFAULT_PACMAN_FREQUENCY = 0.3f;

	public PacMan(Tile tile) {
		
		super(tile);
		this.setFrequency(DEFAULT_PACMAN_FREQUENCY);
		// Appelle une fonction lorsque Pac-Man entre en collision avec un fantome
		this.onCollision("ghost", this::collideGhost);
	}
	
	/**
	 * Appele lorsque Pac-Man entre en collision avec un fantome
	 * @param ghost fantome entre en collision avec Pac-Man
	 */
	private void collideGhost (Entity ghost) {
		
		this.die();
	}
	
	private void die () {
		
		this.getGrid().getScoreBoard().loseStock();
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public String getType() {
		
		return "pac-man";
	}
	
	@Override
	public String toString () {
		
		return "@";
	}
}
