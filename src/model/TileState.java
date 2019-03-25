package model;

public enum TileState {
	
	CORRIDOR (true),
	WALL (false);
	
	private boolean transparent;
	
	private TileState (boolean transparent) {
		
		this.transparent = transparent;
	}
	
	public boolean isTransparent () {
		
		return this.transparent;
	}
}