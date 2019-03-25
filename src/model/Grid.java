package model;

public class Grid {
	
	Tile[][] tiles;

	public Grid (TileState[][] states) {
		
		tiles = new Tile[states.length][];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile[states[i].length];
			for (int j = 0; j < tiles[i].length; j++)
				tiles[i][j] = new Tile(this, new Coord2D(i, j), states[i][j]);
		}
	}
	
	public Tile getTile (Coord2D coords) {
		
		return this.tiles[coords.x][coords.y];
	}
	
	public static Grid read (String src) {
		
		String[] lines = src.split("\n");
		TileState[][] states = new TileState[lines.length][];
		for (int i = 0; i < states.length; i++) {
			states[i] = new TileState[lines[i].length()];
			for (int j = 0; j < states[i].length; j++)
				states[i][j] = TileState.charToTileState(lines[i].charAt(j));
		}
		return new Grid(states);
	}
	
	@Override
	public String toString () {
		
		String res = "";
		for (Tile[] line : tiles) {
			for (Tile t : line)
				res += t.toString();
			res += "\n";
		}
		return res;
	}
}
