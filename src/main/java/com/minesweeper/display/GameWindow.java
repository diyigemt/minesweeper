package com.minesweeper.display;

import com.minesweeper.Main;
import com.minesweeper.map.Grid;
import com.minesweeper.map.LayBombToMap;
import com.minesweeper.map.Map;
import com.minesweeper.map.OpenGrid;
import com.minesweeper.user.InputType;
import com.minesweeper.utils.Constant;
import com.minesweeper.utils.Store;

import static com.minesweeper.utils.Constant.ERROR_INPUT;

public class GameWindow extends GridWindow {
	private boolean isFirst;
	private Map map;
	private OpenGrid grid = new OpenGrid();

	public GameWindow() throws Exception {
		init();
	}

	public void init() throws Exception {
		this.isFirst = true;
		this.map = new Map();
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 5);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 7);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 9);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 11);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 13);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 15);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 17);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 19);
		this.setCursorText("%s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s %s X %s", 21);
		this.cursorPosX = 0;
		this.cursorPosY = 0;
	}

	public boolean setGameMap(Grid[][] map) {
		int index = 5;
		for (Grid[] ys: map) {
			StringBuilder sb = new StringBuilder();
			for (Grid x : ys) {
				sb.append("%s ");
				if (x.isExpendTag()) {
					int i = x.getCountAround();
					if (x.isMineTag()) {
						sb.append("M %s ");
					} else if (i == 0) {
						sb.append("0 %s ");
					} else {
						sb.append(i).append(" %s ");
					}
				} else if (x.isFlagTag()) {
					sb.append("F %s ");
				} else {
					sb.append("X %s ");
				}
			}
			this.setCursorText(sb.toString(), index);
			index += 2;
		}
		return true;
	}

	@Override
	public void focus() {
		this.isRunning = true;
		this.staticShow();
		while (this.isRunning) {
			String nextInput = this.input.getNextInput(InputType.GAME);
			if (nextInput.equals(ERROR_INPUT)) {
				this.staticShow();
			}
			switch (nextInput) {
				case "q": {
					this.isRunning = Main.isRunning = false;
					break;
				}
				case "s": {
					this.next();
					break;
				}
				case "w": {
					this.prev();
					break;
				}
				case "a": {
					this.left();
					break;
				}
				case "d": {
					this.right();
					break;
				}
				case "m": {
					if (isFirst) {
						break;
					}
					grid.markGrid(map.map, this.cursorPosX, this.cursorPosY);
					break;
				}
				case "p": {
					Main.currentWindowName = Constant.CURRENT_WINDOW.PAUSE;
					Store.setGameMap(map);
					this.isRunning = false;
					break;
				}
				case "": {
					int posX = this.cursorPosX;
					int posY = this.cursorPosY;
					if (isFirst) {
						grid.reset();
						LayBombToMap.layBomb(map.map, posY, posX);
						isFirst = false;
					}
					grid.openGrid(map.map, posY, posX);
					this.setGameMap(map.map);
					if (grid.isGameOver()) {
						Main.currentWindowName = Constant.CURRENT_WINDOW.GAME_RESULT;
						Main.isWin = grid.isGameWin();
						this.isRunning = false;
					}
					this.staticShow();
					// TODO win ???
				}
			}
		}
	}
}
