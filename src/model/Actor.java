package model;

/**
 * Entite capable de se mouvoir independamment dans la grille
 * @author Fabien Morival
 */
public abstract class Actor extends Entity {
	
	private float frequency = 1;
	private float nextAction = 1;
	
	private Behaviour defaultBehaviour = new StandingBehaviour();
	private Behaviour behaviour = defaultBehaviour;

	public Actor(Tile tile) {
		
		super(tile);
	}
	
	public Actor(Tile tile, Behaviour behaviour) {
		
		this(tile);
		this.defaultBehaviour = behaviour;
		this.resetDefaultBehaviour();
	}

	/**
	 * Deplace l'entite dans une direction
	 * @param way direction dans laquelle deplacer l'entite
	 */
	public void move (Way way) {
		
		if (!this.exists() || way == null)
			return;
		this.changePosition(this.getPosition().go(way));
	}

	/**
	 * Verifie si l'entite peut se deplacer dans une direction
	 * @param way direction dans laquelle deplacer l'entite
	 * @return true si l'entite peut se deplacer dans cette direction
	 */
	public boolean canMove (Way way) {
		
		if (!this.exists())
			return false;
		return this.getGrid().getTile(this.getPosition().go(way)).isTransparent();
	}
	
	protected void setFrequency (float frequency) {
		
		this.frequency = frequency;
		this.nextAction = frequency;
	}
	
	public void setBehaviour (Behaviour behaviour) {
		
		this.behaviour = behaviour;
	}
	
	public void setDefaultBehaviour (Behaviour behaviour) {

		this.defaultBehaviour = behaviour;
	}
	
	public void resetDefaultBehaviour () {
		
		this.behaviour = this.defaultBehaviour;
	}
	
	@Override
	public void update () {
		
		nextAction -= Time.getDeltaTime();
		if (nextAction <= 0) {
			this.act();
			nextAction = frequency;
		}
	}
	
	public void act () {
		
		Way way = behaviour.next(this);
		if (this.canMove(way))
			this.move(way);
	}
}
