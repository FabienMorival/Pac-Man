package model;

public class Entity {

	private Tile tile;
	
	public Entity (Tile tile) {
		
		this.tile = tile;
	}
	
	public Tile getTile () {
		
		return this.tile;
	}
	
	public Coord2D getPosition () {
		
		return this.tile.getPosition();
	}
	
	public Grid getGrid () {
		
		return this.tile.getGrid();
	}
}
