package model;

/**
 * Entite controllee par le joueur
 * @author Fabien Morival
 */
public class PacMan extends Actor {

	public PacMan(Tile tile) {
		
		super(tile);
		// Appelle une fonction lorsque Pac-Man entre en collision avec un fantome
		this.onCollision("ghost", this::collideGhost);
	}
	
	/**
	 * Appele lorsque Pac-Man entre en collision avec un fantome
	 * @param ghost fantome entre en collision avec Pac-Man
	 */
	private void collideGhost (Entity ghost) {
		
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
