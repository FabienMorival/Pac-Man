package model;

/**
 * Etats d'une case
 * @author Fabien Morival
 */
public enum TileState {
	
	CORRIDOR (true),
	WALL (false),
	DOOR (false);
	
	public boolean transparent;
	
	private TileState (boolean transparent) {
		
		this.transparent = transparent;
	}
}