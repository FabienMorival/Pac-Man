package model;

import java.util.ArrayList;
import java.util.List;

import time.TimeBehaviour;

/**
 * Grille de jeu avec les cases et entites dans le niveau
 * @author Fabien Morival
 */
public class Grid implements TimeBehaviour {
	
	private Tile[][] tiles;
	private List<Entity> entities = new ArrayList<>();
	private List<Spawner> spawners = new ArrayList<>();
	private ScoreBoard scoreBoard = new ScoreBoard();

	public Grid (TileState[][] states) {
		
		tiles = new Tile[states.length][];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile[states[i].length];
			for (int j = 0; j < tiles[i].length; j++)
				tiles[i][j] = new Tile(this, new Coord2D(j, i), states[i][j]);
		}
	}
	
	public int getX () {
		
		return this.tiles.length > 0 ? this.tiles[0].length : 0;
	}
	
	public int getY () {

		return this.tiles.length;
	}
	
	public Tile getTile (Coord2D coords) {
		
		return this.tiles[coords.y][coords.x];
	}
	
	public Tile getTile (int x, int y) {
		
		return this.getTile(new Coord2D(x, y));
	}
	
	public List<Entity> getEntities () {
		
		return new ArrayList<>(this.entities);
	}
	
	public void addSpawner (Spawner s) {
		
		this.spawners.add(s);
	}
	
	public void addEntity (Entity e) {
		
		this.entities.add(e);
	}
	
	public ScoreBoard getScoreBoard () {
		
		return this.scoreBoard;
	}
	
	public void init () {
		
		for (Spawner s : spawners)
			s.spawn();
	}
	
	public void clear () {
		
		List<Entity> toErase = this.findEntitiesByType("ghost");
		toErase.add(this.findPacMan());
		for (Entity e : toErase)
			e.clear();
	}
	
	public void reset () {
		
		this.clear();
		this.init();
	}
	
	public void clearEntity (Entity e) {
		
		this.entities.remove(e);
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
	
	public Entity findEntityByName (String name) {
		
		for (Entity e : entities)
			if (e.isNamed() && e.getName().equals(name))
				return e;
		return null;
	}
	
	public PacMan findPacMan () {
		
		return (PacMan) this.findEntityByName(PacMan.PACMAN_DEFAULT_NAME);
	}
	
	public int distanceBetween (Coord2D from, Coord2D to) {
		
		return 1;
	}
	
	public void update () {
		
		for (Entity e : this.getEntities())
			if (e.exists())
				e.update();
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
