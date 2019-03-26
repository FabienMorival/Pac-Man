package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.Coord2D;
import model.Ghost;
import model.Grid;
import model.PacMan;
import model.Way;

public class MapLoader {
	
	private static final String MAP_REPOSITORY = "resources\\maps";

	public Grid open (String name) throws IOException {
		
		File file = new File(MAP_REPOSITORY + "\\" + name); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		String res = br.readLine(), st; 
		while ((st = br.readLine()) != null)
		    res += "\n" + st;
		return Grid.read(res);
	}
	
	public static void main (String[] args) {
		
		try {
			Grid g = new MapLoader().open("main");
			Ghost blinky = (Ghost)g.findEntitiesByType("ghost").get(0);
			for (int i = 0; i < 100; i++) {
			blinky.act();
			System.out.println(g);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
