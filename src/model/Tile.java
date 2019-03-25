package model;

public class Tile {
	
	private Grid grid;
	private TileState state;
	private Coord2D coords;
	
	public Tile (Grid grid, Coord2D coords, TileState state) {
		
		this.grid = grid;
		this.coords = coords;
		this.state = state;
	}
	
	public Grid getGrid () {
		
		return this.grid;
	}
	
	public TileState getState () {
		
		return this.state;
	}
	
	public Coord2D getPosition () {
		
		return this.coords;
	}
	
	public boolean isTransparent () {
		
		return this.state.transparent;
	}
	
	@Override
	public String toString () {
		
		return this.state.toString();
	}
}
