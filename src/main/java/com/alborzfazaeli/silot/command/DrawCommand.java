package com.alborzfazaeli.silot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.alborzfazaeli.silot.Constants;
import com.alborzfazaeli.silot.Pot;
import com.alborzfazaeli.silot.Ticket;
import com.alborzfazaeli.silot.Tickets;
import com.alborzfazaeli.silot.Winner;
import com.alborzfazaeli.silot.Winners;
import com.alborzfazaeli.silot.io.IO;

public class DrawCommand extends Command {

	public static final int EXIT_BUY_TICKET = 1;
	
	private Tickets tickets = null;
	private Winners winners = null;
	private Pot pot = null;
	private IO io = null;

	public DrawCommand(Pot pot, Tickets tickets, Winners winners, IO io) {
		super(Constants.CMD_DRAW);
		this.tickets = tickets;
		this.winners = winners;
		this.pot = pot;
		this.io = io;
	}

	@Override
	public int execute() {
		if (tickets == null) {
			io.println("Buy more tickets!");
			return EXIT_BUY_TICKET;
		}
		Ticket[] pTickets = tickets.getPurchasedTickets();
		if (pTickets == null || pTickets.length < Constants.NUM_OF_WINNERS) {
			io.println("Buy more tickets!");
			return EXIT_BUY_TICKET;
		}
		List<Winner> list = new ArrayList<Winner>();
		int totalPrize = 0;
		double balance = (double) pot.getBalance();
		double[] percent = new double[]{0.75d, 0.15d, 0.10d};
		
		List<Ticket> tlist = new ArrayList<Ticket>(Arrays.asList(pTickets));
		Collections.shuffle(tlist); /* random permutation */
		
		for (int i = 0; i < Constants.NUM_OF_WINNERS; i++) {
			Ticket ticket = tlist.remove(0);
			double prize = (balance / 2) * percent[i];
			Winner w = new Winner(ticket, (int) prize);
			list.add(w);
			totalPrize += prize;
		}
		
		pot.updateBalance(-1 * totalPrize);
		winners.setWinners(list.toArray(new Winner[list.size()]));
		tickets.reset();
		
		io.println("Winners selected!");
		io.println("Pot balance: " + pot.getBalance());
		
		return EXIT_SUCCESS;
	}

}
