package model;

/**
 * Entite devant etre recuperee par Pac-Man afin de completer le niveau
 * @author Fabien Morival
 */
public class PacDot extends Bonus {
	
	public final static int PACDOT_SCORE = 10;

	public PacDot(Tile tile) {
		
		super(tile);
	}

	@Override
	public void apply(Entity other) {

		other.getGrid().getScoreBoard().addScore(PACDOT_SCORE);
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
