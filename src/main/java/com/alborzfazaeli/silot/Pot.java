package com.alborzfazaeli.silot;

public class Pot {

	private int balance = Constants.INITIAL_POT_BALANCE;
	
	public Pot() {
	}

	public void updateBalance(int i) {
		balance += i;
	}
	
	public int getBalance() {
		return balance;
	}
	
}
