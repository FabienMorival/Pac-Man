package console;

import model.Entity;
import model.Grid;

public class GridView extends ConsoleView<Grid> {
	
	private TileView[][] tiles;

	public GridView(Grid model) {
		
		super(model);
		int x = model.getX(), y = model.getY();
		this.tiles = new TileView[y][];
		for (int i = 0; i < y; i++) {
			this.tiles[i] = new TileView[x];
			for (int j = 0; j < x; j++)
				this.tiles[i][j] = new TileView(model.getTile(j, i));
		}
	}

	@Override
	protected String computeView(Object arg) {
		
		Grid g = this.getModel();
		String res = "";
		String[] tl = new String[g.getY()];
		for (int i = 0; i < g.getY(); i++) {
			tl[i] = "";
			for (int j = 0; j < g.getX(); j++)
				tl[i] += tiles[i][j].display();
		}
		for (Entity e : g.getEntities()) {
			int x = e.getPosition().x, y = e.getPosition().y;
			tl[y] = tl[y].substring(0,x) + e.toString() + tl[y].substring(x+1);
		}
		for (String t : tl)
			res += t + "\n";
		return res;
	}
}
