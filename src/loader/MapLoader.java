package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.Ghost;
import model.Grid;
import model.PacDot;
import model.PacMan;
import model.PowerPellet;
import model.Spawner;
import model.TileState;

public class MapLoader {
	
	private static final String MAP_REPOSITORY = "resources\\maps";

	public static final char WALL_SYMBOL = 'X';
	public static final char DOOR_SYMBOL = '_';
	public static final char PACDOT_SYMBOL = '.';
	public static final char POWER_PELLET_SYMBOL = 'o';
	public static final char PACMAN_SYMBOL = '@';
	public static final char BLINKY_SYMBOL = 'B';
	public static final char PINKY_SYMBOL = 'P';
	public static final char INKY_SYMBOL = 'I';
	public static final char CLYDE_SYMBOL = 'C';
	public static final char FRUIT_SYMBOL = '?';

	public final static String PACMAN_NAME = PacMan.PACMAN_DEFAULT_NAME;
	public static final String BLINKY_NAME = "blinky";
	public static final String PINKY_NAME = "pinky";
	public static final String INKY_NAME = "inky";
	public static final String CLYDE_NAME = "clyde";

	public Grid open (String name) throws IOException {
		
		File file = new File(MAP_REPOSITORY + "\\" + name); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		String res = br.readLine(), st; 
		while ((st = br.readLine()) != null)
		    res += "\n" + st;
		br.close();
		
		return read(res);
	}
	
	public Grid read (String src) {
		
		String[] lines = src.split("\n");
		TileState[][] states = new TileState[lines.length][];
		
		// Ajout des cases
		for (int i = 0; i < states.length; i++) {
			states[i] = new TileState[lines[i].length()];
			for (int j = 0; j < states[i].length; j++) {
				char c = lines[i].charAt(j);
				switch (c) {
				case WALL_SYMBOL:
					states[i][j] = TileState.WALL;
					break;
				case DOOR_SYMBOL:
					states[i][j] = TileState.DOOR;
					break;
				default:
					states[i][j] = TileState.CORRIDOR;
					break;
				}
			}
		}
		// Creation de la grille
		Grid g = new Grid(states);
		
		// Ajout des entites
		for (int i = 0; i < g.getY(); i++) {
			for (int j = 0; j < g.getX(); j++) {
				char c = lines[i].charAt(j);
				switch (c) {
				case BLINKY_SYMBOL:
					g.addSpawner(new Spawner(g.getTile(j, i), t -> {
						Ghost ghost = new Ghost(t);
						ghost.setName(BLINKY_NAME);
						t.getGrid().addEntity(ghost);
					}));
					break;
				case PINKY_SYMBOL:
					g.addSpawner(new Spawner(g.getTile(j, i), t -> {
						Ghost ghost = new Ghost(t);
						ghost.setName(PINKY_NAME);
						t.getGrid().addEntity(ghost);
					}));
					break;
				case INKY_SYMBOL:
					g.addSpawner(new Spawner(g.getTile(j, i), t -> {
						Ghost ghost = new Ghost(t);
						ghost.setName(INKY_NAME);
						t.getGrid().addEntity(ghost);
					}));
					break;
				case CLYDE_SYMBOL:
					g.addSpawner(new Spawner(g.getTile(j, i), t -> {
						Ghost ghost = new Ghost(t);
						ghost.setName(CLYDE_NAME);
						t.getGrid().addEntity(ghost);
					}));
					break;
				case PACMAN_SYMBOL:
					g.addSpawner(new Spawner(g.getTile(j, i), t -> {
						PacMan pacman = new PacMan(t);
						pacman.setName(PACMAN_NAME);
						t.getGrid().addEntity(pacman);
					}));
					break;
				case PACDOT_SYMBOL:
					g.addEntity(new PacDot(g.getTile(j, i)));
					break;
				case POWER_PELLET_SYMBOL:
					g.addEntity(new PowerPellet(g.getTile(j, i)));
					break;
				case FRUIT_SYMBOL: break;
				}
			}
		}
		return g;
	}
}
