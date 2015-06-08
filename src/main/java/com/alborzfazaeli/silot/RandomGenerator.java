package com.alborzfazaeli.silot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author alborzfazaeli
 *
 *         This class creates a random permutation of integers between minValue
 *         and maxValue. This works as ball machine.
 */
public class RandomGenerator {

	private int minValue = -1;
	private int maxValue = -1;
	private List<Integer> list = null;

	public RandomGenerator(int minValue, int maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.list = new ArrayList<Integer>();
		reset();
	}

	public void reset() {
		list.clear();
		for (int i = minValue; i <= maxValue; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
	}

	public int nextRandom() {
		return hasMoreRandom() ? list.remove(0) : -1;
	}

	public boolean hasMoreRandom() {
		return list.size() > 0;
	}

}
