package com.alborzfazaeli.silot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alborzfazaeli.silot.command.Command;
import com.alborzfazaeli.silot.command.DrawCommand;
import com.alborzfazaeli.silot.command.PurchaseCommand;
import com.alborzfazaeli.silot.command.WinnersCommand;
import com.alborzfazaeli.silot.io.IO;

/**
 * 
 * @author alborzfazaeli
 *
 *         This class is request handler responsible to read requests from input
 *         and execute proper command.
 */
public class SiLot {

	private IO io = null;
	private Map<String, Command> commands = null;

	public SiLot(int numOfTickets, IO io) {
		this.io = io;

		Pot pot = new Pot();
		Tickets tickets = new Tickets(numOfTickets, io);
		Winners winners = new Winners();

		commands = new HashMap<String, Command>();
		registerCommand(new PurchaseCommand(pot, tickets, io));
		registerCommand(new DrawCommand(pot, tickets, winners, io));
		registerCommand(new WinnersCommand(winners, io));
	}

	private void registerCommand(Command cmd) {
		/* maps command name to command */
		commands.put(cmd.getName(), cmd);
	}

	/**
	 * prints list of available commands.
	 */
	private void printCommands() {
		io.println("Available commands:");
		Iterator<Command> it = commands.values().iterator();
		while (it.hasNext()) {
			Command cmd = it.next();
			io.println("\t" + cmd.getName());
		}
		io.println("\texit");
		io.print("Enter command: ");
	}

	public void handleCommands() {
		boolean exited = false;
		while (!exited) {
			printCommands();
			String cmd = io.nextLine();
			if ("exit".equals(cmd)) {
				exited = true;
			} else {
				Command command = commands.get(cmd);
				if (command != null) {
					command.execute();
				}
			}
		}
		io.println("Exited successfully!");
	}

}
