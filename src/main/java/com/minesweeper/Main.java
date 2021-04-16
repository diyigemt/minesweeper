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
					gameWindow.init();
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
				}
			}
		}
//		while (isRunning) {
//			try {
//				select = reader.readLine();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			if (select == null) continue;
//			switch (select) {
//				case "q": {
//					isRunning = false;
//					break;
//				}
//				case "s":
//				case "S": {
//					if (currentWindow instanceof NaviWindow) {
//						((NaviWindow) currentWindow).next();
//					} else if (currentWindow instanceof GridWindow) {
//						((GridWindow) currentWindow).next();
//					}
//					break;
//				}
//				case "w":
//				case "W": {
//					if (currentWindow instanceof NaviWindow) {
//						((NaviWindow) currentWindow).prev();
//					} else if (currentWindow instanceof GridWindow) {
//						((GridWindow) currentWindow).prev();
//					}
//					break;
//				}
//				case "a":
//				case "A": {
//					if (currentWindow instanceof GridWindow) {
//						((GridWindow) currentWindow).left();
//					}
//					break;
//				}
//				case "d":
//				case "D": {
//					if (currentWindow instanceof GridWindow) {
//						((GridWindow) currentWindow).right();
//					}
//					break;
//				}
//				case "": {
//					if (currentWindow instanceof NaviWindow) {
//						int pos = ((NaviWindow) currentWindow).getCurrentCursorPos();
//						switch (pos) {
//							case 0: {
//								if (currentWindow == mainWindow) {
//									currentWindow = gameWindow;
//								}
//								break;
//							}
//							case 1: {
//								if (currentWindow == gameOverWindow) {
//									currentWindow = mainWindow;
//									mainWindow.resetCursor();
//								}
//								break;
//							}
//							case 2: {
//								isRunning = false;
//								ClearScreen.clsCmd();
//								break;
//							}
//							default:
//						}
//					} else if (currentWindow instanceof GridWindow) {
//						int posX = ((GridWindow) currentWindow).getCurrentPosX();
//						int posY = ((GridWindow) currentWindow).getCurrentPosY();
//						if (isFirst) {
//							LayBombToMap.layBomb(map.map, posY, posX);
//							isFirst = false;
//						}
//						grid.openGrid(map.map, posY, posX);
//						((GameWindow) currentWindow).setGameMap(map.map);
//						if (grid.isGameOver()) {
//							currentWindow = gameOverWindow;
//						}
//					}
//				}
//				default:
//					currentWindow.staticShow();
//			}
//		}
		ClearScreen.clsCmd();
	}
}
