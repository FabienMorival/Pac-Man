package model;

/**
 * Comportement d'une entite regissant ses deplacements
 * @author Fabien Morival
 */
public interface Behaviour {

	/**
	 * Prochain deplacement de l'entite
	 * @param e entite a deplacer
	 * @return deplacement a effectuer
	 */
	public Way next (Actor e);
}
