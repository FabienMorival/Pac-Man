package model;

/**
 * Comportement performant une action au cours du temps. Doit �tre captur� par {@link Time}
 * pour �tre notifi�.
 * 
 * @author Fabien
 */
public interface TimeBehaviour {

	/**
	 * Fonction appel�e � chaque frame par {@link Time}.
	 */
	public void update();
}
