package com.alborzfazaeli.silot;

public class Winner {

	private int prize = -1;
	private Ticket ticket = null;

	public Winner(Ticket ticket, int prize) {
		this.prize = prize;
		this.ticket = ticket;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	@Override
	public String toString() {
		String t = ticket == null || ticket.getName() == null ? "" : ticket.getName();
		return t + ": " + prize + "$";
	}

}
