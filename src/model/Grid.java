package model;

public class Grid {
	
	Tile[][] tiles;

	public Grid (TileState[][] states) {
		
		tiles = new Tile[states.length][];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile[states[i].length];
			for (int j = 0; j < tiles[i].length; j++)
				tiles[i][j] = new Tile(states[i][j]);
		}
	}
}
