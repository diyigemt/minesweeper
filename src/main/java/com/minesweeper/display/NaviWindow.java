package com.minesweeper.display;

import com.minesweeper.utils.Utils;

import java.util.ArrayList;

public class NaviWindow extends Window{
	private ArrayList<Integer> availablePos = new ArrayList<Integer>();
	private int cursorPos = -1;
	public NaviWindow() {
		super();
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
		for (int i = 0; i < this.availablePos.size() - 1; i++) {
			if (this.availablePos.get(i) < data && this.availablePos.get(i + 1) > data) {
				this.availablePos.add(i, data);
				return;
			}
		}
		this.availablePos.add(data);
	}

	private void removeCursor(int pos) {
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
		this.cursorPos = 0;
	}
}
