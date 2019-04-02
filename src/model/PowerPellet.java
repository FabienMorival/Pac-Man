package model;

/**
 * Entite consommable par Pac-Man pour rendre les fantomes lents et vulnerables
 * @author Fabien Morival
 */
public class PowerPellet extends Bonus {
	
	public final static int PP_SCORE = 50;

	public PowerPellet(Tile tile) {
		
		super(tile);
	}

	@Override
	public void apply(Entity other) {

		other.getGrid().getScoreBoard().addScore(PP_SCORE);
	}

	@Override
	public String getType() {

		return "power-pellet";
	}
	
	@Override
	public String toString () {
		
		return "o";
	}
}
