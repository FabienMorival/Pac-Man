package model;

/**
 * Fantome poursuivant Pac-Man
 * @author Fabien Morival
 */
public class Ghost extends Actor {

	/**
	 * Pattern regissant les mouvements du fantome
	 */
	private Pattern pattern;
	
	public Ghost(Tile tile, Pattern pattern) {
		
		super(tile);
		this.pattern = pattern;
	}
	
	/**
	 * Permet au fantome d'effectuer un deplacement
	 */
	public void act () {
		
		this.move(pattern.next(this));
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
