package model;

/**
 * Directions possibles de deplacement des personnages
 * @author Fabien Morival
 */
public enum Way {
	
	/**
	* Deplacement vers le haut (0, -1).
	*/
	N (0, -1),
	
	/**
	* Deplacement vers le bas (0, 1).
	*/
	S (0, 1),
	
	/**
	* Deplacement vers la droite (1, 0).
	*/
	E (1, 0),
	
	/**
	* Deplacement vers la gauche (-1, 0).
	*/
	W (-1, 0);
	
	/**
	* Nombre de cases horizontales.
	*/
	public int x;
	
	/**
	* Nombre de cases verticales.
	*/
	public int y;
	
	private Way (int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Donne le deplacement oppose.
	 * @return deplacement oppose au deplacement en cours
	 */
	public Way opposite () {
		
		switch (this) {
		case N: return S;
		case S: return N;
		case E: return W;
		case W: return E;
		default : return null;
		}
	}
	
	/**
	* Tableau des 4 déplacements cardinaux
	* @return tableau des 4 deplacements
	*/
	public static Way[] cardinal () {
		
		return new Way[]{N, S, W, E};
	}
	
	@Override
	public String toString () {

		return "(" + x + ", " + y + ")";
	}
}
