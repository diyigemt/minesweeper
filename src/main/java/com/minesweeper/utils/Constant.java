package com.minesweeper.utils;

import java.util.ArrayList;

/**
 * @author diyigemt
 */
public class Constant {
	public static final ArrayList<String> MENU_LIST = new ArrayList<String>();
	public static final ArrayList<String> GAME_LIST = new ArrayList<String>();
	public static final String ERROR_INPUT = "-1";
	public static int GAME_TOTAL = 0;
	public static int GAME_WIN = 0;

	/**
	 * 提供当前运行的界面信息
	 */
	public enum CURRENT_WINDOW {
		/* 主菜单 */
		MAIN_MENU,
		/* 游戏界面 */
		GAME,
		/* 暂停界面 */
		PAUSE,
		/* 游戏结果界面 */
		GAME_RESULT,
		/* 生涯界面 */
		RECORD
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
