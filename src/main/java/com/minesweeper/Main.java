package com.minesweeper;

import com.minesweeper.display.GameWindow;
import com.minesweeper.display.NaviWindow;
import com.minesweeper.display.Window;

import com.minesweeper.map.Map;
import com.minesweeper.map.OpenGrid;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Constant;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static Window currentWindow;
	public static Constant.CURRENT_WINDOW currentWindowName;
	public static boolean isWin = false;
	public static boolean isRunning = true;
	public static boolean isPause = false;

	public static void main(String[] args) throws Exception {
		currentWindowName = Constant.CURRENT_WINDOW.MAIN_MENU;
		NaviWindow mainWindow = new NaviWindow();
		mainWindow.setText("Mine Sweeper", 5);
		mainWindow.setCursorText("新游戏", 10);
		mainWindow.setCursorText("生涯", 12);
		mainWindow.setCursorText("退出", 14);
		NaviWindow gameOverWindow = new NaviWindow();
		gameOverWindow.setText("Game Over.", 5);
		gameOverWindow.setCursorText("新游戏", 10);
		gameOverWindow.setCursorText("返回主菜单", 12);
		NaviWindow gamePauseWindow = new NaviWindow();
		gamePauseWindow.setText("游戏暂停", 5);
		gamePauseWindow.setCursorText("恢复", 10);
		gamePauseWindow.setCursorText("返回主菜单", 12);
		GameWindow gameWindow = new GameWindow();
		currentWindow = mainWindow;
		while (isRunning) {
			currentWindow.focus();
			switch (currentWindowName) {
				case MAIN_MENU: {
					currentWindow = mainWindow;
					mainWindow.resetCursor();
					currentWindow.staticShow();
					break;
				} case GAME: {
					if (!isPause) {
						gameWindow.init();
					}
					currentWindow = gameWindow;
					break;
				} case GAME_RESULT: {
					gameOverWindow.setText("You Lose.", 6);
					if (isWin) {
						gameOverWindow.setText("You Win.", 6);
					}
					gameOverWindow.resetCursor();
					currentWindow = gameOverWindow;
					break;
				} case PAUSE: {
					gamePauseWindow.resetCursor();
					currentWindow = gamePauseWindow;
				}
			}
		}
		ClearScreen.clsCmd();
	}
}
