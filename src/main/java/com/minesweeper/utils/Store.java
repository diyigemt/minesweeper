package com.minesweeper.utils;

import java.util.HashMap;

public class Store {
	private static final HashMap<String, Object> map = new HashMap<String, Object>();
	private static final String CONSOLE_HEIGHT = "console_height";
	private static final String CONSOLE_WIDTH = "console_width";

	public static void set(String key, Object o) {
		map.put(key, o);
	}

	public static Object get(String key) {
		return map.get(key);
	}

	public static void setConsoleSize(int height, int width) {
		set(CONSOLE_HEIGHT, height);
		set(CONSOLE_WIDTH, width);
	}

	public static int getConsoleHeight() {
		return (int) get(CONSOLE_HEIGHT);
	}

	public static int getConsoleWidth() {
		return (int) get(CONSOLE_WIDTH);
	}
}
