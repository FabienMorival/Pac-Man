package model;

/**
 * Comportement par defaut n'effectuant aucun mouvement
 * @author Fabien Morival
 */
public class ManualBehaviour implements Behaviour {
	
	private Way input = null;
	
	@Override
	public Way next(Actor a) {

		return input;
	}
	
	public void inputDirection (Way dir) {
		
		this.input = dir;
	}
}
