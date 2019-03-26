package model;

/**
 * Bonus immobile consommable par Pac-Man
 * @author Fabien Morival
 */
public abstract class Bonus extends Entity {

	public Bonus(Tile tile) {
		
		super(tile);
		// Le bonus est consomme lorsqu'il entre en collision avec Pac-Man
		this.onCollision("pac-man", this::consume);
	}

	/**
	 * Fonction appellee au contact avec Pac-Man
	 * Detruit le bonus et applique ses effets
	 * @param other entite consommant le bonus
	 */
	public void consume (Entity other) {
		
		this.apply(other);
		this.clear();
	}
	
	public abstract void apply (Entity other);
}
