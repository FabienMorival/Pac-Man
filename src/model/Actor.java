package model;

public abstract class Actor extends Entity {

	public Actor(Tile tile) {
		
		super(tile);
	}

	public void move (Way way) {
		
		if (!this.exists())
			return;
		this.changePosition(this.getPosition().go(way));
	}

	public boolean canMove (Way way) {
		
		if (!this.exists())
			return false;
		return this.getGrid().getTile(this.getPosition().go(way)).isTransparent();
	}
}
