package com.alborzfazaeli.silot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alborzfazaeli.silot.io.IO;

/**
 * 
 * @author alborzfazaeli
 *
 * This class keeps list of available/purchased tickets.
 */
public class Tickets {

	private Set<String> names = null;
	private List<Ticket> purchasedTickets = null;
	private RandomGenerator randomGenerator = null;
	private int maxNumberOfTickets = -1;
	private IO io = null;
	
	public Tickets(int numberOfTickets, IO io) {
		/* number of tickets must be greater than or equal to number of winners. */
		this.maxNumberOfTickets = Math.max(Constants.NUM_OF_WINNERS, numberOfTickets);
		purchasedTickets = new ArrayList<Ticket>();
		randomGenerator = new RandomGenerator(1, this.maxNumberOfTickets);
		names = new HashSet<String>();
		this.io = io;
		reset();
	}
	
	public int getMaximumNumberOfTickets() {
		return maxNumberOfTickets;
	}

	public void reset() {
		names.clear();
		purchasedTickets.clear();
		randomGenerator.reset();
	}

	public boolean isTicketAvailable() {
		return randomGenerator.hasMoreRandom();
	}

	public Ticket purchaseTicket(String name) {
		if (name == null || name.length() <= 0) {
			io.println("Invalid name!");
			return null;
		}
		if (!isTicketAvailable()) {
			io.println("No more tickets available!");
			return null;
		}
		if (names.contains(name)) {/* duplicate names are not allowed. */
			io.println("Name \'" + name + "\' exists!");
			return null;
		}
		names.add(name);
		Ticket t = new Ticket(randomGenerator.nextRandom());
		t.setName(name);
		purchasedTickets.add(t);
		return t;
	}

	public Ticket[] getPurchasedTickets() {
		return purchasedTickets.toArray(new Ticket[purchasedTickets.size()]);
	}

	public Ticket ticketForBallNumber(int number) {
		if (purchasedTickets == null) {
			return null;
		}
		for (Ticket t : purchasedTickets) {
			if (t.getBallNumber() == number) {
				return t;
			}
		}
		return null;
	}

}
