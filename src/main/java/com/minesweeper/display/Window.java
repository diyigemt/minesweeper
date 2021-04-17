package com.minesweeper.display;

import com.minesweeper.user.UserInput;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Store;
import com.minesweeper.utils.Utils;

public class Window {
	protected int height; //控制台窗口能显示的行数
	protected int width; //控制台窗口每行能显示的列数
	protected String[] contain; //当前待显示内容
	protected String[] prevContain; // 之前窗口内容
	private int nextPosY; //下一行位置
	private int startPosY = 0; //默认第一行的显示位置
	protected boolean isRunning = true;
	protected UserInput input = new UserInput();

	public Window() {
//		this.height = Store.getConsoleHeight();
//		this.width = Store.getConsoleWidth();
		this.width = 120;
		this.height = 50;
		this.contain = new String[this.height];
		this.nextPosY = this.startPosY = this.height / 2;
	}

	// 设置第一行显示位置 默认为屏幕中间
	public Window(int startPosY) {
		this();
		if (startPosY > this.height) startPosY = this.height / 2;
		this.nextPosY = this.startPosY = startPosY;
	}

	// 设置第一行显示位置
	public boolean setStartPosY(int startPosY) {
		if (startPosY > height) startPosY = height / 2;
		this.nextPosY = this.startPosY = startPosY;
		return true;
	}

	// 文本居中 左对齐 右对齐
	public enum TextPos {
		Center, Left, Right
	}

	/**
	 * 将文本设置在下一行居中显示
	 *
	 * @param text 要设置的文本
	 * @return 设置是否成功 当前屏幕没有下一行的时候返回false
	 */
	public boolean setText(String text) {
		return setNativeText(Utils.getCenterText(this.height, this.width, text), this.nextPosY++);
	}

	/**
	 * 将文本设置在下一行 align自行设置
	 *
	 * @param text 要设置的文本
	 * @param pos  文本align
	 * @return 设置是否成功 当前屏幕没有下一行的时候返回false
	 */
	public boolean setText(String text, TextPos pos) {
		if (!checkPos(this.nextPosY)) return false;
		switch (pos) {
			case Left:
				return setNativeText(text, this.nextPosY++);
			case Right:
				return setNativeText(Utils.getRightText(this.height, this.width, text), this.nextPosY++);
			case Center:
			default:
				setNativeText(Utils.getCenterText(this.height, this.width, text), this.nextPosY++);
		}
		return true;
	}

	/**
	 * 将文本设置在精确的位置
	 *
	 * @param text 要设置的文本
	 * @param posX 文本左缩进
	 * @param posY 文本行数位置
	 * @return 设置是否成功 当前屏幕没有下一行的时候返回false
	 */
	public boolean setText(String text, int posX, int posY) {
		String s = Utils.getPosText(this.height, this.width, text, posX);
		return setNativeText(s, posY);
	}

	/**
	 * 将文本设置在精确的位置 居中 自设行数
	 *
	 * @param text 要设置的文本
	 * @param posY 文本行数位置
	 * @return 设置是否成功 当前屏幕没有下一行的时候返回false
	 */
	public boolean setText(String text, int posY) {
		return this.setNativeText(Utils.getCenterText(this.height, this.width, text), posY);
	}

	/**
	 * 设置文本
	 *
	 * @param text 要设置的文本
	 * @param posY 文本行数位置
	 * @return 设置是否成功 当前屏幕没有下一行的时候返回false
	 */
	protected boolean setNativeText(String text, int posY) {
		if (!checkPos(posY)) return false;
		this.contain[posY] = text;
		return true;
	}

	/**
	 * 当前屏幕是否能容纳下该行
	 *
	 * @param target 测试的行数
	 * @return 无法容纳时返回false
	 */
	protected boolean checkPos(int target) {
		return target + 1 <= height;
	}

	/**
	 * 打印屏幕内容
	 *
	 * @param isCover 是否合并之前屏幕内容
	 * @return 是否打印成功
	 */
	public boolean show(boolean isCover) {
		if (isCover) {
			if (this.contain.length != this.prevContain.length) return false;
			if (this.prevContain != null) {
				int index = 0;
				for (String s : this.contain) {
					String s1 = this.prevContain[index];
					if (s == null && s1 != null) {
						this.contain[index] = s1;
					}
					index++;
				}
			}
		}
		return this.show();
	}

	/**
	 * 打印屏幕内容
	 * 打印前会清空屏幕
	 *
	 * @return 是否打印成功
	 */
	public boolean show() {
		ClearScreen.clsCmd();
		for (String s : this.contain) {
			if (s == null) {
				System.out.println("");
				continue;
			}
			System.out.println(s);
		}
		this.prevContain = this.contain.clone();
		this.contain = new String[this.height];
		this.nextPosY = this.startPosY;
		return true;
	}

	/**
	 * 打印屏幕内容且不清空屏幕
	 * @return 是否成功
	 */
	public boolean staticShow() {
		ClearScreen.clsCmd();
		for (String s : this.contain) {
			if (s == null) {
				System.out.println("");
				continue;
			}
			System.out.println(s);
		}
		return true;
	}

	/**
	 * 获取焦点
	 */
	public void focus() {
		this.isRunning = true;
	}

	/**
	 * 获取窗口高度
	 * @return 窗口高度
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 获取窗口宽度
	 * @return 窗口宽度
	 */
	public int getWidth() {
		return width;
	}
}
