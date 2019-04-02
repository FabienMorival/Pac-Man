package model;

/**
 * Comportement par defaut n'effectuant aucun mouvement
 * @author Fabien Morival
 */
public class StandingBehaviour implements Behaviour {
	
	@Override
	public Way next(Actor a) {

		return null;
	}
}
