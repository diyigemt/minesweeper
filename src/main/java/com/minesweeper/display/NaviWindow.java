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

@SuppressWarnings("AlibabaSwitchStatement")
public class NaviWindow extends Window{
	private ArrayList<Integer> availablePos = new ArrayList<Integer>(); // 存储属于光标项目的位置
	private int cursorPos = -1; // 当前光标位置
	// 创建默认窗体 提供操作提示
	public NaviWindow() {
		super();
		this.setText("请输入 s w 进行菜单导航, enter 进行输入确认和进入菜单项, q 退出程序", 0, 40);
	}
	public NaviWindow(int startPosY) {
		super(startPosY);
	}

	/**
	 * 获取光标位置
	 * @return 光标位置
	 */
	public ArrayList<Integer> getAvailablePos() {
		return availablePos;
	}

	/**
	 * 设置光标位置
	 * @param availablePos 可用的光标位置
	 */
	public void setAvailablePos(ArrayList<Integer> availablePos) {
		this.availablePos = availablePos;
	}

	/**
	 * 添加一个可用的光标位置
	 * @param pos 可用的光标位置
	 */
	public boolean addAvailablePos(int pos) {
		String tmp = this.contain[pos];
		if (tmp == null) {
			return false;
		}
		this.sortAdd(pos);
		return true;
	}

	/**
	 * 插入排序 保证光标的正确性
	 * @param data 插入的posY位置
	 */
	private void sortAdd(int data) {
		if (this.availablePos.isEmpty()) {
			this.availablePos.add(data);
			return;
		}
		if (this.availablePos.contains(data)) {
			return;
		}
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

	/**
	 * 清除一个位置的光标效果
	 * @param pos 要清除的位置
	 */
	public void removeCursor(int pos) {
		int index = this.availablePos.get(pos);
		String tmp = this.contain[index];
		if (tmp == null) {
			return;
		}
		tmp = tmp.replace(">>", "  ").replace("<<", "  ");
		this.contain[index] = tmp;
	}

	/**
	 * 添加一个可用的光标菜单项
	 * @param target 菜单项
	 * @param posY 垂直位置
	 * @return 是否添加成功
	 */
	public boolean setCursorText(String target, int posY) {
		if (!this.checkPos(posY)) {
			return false;
		}
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

	/**
	 * 移动光标到下一个位置
	 * @return 是否移动成功
	 */
	public boolean next() {
		if (!hasNext()) {
			return false;
		}
		int index = this.cursorPos;
		int nextPos = this.availablePos.get(index + 1);
		String cursorText = Utils.getCursorWideText(this.width, this.contain[nextPos]);
		this.setNativeText(cursorText, nextPos);
		this.removeCursor(index);
		this.cursorPos++;
		return this.staticShow();
	}

	/**
	 * 移动光标到前一个位置
	 * @return 是否移动成功
	 */
	public boolean prev() {
		if (!hasPrev()) {
			return false;
		}
		int index = this.cursorPos;
		int prevPos = this.availablePos.get(index - 1);
		String cursorText = Utils.getCursorWideText(this.width, this.contain[prevPos]);
		this.setNativeText(cursorText, prevPos);
		this.removeCursor(index);
		this.cursorPos--;
		return this.staticShow();
	}

	/**
	 * 判断光标是否还可以往下移动
	 * @return 是否可以移动
	 */
	public boolean hasNext() {
		if (this.availablePos.isEmpty()) {
			return false;
		}
		return this.cursorPos < this.availablePos.size() - 1;
	}

	/**
	 * 判断光标是否还可以往前移动
	 * @return 是否可以移动
	 */
	public boolean hasPrev() {
		if (this.availablePos.isEmpty()) {
			return false;
		}
		return this.cursorPos > 0;
	}

	/**
	 * 获取当前光标位置
	 * @return 当前光标位置
	 */
	public int getCurrentCursorPos() {
		return this.cursorPos;
	}

	/**
	 * 设置光标位置
	 * @param pos 设置值
	 */
	public void setCursorPos(int pos) {
		if (this.checkPos(pos) && this.availablePos.size() > pos) {
			this.cursorPos = pos;
		}
	}

	/**
	 * 重置光标位置到第一个菜单项
	 */
	public void resetCursor() {
		if (this.availablePos.isEmpty()) {
			return;
		}
		int index = this.cursorPos;
		this.removeCursor(index);
		int prevPos = this.availablePos.get(0);
		String cursorText = Utils.getCursorWideText(this.width, this.contain[prevPos]);
		this.setNativeText(cursorText, prevPos);
		this.cursorPos = 0;
	}

	/**
	 * 获取焦点 接管用户输入
	 */
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
