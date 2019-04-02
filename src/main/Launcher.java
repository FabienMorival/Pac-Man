package main;

import java.io.IOException;

import console.GridView;
import controller.ClassicLevelManager;
import loader.MapLoader;
import model.Grid;
import time.Time;
import time.TimeBehaviour;

/**
 * Classe principale chargee de lancer le jeu
 * @author Fabien Morival
 */
public class Launcher {

	public static void main (String[] args) {
	
		MapLoader loader = new MapLoader();
		Grid level = null;
		try {
			level = loader.open("main");
		} catch (IOException e) {
			System.err.println("Impossible de charger le niveau sélectionné.");
			return;
		}
		
		ClassicLevelManager manager = new ClassicLevelManager(level);
		GridView view = new GridView(level);
		
		Time.capture(new TimeBehaviour() {
			
			public void update () {
				System.out.println(view.display());
			}
		});
		
		manager.start();
	}
}
