package com.alborzfazaeli.silot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alborzfazaeli.silot.io.IO;

public class TicketsTest {
	
	private static IO testIO = null;
	
	@BeforeClass
	public static void beforeClass() {
		testIO = new IO() {

			@Override
			public String nextLine() {
				return null;
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
	}

	@Test
	public void testGetMaximumNumberOfTickets() {
		Tickets t = new Tickets(1, testIO);
		assertEquals("tickts less than winners!", Constants.NUM_OF_WINNERS, t.getMaximumNumberOfTickets());
	}

	@Test
	public void testPurchaseTicket() {
		Tickets t = new Tickets(1, testIO);
		assertNull("", t.purchaseTicket(null));
		assertNull("", t.purchaseTicket(""));
		for (int i = 0; i < Constants.NUM_OF_WINNERS; i++) {
			assertNotNull("Null ticket!", t.purchaseTicket("Name" + i));
		}
		assertFalse("ticket available!", t.isTicketAvailable());
		assertNull("ticket is not null!", t.purchaseTicket("Name100"));
		t.reset();
		assertTrue("ticket not available!", t.isTicketAvailable());
		assertNotNull("Null ticket!", t.purchaseTicket("Name1"));
		assertNull("ticket is not null!", t.purchaseTicket("Name1"));		
	}

	@Test
	public void testGetPurchasedTickets() {
		Tickets t = new Tickets(1, testIO);
		assertNotNull("tickets is null!", t.getPurchasedTickets());
		assertEquals("ticket length != 0", 0, t.getPurchasedTickets().length);
		t.purchaseTicket("Name1");
		assertEquals("ticket length != 1", 1, t.getPurchasedTickets().length);
		assertEquals("ticket name is not Name1", "Name1", t.getPurchasedTickets()[0].getName());
	}

	@Test
	public void testTicketForBallNumber() {
		Tickets t = new Tickets(1, testIO);
		assertNull("ticket is not null!", t.ticketForBallNumber(1));
		Ticket tt = t.purchaseTicket("Name1");
		assertNotNull("ticket is null!", tt);
		Ticket ttt = t.ticketForBallNumber(tt.getBallNumber());
		assertNotNull("ticket is null!", ttt);
		assertEquals("ticket name is not Name1", tt.getName(), ttt.getName());
	}

}
