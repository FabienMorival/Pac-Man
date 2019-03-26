package model;

import java.util.Arrays;
import java.util.List;

public enum Way {

	N (0, -1),
	S (0, 1),
	E (1, 0),
	W (-1, 0);
	
	public int x, y;
	
	private Way (int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public Way opposite () {
		
		switch (this) {
		case N: return S;
		case S: return N;
		case E: return W;
		case W: return E;
		default : return null;
		}
	}
	
	public static Way[] cardinal () {
		
		return new Way[]{N, S, W, E};
	}
	
	@Override
	public String toString () {

		return "(" + x + ", " + y + ")";
	}
}
