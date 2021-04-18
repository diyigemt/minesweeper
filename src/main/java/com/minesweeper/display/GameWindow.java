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
import static com.minesweeper.utils.Constant.GAME_WIN;

/**
 * 游戏主窗体
 * @author diyigemt
 */
@SuppressWarnings("AlibabaSwitchStatement")
public class GameWindow extends GridWindow {
	private boolean isFirst;
	private Map map;
	private OpenGrid grid = new OpenGrid();

	/**
	 * 默认构造函数 添加操作提示
	 */
	public GameWindow() {
		init();
		this.setText("请输入 w a s d 进行光标移动, p 暂停游戏, m 标记格子, enter 进行输入确认和翻开格子, q 退出程序", 0, 40);
	}

	/**
	 * 初始化游戏地图 假的 因为地图在第一次点击的时候才会初始化
	 */
	public void init() {
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
		this.posX = 0;
		this.posY = 0;
	}

	/**
	 * 将游戏地图转换为窗体内容
	 * @param map 游戏地图
	 * @return 是否成功
	 */
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

	/**
	 * 获取焦点 接管用户输入 同时进行游戏逻辑的判断
	 */
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
					grid.markGrid(map.map, this.posX, this.posY);
					break;
				}
				case "p": {
					Main.currentWindowName = Constant.CURRENT_WINDOW.PAUSE;
					Store.setGameMap(map);
					this.isRunning = false;
					break;
				}
				case "": {
					int posX = this.posX;
					int posY = this.posY;
					if (isFirst) {
						grid.reset();
						LayBombToMap.layBomb(map.map, posY, posX);
						isFirst = false;
					}
					grid.openGrid(map.map, posY, posX);
					this.setGameMap(map.map);
					if (grid.isGameOver()) {
						Main.currentWindowName = Constant.CURRENT_WINDOW.GAME_RESULT;
						Constant.GAME_TOTAL = Constant.GAME_TOTAL + 1;
						if (grid.isGameWin()) {
							Main.isWin = grid.isGameWin();
							Constant.GAME_WIN = GAME_WIN + 1;
						}
						this.isRunning = false;
					}
					this.staticShow();
				}
			}
		}
	}
}
