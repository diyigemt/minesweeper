package com.minesweeper.display;

public class WindowManager {
	private Window nowWindow;
	private Window prevWindow;

	public void show() {
		if (this.nowWindow != null) this.nowWindow.show();
	}
}
