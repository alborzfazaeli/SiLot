package com.alborzfazaeli.silot.io;

import java.util.Scanner;

/**
 * 
 * @author alborzfazaeli
 *
 * Default implementation of IO class that reads from System.in and writes to System.out.
 * 
 */
public class DefaultIO extends IO {

	private Scanner scanner = null;

	public DefaultIO() {
		this.scanner = new Scanner(System.in);
	}
	
	@Override
	public String nextLine() {
		return scanner.nextLine();
	}

	@Override
	public void print(Object o) {
		System.out.print(o);
	}

	@Override
	public void println(Object o) {
		System.out.println(o);
	}

	@Override
	public void format(String format, Object... os) {
		System.out.format(format, os);
	}

}
