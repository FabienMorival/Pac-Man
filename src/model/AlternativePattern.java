package model;

import java.util.Random;

public class AlternativePattern implements Pattern {
	
	private final static float MIN_BEFORE_ALT = 20;
	private final static float MAX_BEFORE_ALT = 30;
	
	Random random = new Random();
	
	Pattern[] patterns;
	int index = 0;
	int timer = 0;
	
	public AlternativePattern (Pattern[] patterns) {
		
		this.patterns = patterns;
	}
	
	public AlternativePattern (Pattern p1, Pattern p2) {
		
		this(new Pattern[] { p1, p2 } );
	}

	@Override
	public Way next(Actor a) {

		Pattern p = patterns[index];
		if (++timer >= MAX_BEFORE_ALT) {
			timer = 0;
			index = (index + 1) % patterns.length;
		}
		return p.next(a);
	}
}
