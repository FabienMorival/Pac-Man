package model;

public enum Way {

	NORTH (0, -1),
	SOUTH (0, 1),
	EAST (1, 0),
	WEST (-1, 0);
	
	public int x, y;
	
	private Way (int x, int y) {
		
		this.x = x;
		this.y = y;
	}
}
