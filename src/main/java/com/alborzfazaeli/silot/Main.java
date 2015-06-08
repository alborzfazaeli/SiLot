package com.alborzfazaeli.silot;

import com.alborzfazaeli.silot.io.DefaultIO;

public class Main {

	public static void main(String[] args) {
		SiLot silot = new SiLot(50, new DefaultIO());
		silot.handleCommands();
	}
	
}
