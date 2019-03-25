package model;

public class Tile {
	
	private TileState state;
	
	public Tile (TileState state) {
		
		this.state = state;
	}
	
	public TileState getState () {
		
		return this.state;
	}
	
	public boolean isTransparent () {
		
		return this.state.isTransparent();
	}
}
