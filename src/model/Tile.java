package model;

/**
 * Case composant une grille
 * @author Fabien Morival
 */
public class Tile {
	
	/**
	 * Grille a laquelle appartient la case
	 */
	private Grid grid;
	
	/**
	 * Etat de la case
	 */
	private TileState state;
	
	/**
	 * Coordonnees de la case dans la grille
	 */
	private Coord2D coords;
	
	public Tile (Grid grid, Coord2D coords, TileState state) {
		
		this.grid = grid;
		this.coords = coords;
		this.state = state;
	}
	
	/**
	 * Grille a laquelle appartient la case
	 */
	public Grid getGrid () {
		
		return this.grid;
	}

	/**
	 * Etat de la case
	 */
	public TileState getState () {
		
		return this.state;
	}
	
	/**
	 * Coordonnees de la case dans la grille
	 */
	public Coord2D getPosition () {
		
		return this.coords;
	}
	
	/**
	 * Verifie si la case est traversable par une entite
	 */
	public boolean isTransparent () {
		
		return this.state.transparent;
	}
	
	@Override
	public String toString () {
		
		switch (this.state) {
		case WALL: return "X";
		case DOOR: return "_";
		default: return " ";
		}
	}
}
