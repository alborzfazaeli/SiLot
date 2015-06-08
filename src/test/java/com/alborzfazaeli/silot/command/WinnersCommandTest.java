package com.alborzfazaeli.silot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alborzfazaeli.silot.Constants;
import com.alborzfazaeli.silot.Ticket;
import com.alborzfazaeli.silot.Winner;
import com.alborzfazaeli.silot.Winners;
import com.alborzfazaeli.silot.io.IO;

public class WinnersCommandTest {

	@Test
	public void testExecute() {
		final List<String> inputs = new ArrayList<String>();
		final List<Object> outputs = new ArrayList<Object>();
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
				for (Object o : os) {
					outputs.add(o);
				}
			}
		};
		
		Winners winners = new Winners();
		assertNull("Winners not null!", winners.getWinners());
		
		Command cmd = new WinnersCommand(winners, io);
		int ret = cmd.execute();
		assertEquals("Invalid return value!", WinnersCommand.EXIT_NO_WINNERS_SELECTED, ret);
		
		Winner w1 = createWinner("Name1", 1);
		Winner w2 = createWinner("Name2", 2);
		Winner w3 = createWinner("Name3", 3);
		
		winners.setWinners(new Winner[]{w1});
		ret = cmd.execute();
		assertEquals("Invalid return value!", WinnersCommand.EXIT_NO_WINNERS_SELECTED, ret);
		
		outputs.clear();
		winners.setWinners(new Winner[]{w1, w2, w3});
		ret = cmd.execute();
		assertEquals("Invalid return value!", WinnersCommand.EXIT_SUCCESS, ret);
		assertEquals("outputs.length != 6", 6, outputs.size());
		assertEquals("Invalid output!", "1st ball", outputs.get(0));
		assertEquals("Invalid output!", "2nd ball", outputs.get(1));
		assertEquals("Invalid output!", "3rd ball", outputs.get(2));
		assertEquals("Invalid output!", "Name1: 100$", outputs.get(3));
		assertEquals("Invalid output!", "Name2: 100$", outputs.get(4));
		assertEquals("Invalid output!", "Name3: 100$", outputs.get(5));
	}
	
	private Winner createWinner(String name, int ballNum) {
		Ticket ticket = new Ticket(ballNum);
		ticket.setName(name);
		return new Winner(ticket, 100);
	}

	@Test
	public void testGetName() {
		Command cmd = new WinnersCommand(null, null);
		assertEquals("Command name!", Constants.CMD_WINNERS, cmd.getName());
	}

}
