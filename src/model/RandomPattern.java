package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Pattern se deplacement aleatoirement dans la grille
 * @author Fabien Morival
 */
public class RandomPattern implements Pattern {
	
	/**
	 * Chaos par defaut du deplacement
	 */
	private final static float DEFAULT_CHAOS = 0.1f;
	
	/**
	 * Generateur de nombres aleatoires
	 */
	Random RNG = new Random();
	
	/**
	 * Dernier deplacement effectue
	 * Celui-ci sera favorise afin de garder un deplacement coherent
	 */
	Way previous = this.randomWay();
	
	/**
	 * Pourcentage de preference du dernier deplacement
	 * Une valeur elevee rendra le deplacement plus chaotique
	 */
	private float chaos = DEFAULT_CHAOS;

	@Override
	public Way next(Actor a) {

		Way newWay;
		do {
			newWay = randomPossibleWay(a);
		} while (newWay == previous.opposite() && RNG.nextFloat() > chaos);
		return previous = newWay;
	}

	/**
	 * Deplacement tire aleatoirement parmi ceux possibles pour l'entite
	 * @param a entite a deplacer
	 * @return deplacement possible aleatoire
	 */
	public Way randomPossibleWay(Actor a) {

		List<Way> ways = new ArrayList<>();
		for (Way way : Way.values())
			if (a.canMove(way))
				ways.add(way);
		return ways.get(RNG.nextInt(ways.size()));
	}
	
	/**
	 * Deplacement aleatoire
	 * @return deplacement aleatoire
	 */
	private Way randomWay () {

		return Way.values()[RNG.nextInt(Way.values().length)];
	}
	
	/**
	 * Change le chaos du deplacement de l'entite
	 * @param chaos pourcentage de chaos du deplacement
	 */
	public void setChaos (float chaos) {
		
		this.chaos = chaos;
	}
}
