package model;

import java.util.Observable;

/**
 * Tableau contenant les scores du joueur sur une partie
 * @author Fabien Morival
 */
public class ScoreBoard {
	
	public final static int DEFAULT_STOCK_COUNT = 3;
	public final static int SCORE_EXTRA_STOCK = 10000;
	public final static int DEFAULT_SCORE = 0;

	private int stocks = DEFAULT_STOCK_COUNT;
	private int score = DEFAULT_SCORE;
	
	public void addScore (int score) {
		
		this.score += score;
		// Si on depasse un certain score, on obtient une vie supplementaire
		if (this.score - score % SCORE_EXTRA_STOCK > this.score % SCORE_EXTRA_STOCK)
			this.addExtraStock();
	}
	
	public int getScore () {
		
		return this.score;
	}
	
	public int getStockCount () {
		
		return this.stocks;
	}
	
	public void addExtraStock () {
		
		this.stocks++;
	}
	
	public void loseStock () {
		
		this.stocks--;
	}
	
	public boolean isGameOver () {
		
		return this.stocks <= 0;
	}
}
