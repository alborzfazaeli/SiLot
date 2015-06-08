package com.alborzfazaeli.silot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RandomGeneratorTest {

	@Test
	public void testRandomGenerator() {
		Set<Integer> set = new HashSet<Integer>();
		RandomGenerator r = new RandomGenerator(1, 5);
		for (int i = 0; i < 5; i++) {
			int rand = r.nextRandom();
			assertFalse("Random in not unique!", set.contains(rand));
			assertTrue("Invalid random value", rand >= 1);
			assertTrue("Invalid random value", rand <= 5);
			set.add(rand);
		}
		assertFalse("Has more random!", r.hasMoreRandom());
		r.reset();
		assertTrue("Doesn't have more random!", r.hasMoreRandom());		
	}

}
