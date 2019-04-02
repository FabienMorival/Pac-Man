package console;

import java.util.Observable;
import java.util.Observer;

public abstract class ConsoleView<T> implements Observer {

	private T model;
	
	//private String view = "";
	
	public ConsoleView (T model) {
		
		this.model = model;
	}
	
	public T getModel () {
		
		return this.model;
	}
	
	public String display () {
		
		//return this.view;
		return this.computeView(null);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		//this.view = this.computeView(arg);
	}
	
	protected abstract String computeView (Object arg);
}
