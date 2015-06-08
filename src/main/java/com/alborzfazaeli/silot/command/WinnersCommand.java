package com.alborzfazaeli.silot.command;

import com.alborzfazaeli.silot.Constants;
import com.alborzfazaeli.silot.Winner;
import com.alborzfazaeli.silot.Winners;
import com.alborzfazaeli.silot.io.IO;

public class WinnersCommand extends Command {

	public static final int EXIT_NO_WINNERS_SELECTED = 1;
	
	private IO io = null;
	private Winners winners = null;

	public WinnersCommand(Winners winners, IO io) {
		super(Constants.CMD_WINNERS);
		this.io = io;
		this.winners = winners;
	}

	@Override
	public int execute() {
		if (winners == null) {
			io.println("No winners selected yet!");
			return EXIT_NO_WINNERS_SELECTED;
		}
		Winner[] ws = winners.getWinners();
		if (ws == null || ws.length < Constants.NUM_OF_WINNERS) {
			io.println("No winners selected yet!");
			return EXIT_NO_WINNERS_SELECTED;
		}
		io.format("%-16s%-16s%-16s%n", "1st ball", "2nd ball", "3rd ball");
		io.format("%-16s%-16s%-16s%n", ws[0].toString(), ws[1].toString(), ws[2].toString());
		return EXIT_SUCCESS;
	}
	
}
