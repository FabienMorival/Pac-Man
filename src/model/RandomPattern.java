package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPattern implements Pattern {
	
	private final static float MOTION_CHAOS = 0.1f;
	
	Random random = new Random();
	Way previous = this.randomWay();

	@Override
	public Way next(Actor a) {

		Way newWay;
		do {
			newWay = randomPossibleWay(a);
		} while (newWay == previous.opposite() && random.nextFloat() > MOTION_CHAOS);
		return previous = newWay;
	}

	public Way randomPossibleWay(Actor a) {

		List<Way> ways = new ArrayList<>();
		for (Way way : Way.values())
			if (a.canMove(way))
				ways.add(way);
		return ways.get(random.nextInt(ways.size()));
	}
	
	private Way randomWay () {

		return Way.values()[random.nextInt(Way.values().length)];
	}
}
