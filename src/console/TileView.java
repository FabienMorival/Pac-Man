package console;

import model.Tile;

public class TileView extends ConsoleView<Tile> {

	public TileView(Tile model) {
		
		super(model);
	}

	@Override
	public String computeView (Object arg) {
		
		return this.getModel().toString();
	}
}
