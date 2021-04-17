package com.minesweeper.display;

import com.minesweeper.Main;
import com.minesweeper.map.Map;
import com.minesweeper.repo.Repository;
import com.minesweeper.user.InputType;
import com.minesweeper.utils.Constant;
import com.minesweeper.utils.Store;
import com.minesweeper.utils.Utils;

import java.util.ArrayList;

import static com.minesweeper.utils.Constant.ERROR_INPUT;

public class NaviWindow extends Window{
	private ArrayList<Integer> availablePos = new ArrayList<Integer>();
	private int cursorPos = -1;
	public NaviWindow() {
		super();
		this.setText("请输入 s w 进行菜单导航, enter 进行输入确认和进入菜单项, q 退出程序", 0, 40);
	}
	public NaviWindow(int startPosY) {
		super(startPosY);
	}

	public ArrayList<Integer> getAvailablePos() {
		return availablePos;
	}

	public void setAvailablePos(ArrayList<Integer> availablePos) {
		this.availablePos = availablePos;
	}

	public boolean addAvailablePos(int pos) {
		String tmp = this.contain[pos];
		if (tmp == null) return false;
		this.sortAdd(pos);
		return true;
	}

	private void sortAdd(int data) {
		if (this.availablePos.isEmpty()) {
			this.availablePos.add(data);
			return;
		}
		if (this.availablePos.contains(data)) return;
		if (this.availablePos.get(0) > data) {
			this.availablePos.add(0, data);
			return;
		}
		for (int i = 0; i < this.availablePos.size() - 1; i++) {
			if (this.availablePos.get(i) < data && this.availablePos.get(i + 1) > data) {
				this.availablePos.add(i, data);
				return;
			}
		}
		this.availablePos.add(data);
	}

	public void removeCursor(int pos) {
		int index = this.availablePos.get(pos);
		String tmp = this.contain[index];
		if (tmp == null) return;
		tmp = tmp.replace(">>", "  ").replace("<<", "  ");
		this.contain[index] = tmp;
	}

	public boolean setCursorText(String target, int posY) {
		if (!this.checkPos(posY)) return false;
		if (this.cursorPos == -1) {
			this.cursorPos = 0;
			String cursorText = Utils.getCursorWideText(this.width, target);
			this.setNativeText(cursorText, posY);
		} else {
			this.setText(target, posY);
		}
		this.addAvailablePos(posY);
		return true;
	}

	public boolean next() {
		if (!hasNext()) return false;
		int index = this.cursorPos;
		int nextPos = this.availablePos.get(index + 1);
		String cursorText = Utils.getCursorWideText(this.width, this.contain[nextPos]);
		this.setNativeText(cursorText, nextPos);
		this.removeCursor(index);
		this.cursorPos++;
		return this.staticShow();
	}

	public boolean prev() {
		if (!hasPrev()) return false;
		int index = this.cursorPos;
		int prevPos = this.availablePos.get(index - 1);
		String cursorText = Utils.getCursorWideText(this.width, this.contain[prevPos]);
		this.setNativeText(cursorText, prevPos);
		this.removeCursor(index);
		this.cursorPos--;
		return this.staticShow();
	}

	public boolean hasNext() {
		if (this.availablePos.isEmpty()) return false;
		return this.cursorPos < this.availablePos.size() - 1;
	}

	public boolean hasPrev() {
		if (this.availablePos.isEmpty()) return false;
		return this.cursorPos > 0;
	}

	public int getCurrentCursorPos() {
		return this.cursorPos;
	}

	public void setCursorPos(int pos) {
		this.cursorPos = pos;
	}

	public void resetCursor() {
		if (this.availablePos.isEmpty()) return;
		int index = this.cursorPos;
		this.removeCursor(index);
		int prevPos = this.availablePos.get(0);
		String cursorText = Utils.getCursorWideText(this.width, this.contain[prevPos]);
		this.setNativeText(cursorText, prevPos);
		this.cursorPos = 0;
	}

	@Override
	public void focus() {
		this.isRunning = true;
		this.staticShow();
		while (this.isRunning) {
			String nextInput = this.input.getNextInput(InputType.MENU);
			if (nextInput.equals(ERROR_INPUT)) {
				continue;
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
				case "": {
					int pos = this.cursorPos;
					switch (Main.currentWindowName) {
						case MAIN_MENU: {
							int index = pos;
							Map gameMap = Store.getGameMap();
							if (gameMap != null) {
								index = index - 1;
								Main.currentWindowName = Constant.CURRENT_WINDOW.GAME;
							}
							if (index == 0) {
								Main.currentWindowName = Constant.CURRENT_WINDOW.GAME;
							} else if (index == 1) {
								Main.currentWindowName = Constant.CURRENT_WINDOW.RECORD;
							} else if (index == 2) {
								Main.isRunning = false;
							}
							break;
						}
						case RECORD: {
							if (pos == 0) {
								Repository.getInstance().clearHistory();
							} else if (pos == 1) {
								Main.currentWindowName = Constant.CURRENT_WINDOW.MAIN_MENU;
							}
							break;
						}
						case PAUSE: {
							if (pos == 0) {
								Main.isPause = true;
								Main.currentWindowName = Constant.CURRENT_WINDOW.GAME;
							} else if (pos == 1) {
								Main.isPause = false;
								Repository.getInstance().saveGameContext(Store.getGameMap());
								Main.currentWindowName = Constant.CURRENT_WINDOW.MAIN_MENU;
							}
							break;
						}
						case GAME_RESULT: {
							Main.isPause = false;
							if (pos == 0) {
								Main.currentWindowName = Constant.CURRENT_WINDOW.GAME;
							} else if (pos == 1) {
								Main.currentWindowName = Constant.CURRENT_WINDOW.MAIN_MENU;
							}
						}
						default:
					}
					this.isRunning = false;
				}
			}
		}
	}
}
