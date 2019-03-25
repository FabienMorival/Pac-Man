package model;

public enum TileState {
	
	CORRIDOR (' ', true),
	WALL ('X', false),
	DOOR ('_', false);
	
	public char symbol;
	public boolean transparent;
	
	private TileState (char symbol, boolean transparent) {
		
		this.symbol = symbol;
		this.transparent = transparent;
	}
	
	@Override
	public String toString () {
		
		return String.valueOf(this.symbol);
	}
	
	public static TileState charToTileState (char symbol) {
		
		for (TileState ts : TileState.values())
			if (ts.symbol == symbol)
				return ts;
		return TileState.CORRIDOR;
	}
}