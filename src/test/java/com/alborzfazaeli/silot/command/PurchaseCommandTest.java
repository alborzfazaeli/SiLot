package com.alborzfazaeli.silot.command;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alborzfazaeli.silot.Constants;
import com.alborzfazaeli.silot.Pot;
import com.alborzfazaeli.silot.Tickets;
import com.alborzfazaeli.silot.io.IO;

public class PurchaseCommandTest {

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
		Pot pot = new Pot();
		Tickets tickets = new Tickets(3, io);
		Command cmd = new PurchaseCommand(pot, tickets, io);
		
		assertEquals("Balance is not " + Constants.INITIAL_POT_BALANCE,
				Constants.INITIAL_POT_BALANCE, pot.getBalance());
		
		inputs.add("");
		int ret = cmd.execute();
		assertEquals("Invalid return value!", PurchaseCommand.EXIT_INVALID_NAME, ret);

		inputs.add("Name1");
		ret = cmd.execute();
		assertEquals("Balance is not " + (Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 1)), Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 1), pot.getBalance());
		assertNotNull("Null tickets!", tickets.getPurchasedTickets());
		assertEquals("tickets.length != 1", 1, tickets.getPurchasedTickets().length);
		assertEquals("Invalid return value!", PurchaseCommand.EXIT_SUCCESS, ret);
		
		inputs.add("Name2");
		ret = cmd.execute();
		assertEquals("Balance is not " + (Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 2)), Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 2), pot.getBalance());
		assertEquals("Invalid return value!", PurchaseCommand.EXIT_SUCCESS, ret);
		
		inputs.add("Name3");
		ret = cmd.execute();
		assertEquals("Balance is not " + (Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 3)), Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 3), pot.getBalance());
		assertEquals("Invalid return value!", PurchaseCommand.EXIT_SUCCESS, ret);

		inputs.add("Name4");
		ret = cmd.execute();
		assertEquals("Balance is not " + (Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 3)), Constants.INITIAL_POT_BALANCE
				+ (Constants.TICKET_PRICE * 3), pot.getBalance());
		assertEquals("Invalid return value!", PurchaseCommand.EXIT_NO_TICKET_AVAILABLE, ret);
	}

	@Test
	public void testGetName() {
		Command cmd = new PurchaseCommand(null, null, null);
		assertEquals("Command name!", Constants.CMD_PURCHASE, cmd.getName());
	}

}
