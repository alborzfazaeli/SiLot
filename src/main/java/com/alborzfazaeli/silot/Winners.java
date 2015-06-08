package com.alborzfazaeli.silot;

public class Winners {

	private Winner[] winners = null;

	public Winner[] getWinners() {
		return winners;
	}

	public void setWinners(Winner[] winners) {
		this.winners = winners;
	}
	
	public void reset() {
		this.winners = null;
	}

}
