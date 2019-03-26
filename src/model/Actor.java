package model;

/**
 * Entite capable de se mouvoir independamment dans la grille
 * @author Fabien Morival
 */
public abstract class Actor extends Entity {

	public Actor(Tile tile) {
		
		super(tile);
	}

	/**
	 * Deplace l'entite dans une direction
	 * @param way direction dans laquelle deplacer l'entite
	 */
	public void move (Way way) {
		
		if (!this.exists())
			return;
		this.changePosition(this.getPosition().go(way));
	}

	/**
	 * Verifie si l'entite peut se deplacer dans une direction
	 * @param way direction dans laquelle deplacer l'entite
	 * @return true si l'entite peut se deplacer dans cette direction
	 */
	public boolean canMove (Way way) {
		
		if (!this.exists())
			return false;
		return this.getGrid().getTile(this.getPosition().go(way)).isTransparent();
	}
}
