package model;

public class Ghost extends Actor {

	private Pattern pattern;
	
	public Ghost(Tile tile, Pattern pattern) {
		
		super(tile);
		this.pattern = pattern;
	}
	
	public void act () {
		
		this.move(pattern.next(this));
	}

	@Override
	public String getType() {
		
		return "ghost";
	}
	
	@Override
	public String toString () {
		
		return "!";
	}
}
