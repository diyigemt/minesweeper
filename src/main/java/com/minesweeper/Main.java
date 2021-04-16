package com.minesweeper;

import com.minesweeper.display.GridWindow;
import com.minesweeper.display.NaviWindow;
import com.minesweeper.display.Window;
import com.minesweeper.map.Grid;
import com.minesweeper.map.LayBombToMap;
import com.minesweeper.map.Map;
import com.minesweeper.map.OpenGrid;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	private static Window currentWindow;

	public static void main(String[] args) throws Exception {
		boolean running = true;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		NaviWindow mainWindow = new NaviWindow();
		mainWindow.setText("Mine Sweeper", 5);
		mainWindow.setCursorText("新游戏", 10);
		mainWindow.setCursorText("生涯", 12);
		mainWindow.setCursorText("退出", 14);
		NaviWindow gameOverWindow = new NaviWindow();
		gameOverWindow.setText("Game Over.", 5);
		gameOverWindow.setText("You Lose.", 6);
		gameOverWindow.setCursorText("新游戏", 10);
		gameOverWindow.setCursorText("返回主菜单", 12);
		GridWindow gameWindow = new GridWindow();
		Map map = new Map();
		OpenGrid grid = new OpenGrid();
		boolean isFirst = true;
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 5);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 6);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 7);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 8);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 9);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 10);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 11);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 12);
		gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 13);

		currentWindow = mainWindow;
		String select = null;
		currentWindow.staticShow();
		while (running) {
			try {
				select = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (select == null) continue;
			switch (select) {
				case "q": {
					running = false;
					break;
				}
				case "s":
				case "S": {
					if (currentWindow instanceof NaviWindow) {
						((NaviWindow) currentWindow).next();
					} else if (currentWindow instanceof GridWindow) {
						((GridWindow) currentWindow).next();
					}
					break;
				}
				case "w":
				case "W": {
					if (currentWindow instanceof NaviWindow) {
						((NaviWindow) currentWindow).prev();
					} else if (currentWindow instanceof GridWindow) {
						((GridWindow) currentWindow).prev();
					}
					break;
				}
				case "a":
				case "A": {
					if (currentWindow instanceof GridWindow) {
						((GridWindow) currentWindow).left();
					}
					break;
				}
				case "d":
				case "D": {
					if (currentWindow instanceof GridWindow) {
						((GridWindow) currentWindow).right();
					}
					break;
				}
				case "": {
					if (currentWindow instanceof NaviWindow) {
						int pos = ((NaviWindow) currentWindow).getCurrentCursorPos();
						switch (pos) {
							case 0: {
								if (currentWindow == mainWindow) {
									currentWindow = gameWindow;
								}
								break;
							}
							case 1: {
								if (currentWindow == gameOverWindow) {
									currentWindow = mainWindow;
									mainWindow.resetCursor();
								}
								break;
							}
							case 2: {
								running = false;
								ClearScreen.clsCmd();
								break;
							}
							default:
						}
					} else if (currentWindow instanceof GridWindow) {
						int posX = ((GridWindow) currentWindow).getCurrentPosX();
						int posY = ((GridWindow) currentWindow).getCurrentPosY();
						if (isFirst) {
							LayBombToMap.layBomb(map.map, posY, posX);
							isFirst = false;
						}
						grid.openGrid(map.map, posY, posX);
						((GridWindow) currentWindow).setGameMap(map.map);
						if (grid.isGameOver()) {
							currentWindow = gameOverWindow;
						}
					}
				}
				default:
					currentWindow.staticShow();
			}
		}
		ClearScreen.clsCmd();
	}
}
