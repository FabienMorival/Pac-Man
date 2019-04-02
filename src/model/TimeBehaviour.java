package model;

/**
 * Comportement performant une action au cours du temps. Doit être capturé par {@link Time}
 * pour être notifié.
 * 
 * @author Fabien
 */
public interface TimeBehaviour {

	/**
	 * Fonction appelée à chaque frame par {@link Time}.
	 */
	public void update();
}
