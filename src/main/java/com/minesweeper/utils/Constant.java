package com.minesweeper.utils;

import java.util.ArrayList;

public class Constant {
	public static final ArrayList<String> MENU_LIST = new ArrayList<String>();
	public static final ArrayList<String> GAME_LIST = new ArrayList<String>();
	public static final String ERROR_INPUT = "-1";
	public enum CURRENT_WINDOW {
		MAIN_MENU, GAME, PAUSE, GAME_RESULT, RECORD
	}
	static {
		MENU_LIST.add("w");
		MENU_LIST.add("s");
		MENU_LIST.add("q");
		MENU_LIST.add("");

		GAME_LIST.add("w");
		GAME_LIST.add("a");
		GAME_LIST.add("s");
		GAME_LIST.add("d");
		GAME_LIST.add("m");
		GAME_LIST.add("q");
		GAME_LIST.add("p");
		GAME_LIST.add("");
	}
}
