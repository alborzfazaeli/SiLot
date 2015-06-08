package com.alborzfazaeli.silot.command;

public abstract class Command {

	public static final int EXIT_SUCCESS = 0;

	private String name = null;

	public Command(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return exit code shows if the command was executed successfully or not.
	 */
	public abstract int execute();
}
