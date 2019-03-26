package model;

/**
 * Entite devant etre recuperee par Pac-Man afin de completer le niveau
 * @author Fabien Morival
 */
public class PacDot extends Bonus {

	public PacDot(Tile tile) {
		
		super(tile);
	}

	@Override
	public void apply(Entity other) {

	}

	@Override
	public String getType() {

		return "pac-dot";
	}
	
	@Override
	public String toString () {
		
		return ".";
	}
}
