package com.minesweeper.utils;

import com.minesweeper.map.Map;

import java.util.HashMap;

/**
 * @author diyigemt
 */
public class Store {
	private static final HashMap<String, Object> MAP = new HashMap<String, Object>();
	private static final String CONSOLE_HEIGHT = "console_height";
	private static final String CONSOLE_WIDTH = "console_width";
	private static final String CURRENT_GAME_MAP = "console_width";

	public static void set(String key, Object o) {
		MAP.put(key, o);
	}

	public static Object get(String key) {
		return MAP.get(key);
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

	public static void setGameMap(Map map) {
		set(CURRENT_GAME_MAP, map);
	}

	public static Map getGameMap() {
		if (get(CURRENT_GAME_MAP) instanceof Map) {
			return (Map) get(CURRENT_GAME_MAP);
		}
		return null;
	}
}
