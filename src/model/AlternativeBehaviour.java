package model;

import java.util.Random;

/**
 * Behaviour alternant entre différents sous-comportements
 * @author Fabien Morival
 */
public class AlternativeBehaviour implements Behaviour {

	
	/**
	 * Nombre d'iterations minimal par defaut avant de changer de comportement
	 */
	private final static float DEFAULT_MIN_BEFORE_ALT = 20;
	
	/**
	 * Nombre d'iterations maximal par defaut avant de changer de comportement
	 */
	private final static float DEFAULT_MAX_BEFORE_ALT = 30;
	
	/**
	 * Generateur de nombres aléatoires
	 */
	Random RNG = new Random();
	
	/**
	 * Sous-comportements entre lesquels alterner
	 */
	Behaviour[] comportements;
	
	/**
	 * Poids des comportements
	 * Un comportement avec un poids élevé durera plus longtemps
	 */
	float[] weights;
	
	/**
	 * Index du comportement actuel
	 */
	int index = 0;
	
	/**
	 * Nombre d'itérations sur le comportement actuel
	 */
	int timer = 0;
	
	/**
	 * Nombre d'iterations minimal avant de changer de comportement
	 */
	float minTimeBeforeSwitch = DEFAULT_MIN_BEFORE_ALT;
	
	/**
	 * Nombre d'iterations maximal avant de changer de comportement
	 */
	float maxTimeBeforeSwitch = DEFAULT_MAX_BEFORE_ALT;
	
	public AlternativeBehaviour (Behaviour[] comportements) {
		
		this.comportements = comportements;
		this.weights = new float[comportements.length];
		for (int i = 0; i < weights.length; i++)
			weights[i] = 1;
	}
	
	public AlternativeBehaviour (Behaviour p1, Behaviour p2) {
		
		this(new Behaviour[] { p1, p2 } );
	}

	@Override
	public Way next(Actor a) {

		Behaviour p = comportements[index];
		timer++;
		if (needToSwitch()) {
			timer = 0;
			index = (index + 1) % comportements.length;
		}
		return p.next(a);
	}
	
	/**
	 * Change les poids des comportements
	 * L'ordre du tableau correspond a l'ordre des comportements
	 * @param weights nouveaux poids a assigner aux comportements
	 */
	public void setWeights (float[] weights) {
		
		this.weights = weights;
	}
	
	/**
	 * Change le nombre d'iterations avant de changer de comportement
	 * @param min nombre d'iterations minimal avant de changer de comportement
	 * @param max nombre d'iterations maximal avant de changer de comportement
	 */
	public void setTimeBeforeSwitch (float min, float max) {
		
		this.minTimeBeforeSwitch = min;
		this.maxTimeBeforeSwitch = max;
	}
	
	/**
	 * Donne le comportement actuel
	 * @return comportement actuel
	 */
	private Behaviour currentBehaviour () {
		
		return comportements[index];
	}
	
	/**
	 * Donne le poids du comportement actuel
	 * @return poids du comportement actuel
	 */
	private float currentWeight () {
		
		return weights[index];
	}
	
	/**
	 * Verifie s'il est necessaire de changer de comportement
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
