package com.alborzfazaeli.silot.command;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alborzfazaeli.silot.Constants;
import com.alborzfazaeli.silot.Pot;
import com.alborzfazaeli.silot.Tickets;
import com.alborzfazaeli.silot.Winners;
import com.alborzfazaeli.silot.io.IO;

public class DrawCommandTest {

	@Test
	public void testExecute() {
		final List<String> inputs = new ArrayList<String>();
		IO io = new IO() {

			@Override
			public String nextLine() {
				return inputs.remove(0);
			}

			@Override
			public void print(Object o) {
			}

			@Override
			public void println(Object o) {
			}

			@Override
			public void format(String format, Object... os) {
			}
		};
		Winners winners = new Winners();
		
		Pot pot = new Pot();
		Tickets tickets = new Tickets(3, io);
		Command cmd = new DrawCommand(pot, tickets, winners, io);
		
		int ret = cmd.execute();
		assertEquals("tickets.length != 0", 0, tickets.getPurchasedTickets().length);
		assertEquals("Invalid return value!", DrawCommand.EXIT_BUY_TICKET, ret);
		
		tickets.purchaseTicket("Name1");
		tickets.purchaseTicket("Name2");
		tickets.purchaseTicket("Name3");
		
		ret = cmd.execute();
		assertEquals("Invalid return value!", DrawCommand.EXIT_SUCCESS, ret);
		assertEquals("balance != 100", 100, pot.getBalance());
		assertNotNull("winners is null!", winners.getWinners());
		assertEquals("winners.length != 3", 3, winners.getWinners().length);
		assertEquals("tickets.length != 0", 0, tickets.getPurchasedTickets().length);
	}
	
	@Test
	public void testGetName() {
		Command cmd = new DrawCommand(null, null, null, null);
		assertEquals("Command name!", Constants.CMD_DRAW, cmd.getName());
	}

}
