package com.alborzfazaeli.silot;

import static org.junit.Assert.*;

import org.junit.Test;

public class PotTest {

	@Test
	public void testUpdateBalance() {
		Pot pot = new Pot();
		assertEquals("Balance != 200", 200, pot.getBalance());
		pot.updateBalance(-1);
		assertEquals("Balance != 199", 199, pot.getBalance());
		pot.updateBalance(2);
		assertEquals("Balance != 201", 201, pot.getBalance());
	}

}
