package model;

public class Coord2D {

	public int x, y;
	
	public Coord2D (int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public Coord2D go (Way way) {
		
		return new Coord2D(x + way.x, y + way.y);
	}
	
	@Override
	public String toString () {
		
		return "[" + x + ", " + y + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord2D other = (Coord2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
