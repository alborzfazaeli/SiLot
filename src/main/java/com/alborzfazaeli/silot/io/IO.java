package com.alborzfazaeli.silot.io;

public abstract class IO {

	public abstract String nextLine();
	
	public abstract void print(Object o);

	public abstract void println(Object o);
	
	public abstract void format(String format, Object... os);
	
}
