package com.minesweeper;

import com.minesweeper.display.GameWindow;
import com.minesweeper.display.NaviWindow;
import com.minesweeper.display.Window;

import com.minesweeper.map.Map;
import com.minesweeper.map.OpenGrid;
import com.minesweeper.repo.Repository;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Constant;
import com.minesweeper.utils.Store;

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
		mainWindow.setCursorText("新游戏", 12);
		mainWindow.setCursorText("生涯", 14);
		mainWindow.setCursorText("退出", 16);
		NaviWindow gameOverWindow = new NaviWindow();
		gameOverWindow.setText("Game Over.", 5);
		gameOverWindow.setCursorText("新游戏", 10);
		gameOverWindow.setCursorText("返回主菜单", 12);
		NaviWindow gamePauseWindow = new NaviWindow();
		gamePauseWindow.setText("游戏暂停", 5);
		gamePauseWindow.setCursorText("恢复", 10);
		gamePauseWindow.setCursorText("返回主菜单", 12);
		NaviWindow recordWindow = new NaviWindow();
		recordWindow.setText("游戏生涯", 5);
		recordWindow.setCursorText("清除数据", 16);
		recordWindow.setCursorText("返回主菜单", 18);
		GameWindow gameWindow = new GameWindow();
		currentWindow = mainWindow;
		Map map = new Map();
		if (Repository.getInstance().readContext(map)) {
			mainWindow.setCursorText("继续游戏", 10);
			mainWindow.removeCursor(1);
			Store.setGameMap(map);
			mainWindow.resetCursor();
		}
		while (isRunning) {
			currentWindow.focus();
			switch (currentWindowName) {
				case MAIN_MENU: {
					currentWindow = mainWindow;
					Map t = new Map();
					if (Repository.getInstance().readContext(t)) {
						mainWindow.setCursorText("继续游戏", 10);
						mainWindow.removeCursor(1);
						Store.setGameMap(t);
					}
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
				case RECORD: {
					int success = Repository.getInstance().readSuccessHistory();
					int all = Repository.getInstance().readAllHistory();
					recordWindow.setText("总胜利数: " + success, 10);
					recordWindow.setText("总游玩数: " + all, 12);
					recordWindow.resetCursor();
					currentWindow = recordWindow;
				}
			}
		}
		ClearScreen.clsCmd();
	}
}
