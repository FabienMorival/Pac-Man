package model;

import java.util.function.Consumer;

public class Spawner {

	private Tile tile;
	
	private Consumer<Tile> pop;
	
	public Spawner (Tile tile, Consumer<Tile> pop) {
		
		this.tile = tile;
		this.pop = pop;
	}
	
	public void spawn () {
		
		this.pop.accept(tile);
	}
}
