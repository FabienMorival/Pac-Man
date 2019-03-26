package model;

import java.util.Random;

/**
 * Pattern alternant entre différents sous-patterns
 * @author Fabien Morival
 */
public class AlternativePattern implements Pattern {

	
	/**
	 * Nombre d'iterations minimal par defaut avant de changer de pattern
	 */
	private final static float DEFAULT_MIN_BEFORE_ALT = 20;
	
	/**
	 * Nombre d'iterations maximal par defaut avant de changer de pattern
	 */
	private final static float DEFAULT_MAX_BEFORE_ALT = 30;
	
	/**
	 * Generateur de nombres aléatoires
	 */
	Random RNG = new Random();
	
	/**
	 * Sous-patterns entre lesquels alterner
	 */
	Pattern[] patterns;
	
	/**
	 * Poids des patterns
	 * Un pattern avec un poids élevé durera plus longtemps
	 */
	float[] weights;
	
	/**
	 * Index du pattern actuel
	 */
	int index = 0;
	
	/**
	 * Nombre d'itérations sur le pattern actuel
	 */
	int timer = 0;
	
	/**
	 * Nombre d'iterations minimal avant de changer de pattern
	 */
	float minTimeBeforeSwitch = DEFAULT_MIN_BEFORE_ALT;
	
	/**
	 * Nombre d'iterations maximal avant de changer de pattern
	 */
	float maxTimeBeforeSwitch = DEFAULT_MAX_BEFORE_ALT;
	
	public AlternativePattern (Pattern[] patterns) {
		
		this.patterns = patterns;
		this.weights = new float[patterns.length];
		for (int i = 0; i < weights.length; i++)
			weights[i] = 1;
	}
	
	public AlternativePattern (Pattern p1, Pattern p2) {
		
		this(new Pattern[] { p1, p2 } );
	}

	@Override
	public Way next(Actor a) {

		Pattern p = patterns[index];
		timer++;
		if (needToSwitch()) {
			timer = 0;
			index = (index + 1) % patterns.length;
		}
		return p.next(a);
	}
	
	/**
	 * Change les poids des patterns
	 * L'ordre du tableau correspond a l'ordre des patterns
	 * @param weights nouveaux poids a assigner aux patterns
	 */
	public void setWeights (float[] weights) {
		
		this.weights = weights;
	}
	
	/**
	 * Change le nombre d'iterations avant de changer de pattern
	 * @param min nombre d'iterations minimal avant de changer de pattern
	 * @param max nombre d'iterations maximal avant de changer de pattern
	 */
	public void setTimeBeforeSwitch (float min, float max) {
		
		this.minTimeBeforeSwitch = min;
		this.maxTimeBeforeSwitch = max;
	}
	
	/**
	 * Donne le pattern actuel
	 * @return pattern actuel
	 */
	private Pattern currentPattern () {
		
		return patterns[index];
	}
	
	/**
	 * Donne le poids du pattern actuel
	 * @return poids du pattern actuel
	 */
	private float currentWeight () {
		
		return weights[index];
	}
	
	/**
	 * Verifie s'il est necessaire de changer de pattern
	 * Si le nombre d'iteration se situe entre le minimum et le maximum d'iterations, cela est determine aleatoirement
	 * @return true s'il est necessaire de changer
	 */
	private boolean needToSwitch () {
		
		float min = minTimeBeforeSwitch * currentWeight();
		float max = maxTimeBeforeSwitch * currentWeight();
		if (timer <= min)
			return false;
		if (timer + RNG.nextFloat() * (max - min) > max)
			return true;
		return false;
	}
}
