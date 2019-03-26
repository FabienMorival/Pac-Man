package model;

public abstract class Bonus extends Entity {

	public Bonus(Tile tile) {
		
		super(tile);
		this.onCollision("pac-man", this::consume);
	}

	public void consume (Entity other) {
		
		this.apply(other);
		this.clear();
	}
	
	public abstract void apply (Entity other);
}
