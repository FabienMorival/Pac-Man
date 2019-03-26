package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Entite pouvant interagir avec les autres dans une grille
 * @author Fabien Morival
 */
public abstract class Entity {

	/**
	 * Case sur laquelle est presente l'objet
	 */
	private Tile tile;
	
	/**
	 * Evenements se produisant lorsque l'entite entre en collision avec d'autres
	 */
	private Map<String, Consumer<Entity>> collisionEvents = new HashMap<>();
	
	public Entity (Tile tile) {
		
		this.tile = tile;
		this.changePosition(tile.getPosition());
	}
	
	/**
	 * Case sur laquelle est presente l'objet
	 */
	public Tile getTile () {
		
		return this.tile;
	}
	
	/**
	 * Coordonnees de l'entite dans la grille
	 */
	public Coord2D getPosition () {
		
		return this.exists() ? this.tile.getPosition() : new Coord2D(-1, -1);
	}
	
	/**
	 * Grille a laquelle l'entite appartient
	 */
	public Grid getGrid () {
		
		return this.exists() ? this.tile.getGrid() : null;
	}
	
	/**
	 * Verifie si l'entite est toujours existante dans une grille
	 * @return true si l'entite est presente dans une grille
	 */
	public boolean exists () {
		
		return this.tile != null;
	}
	
	/**
	 * Supprime l'existence de l'entite de sa grille
	 */
	public void clear () {
		
		if (!this.exists())
			return;
		this.getGrid().entities.remove(this);
		this.tile = null;
	}
	
	/**
	 * Met a jour la position de l'entite
	 * Peut declencher des collisions
	 * @param pos nouvelle position de l'entite
	 */
	public void changePosition (Coord2D pos) {
		
		this.tile = this.getGrid().getTile(pos);
		List<Entity> others = this.getGrid().findEntitiesAtPosition(pos);
		for (Entity other : others)
			if (other != this) {
				this.collide(other);
				other.collide(this);
			}
	}
	
	/**
	 * Entre en collision avec une entite
	 * Peut declencher des evenements de collision
	 * @param e entite avec laquelle entrer en collision
	 */
	private void collide (Entity e) {
		
		if (this.collisionEvents.containsKey(e.getType()))
			this.collisionEvents.get(e.getType()).accept(e);
	}
	
	/**
	 * Prepare un evenement etant appele lorsque l'entite entre en collision avec une entite d'un type specifique
	 * @param type type des entites pour qui on veut declencher l'evenement lorsque l'objet entre en collision avec
	 * @param action evenement a declencher lorsque l'entite entre en collision
	 */
	public void onCollision (String type, Consumer<Entity> action) {
		
		this.collisionEvents.put(type, action);
	}
	
	public abstract String getType ();
}
