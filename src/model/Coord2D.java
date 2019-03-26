package model;

/**
 * Coordonnees bidimensionnelles
 * @author Fabien Morivals
 */
public class Coord2D {

	/**
	 * Coordonnee en abscisse
	 */
	public int x;
	
	/**
	 * Coordonnee en ordonnee
	 */
	public int y;
	
	public Coord2D (int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Coordonnees obtenues en suivant une direction
	 * @param way direction a suivre
	 * @return nouvelles coordonnees
	 */
	public Coord2D go (Way way) {
		
		return new Coord2D(x + way.x, y + way.y);
	}
	
	/**
	 * Distance entiere separant deux coordonnees en evitant les diagonales
	 * @param other coordonnees avec lesquelles mesurer la distance
	 * @return distance de manhattan entre les coordonnees
	 */
	public int distanceManhattan (Coord2D other) {
		
		return Math.abs(x-other.x) + Math.abs(y-other.y);
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
