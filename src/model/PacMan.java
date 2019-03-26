package model;

public class PacMan extends Actor {

	public PacMan(Tile tile) {
		
		super(tile);
		this.onCollision("ghost", this::collideGhost);
	}
	
	private void collideGhost (Entity ghost) {
		
	}

	@Override
	public String getType() {
		
		return "pac-man";
	}
	
	@Override
	public String toString () {
		
		return "@";
	}
}
