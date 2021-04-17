package com.minesweeper.display;

import com.minesweeper.utils.ClearScreen;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 网格型窗体
 * @author diyigemt
 */
public class GridWindow extends Window {
	protected int cursorPosX = -1; // 光标x坐标
	protected int cursorPosY = -1; // 光标y坐标
	private ArrayList<GridWindowInfo> availablePos = new ArrayList<GridWindowInfo>(); // 可用的菜单项坐标列表

	public GridWindow() {
		super();
	}

	public int getCurrentPosX() {
		return this.cursorPosX;
	}

	public int getCurrentPosY() {
		return this.cursorPosY;
	}

	/**
	 * 添加一个可用的光标菜单项
	 * << text中必须将可用的指针位置用 '%s %s' 包裹起来 显示时会用指针代替'%s' >>
	 * @param text 菜单项
	 * @param posY 垂直位置
	 * @return 是否添加成功
	 */
	public boolean setCursorText(String text, int posY) {
		if (!this.checkPos(posY)) return false;
		int posCount = getPosXCount(text);
		if (posCount == 0) {
			text = "%s" + text + "%s";
		}
		if (this.cursorPosY == -1) {
			this.cursorPosY = 0;
			this.cursorPosX = 0;
		}
		ArrayList<Integer> allPosX = findAllPosX(text);
		if (this.availablePos.isEmpty()) {
			GridWindowInfo info = new GridWindowInfo();
			info.setArray(allPosX);
			info.setPosY(posY);
			this.availablePos.add(info);
		} else {
			this.sortAdd(allPosX, posY);
		}
		this.setText(text, posY);
		return true;
	}

	/**
	 * 计算可用的x方向菜单数量
	 * @param text 菜单内容
	 * @return 可用的数量
	 */
	private int getPosXCount(String text) {
		Matcher matcher = Pattern.compile("%s").matcher(text);
		int res = 0;
		while (matcher.find()) {
			res++;
		}
		return res / 2;
	}

	/**
	 * 根据菜单项内容获取全部的可用x方向菜单位置
	 * @param text 菜单内容
	 * @return 可用x方向菜单位置
	 */
	private ArrayList<Integer> findAllPosX(String text) {
		if (text == null || text.equals("")) return null;
		ArrayList<Integer> res = new ArrayList<Integer>();
		while (text.contains("%s")) {
			int index = text.indexOf("%s");
			text = text.replaceFirst("%s", "");
			text = text.replaceFirst("%s", "");
			res.add(index + 2);
		}
		return res;
	}

	/**
	 * 获取菜单项index对应的屏幕位置
	 * @param posY 菜单项index
	 * @return 屏幕位置
	 */
	private int getPosYInfoIndex(int posY) {
		int res = -1;
		for (int i = 0; i < this.availablePos.size(); i++) {
			if (this.availablePos.get(i).getPosY() == posY) {
				res = i;
				break;
			}
		}
		return res;
	}

	/**
	 * 排序插入 菜单项的位置正确
	 * @param list 可用x方向菜单位置
	 * @param posY 菜单y位置
	 */
	private void sortAdd(ArrayList<Integer> list, int posY) {
		int index = getPosYInfoIndex(posY);
		if (index != -1) {
			GridWindowInfo tmp = this.availablePos.get(index);
			tmp.setArray(list);
			return;
		}
		for (int i = 0; i < this.availablePos.size() - 1; i++) {
			if (this.availablePos.get(i).getPosY() < posY && this.availablePos.get(i + 1).getPosY() > posY) {
				index = i;
				break;
			}
		}
		GridWindowInfo info = new GridWindowInfo(posY);
		info.setPosY(posY);
		info.setArray(list);
		if (index != -1) {
			this.availablePos.add(index, info);
		} else {
			this.availablePos.add(info);
		}
	}

	/**
	 * 检查菜单位置是否合法
	 * @param posY y位置
	 * @param posX x位置
	 * @return 是否合法
	 */
	private boolean checkPos(int posY, int posX) {
		return posY + 1 <= height && posX + 1 <= width;
	}

	/**
	 * 移动光标到下一个位置
	 * @return 是否移动成功
	 */
	public boolean next() {
		if (!hasNext()) return false;
		this.cursorPosY++;
		return this.staticShow();
	}

	/**
	 * 移动光标到前一个位置
	 * @return 是否移动成功
	 */
	public boolean prev() {
		if (!hasPrev()) return false;
		this.cursorPosY--;
		return this.staticShow();
	}

	/**
	 * 移动光标到右一个位置
	 * @return 是否移动成功
	 */
	public boolean right() {
		if (!hasRight()) return false;
		this.cursorPosX++;
		return this.staticShow();
	}

	/**
	 * 移动光标到左一个位置
	 * @return 是否移动成功
	 */
	public boolean left() {
		if (!hasLeft()) return false;
		this.cursorPosX--;
		return this.staticShow();
	}

	/**
	 * 判断光标是否还可以往下移动
	 * @return 是否可以移动
	 */
	public boolean hasNext() {
		if (this.availablePos.isEmpty()) return false;
		return this.cursorPosY < this.availablePos.size() - 1;
	}

	/**
	 * 判断光标是否还可以往前移动
	 * @return 是否可以移动
	 */
	public boolean hasPrev() {
		if (this.availablePos.isEmpty()) return false;
		return this.cursorPosY > 0;
	}

	/**
	 * 判断光标是否还可以往右移动
	 * @return 是否可以移动
	 */
	public boolean hasRight() {
		ArrayList<Integer> array = this.availablePos.get(this.cursorPosY).getArray();
		if (array.isEmpty()) return false;
		return this.cursorPosX < array.size() - 1;
	}

	/**
	 * 判断光标是否还可以往左移动
	 * @return 是否可以移动
	 */
	public boolean hasLeft() {
		ArrayList<Integer> array = this.availablePos.get(this.cursorPosY).getArray();
		if (array.isEmpty()) return false;
		return this.cursorPosX > 0;
	}

	/**
	 * 打印屏幕内容且不清空屏幕
	 * 同时根据光标位置打印光标
	 * @return 是否成功
	 */
	@Override
	public boolean staticShow() {
		ClearScreen.clsCmd();
		GridWindowInfo info = this.availablePos.get(this.cursorPosY);
		int posY = info.getPosY();
		int posX = this.cursorPosX;
		for (int i = 0; i < this.contain.length; i++) {
			String s = this.contain[i];
			if (s == null) {
				System.out.println("");
				continue;
			}
			if (i == posY) {
				int size = info.getArray().size() - 1;
				if (posX > size) posX = size;
				int k = 0;
				while (s.contains("%s")) {
					if (k == posX) {
						s = s.replaceFirst("%s", ">>");
						s = s.replaceFirst("%s", "<<");
						break;
					}
					s = s.replaceFirst("%s", "  ");
					s = s.replaceFirst("%s", "  ");
					k++;
				}
			}
			System.out.println(s.replaceAll("%s", "  "));
		}
		return true;
	}
}
