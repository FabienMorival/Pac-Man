package model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	
	Tile[][] tiles;
	List<Entity> entities = new ArrayList<>();

	public Grid (TileState[][] states) {
		
		tiles = new Tile[states.length][];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile[states[i].length];
			for (int j = 0; j < tiles[i].length; j++)
				tiles[i][j] = new Tile(this, new Coord2D(j, i), states[i][j]);
		}
	}
	
	public Tile getTile (Coord2D coords) {
		
		return this.tiles[coords.y][coords.x];
	}
	
	public List<Entity> getEntities () {
		
		return new ArrayList<>(this.entities);
	}
	
	public List<Entity> findEntitiesAtPosition (Coord2D pos) {
		
		List<Entity> eap = new ArrayList<>();
		for (Entity e : entities)
			if (e.getPosition().equals(pos))
				eap.add(e);
		return eap;
	}
	
	public List<Entity> findEntitiesByType (String type) {
		
		List<Entity> eap = new ArrayList<>();
		for (Entity e : entities)
			if (e.getType().equals(type))
				eap.add(e);
		return eap;
	}
	
	public PacMan findPacMan () {
		
		List<Entity> pacmans = this.findEntitiesByType("pac-man");
		return pacmans.isEmpty() ? null : (PacMan) pacmans.get(0);
	}
	
	public int distanceBetween (Coord2D from, Coord2D to) {
		
		return 1;
	}
	
	public static Grid read (String src) {
		
		String[] lines = src.split("\n");
		TileState[][] states = new TileState[lines.length][];
		
		// Adding tiles
		for (int i = 0; i < states.length; i++) {
			states[i] = new TileState[lines[i].length()];
			for (int j = 0; j < states[i].length; j++) {
				states[i][j] = TileState.charToTileState(lines[i].charAt(j));
			}
		}
		// Creating the grid
		Grid g = new Grid(states);
		
		// Adding entities
		for (int i = 0; i < g.tiles.length; i++) {
			for (int j = 0; j < g.tiles[i].length; j++) {
				char c = lines[i].charAt(j);
				switch (c) {
				case 'B':
					g.entities.add(new Ghost(g.tiles[i][j], new AlternativePattern(new RandomPattern(), new ShortestPathPattern())));
					break;
				case 'P': break;
				case 'I': break;
				case 'C': break;
				case '@':
					g.entities.add(new PacMan(g.tiles[i][j]));
					break;
				case '.':
					g.entities.add(0, new PacDot(g.tiles[i][j]));
					break;
				case 'o': break;
				}
			}
		}
		return g;
	}
	
	@Override
	public String toString () {
		
		String res = "";
		String[] tl = new String[tiles.length];
		for (int i = 0; i < tiles.length; i++) {
			tl[i] = "";
			for (Tile t : tiles[i])
				tl[i] += t.toString();
		}
		for (Entity e : entities) {
			int x = e.getPosition().x, y = e.getPosition().y;
			tl[y] = tl[y].substring(0,x) + e.toString() + tl[y].substring(x+1);
		}
		for (String t : tl)
			res += t + "\n";
		return res;
	}
}
