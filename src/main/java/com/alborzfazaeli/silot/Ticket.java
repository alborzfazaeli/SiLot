package com.alborzfazaeli.silot;

public class Ticket {

	private int ballNumber = -1;
	private String name = null;

	public Ticket(int ballNumber) {
		this.ballNumber = ballNumber;
	}

	public int getBallNumber() {
		return ballNumber;
	}

	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
