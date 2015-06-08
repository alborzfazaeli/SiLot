package com.alborzfazaeli.silot.command;

import com.alborzfazaeli.silot.Constants;
import com.alborzfazaeli.silot.Pot;
import com.alborzfazaeli.silot.Ticket;
import com.alborzfazaeli.silot.Tickets;
import com.alborzfazaeli.silot.io.IO;

public class PurchaseCommand extends Command {

	public static final int EXIT_NO_TICKET_AVAILABLE = 1;
	public static final int EXIT_INVALID_NAME = 2;

	private Pot pot = null;
	private Tickets tickets = null;
	private IO io = null;

	public PurchaseCommand(Pot pot, Tickets tickets, IO io) {
		super(Constants.CMD_PURCHASE);
		this.pot = pot;
		this.tickets = tickets;
		this.io = io;
	}

	@Override
	public int execute() {
		if (!tickets.isTicketAvailable()) {
			io.println("No more tickets available!");
			return EXIT_NO_TICKET_AVAILABLE;
		}
		io.print("Enter your name: ");
		String name = io.nextLine();
		if (name == null || name.length() <= 0) {
			io.println("Invalid name!");
			return EXIT_INVALID_NAME;
		}
		Ticket ticket = tickets.purchaseTicket(name);
		if (ticket == null) {
			io.println("No more tickets available!");
			return EXIT_NO_TICKET_AVAILABLE;
		}
		pot.updateBalance(Constants.TICKET_PRICE);/* add 10$ to balance */
		io.println("Ball number: " + ticket.getBallNumber());
		io.println("Pot balance: " + pot.getBalance());
		return EXIT_SUCCESS;
	}

}
