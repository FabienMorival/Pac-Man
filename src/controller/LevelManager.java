package controller;

public interface LevelManager {
	
	void start ();
	
	void onGameOver (Runnable action);
}
