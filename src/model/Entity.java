package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Entity {

	private Tile tile;
	
	private Map<String, Consumer<Entity>> collisionEvents = new HashMap<>();
	
	public Entity (Tile tile) {
		
		this.tile = tile;
		this.changePosition(tile.getPosition());
	}
	
	public Tile getTile () {
		
		return this.tile;
	}
	
	public Coord2D getPosition () {
		
		return this.exists() ? this.tile.getPosition() : new Coord2D(-1, -1);
	}
	
	public Grid getGrid () {
		
		return this.exists() ? this.tile.getGrid() : null;
	}
	
	public boolean exists () {
		
		return this.tile != null;
	}
	
	public void clear () {
		
		if (!this.exists())
			return;
		this.getGrid().entities.remove(this);
		this.tile = null;
	}
	
	public void changePosition (Coord2D pos) {
		
		this.tile = this.getGrid().getTile(pos);
		List<Entity> others = this.getGrid().findEntitiesAtPosition(pos);
		for (Entity other : others)
			if (other != this) {
				this.collide(other);
				other.collide(this);
			}
	}
	
	private void collide (Entity e) {
		
		if (this.collisionEvents.containsKey(e.getType()))
			this.collisionEvents.get(e.getType()).accept(e);
	}
	
	public void onCollision (String type, Consumer<Entity> action) {
		
		this.collisionEvents.put(type, action);
	}
	
	public abstract String getType ();
}
