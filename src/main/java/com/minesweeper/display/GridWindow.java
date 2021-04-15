package com.minesweeper.display;

import com.minesweeper.map.Grid;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridWindow extends Window {
	private int cursorPosX = -1;
	private int cursorPosY = -1;
	private ArrayList<GridWindowInfo> availablePos = new ArrayList<GridWindowInfo>();

	public GridWindow() {
		super();
	}

	public int getCurrentPosX() {
		return this.cursorPosX;
	}

	public int getCurrentPosY() {
		return this.cursorPosY;
	}

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

//  public boolean setCursorText(String text, int posY, int posX, int posCount) {
//
//  }

	private int getPosXCount(String text) {
		Matcher matcher = Pattern.compile("%s").matcher(text);
		int res = 0;
		while (matcher.find()) {
			res++;
		}
		return res / 2;
	}

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

	private boolean checkPos(int posY, int posX) {
		return posY + 1 <= height && posX + 1 <= width;
	}

	public boolean next() {
		if (!hasNext()) return false;
		this.cursorPosY++;
		return this.staticShow();
	}

	public boolean prev() {
		if (!hasPrev()) return false;
		this.cursorPosY--;
		return this.staticShow();
	}

	public boolean right() {
		if (!hasRight()) return false;
		this.cursorPosX++;
		return this.staticShow();
	}

	public boolean left() {
		if (!hasLeft()) return false;
		this.cursorPosX--;
		return this.staticShow();
	}

	public boolean hasNext() {
		if (this.availablePos.isEmpty()) return false;
		return this.cursorPosY < this.availablePos.size() - 1;
	}

	public boolean hasPrev() {
		if (this.availablePos.isEmpty()) return false;
		return this.cursorPosY > 0;
	}

	public boolean hasRight() {
		ArrayList<Integer> array = this.availablePos.get(this.cursorPosY).getArray();
		if (array.isEmpty()) return false;
		return this.cursorPosX < array.size() - 1;
	}

	public boolean hasLeft() {
		ArrayList<Integer> array = this.availablePos.get(this.cursorPosY).getArray();
		if (array.isEmpty()) return false;
		return this.cursorPosX > 0;
	}

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

	public boolean setGameMap(Grid[][] map) {
		int index = 5;
		for (Grid[] ys: map) {
			StringBuilder sb = new StringBuilder();
			for (Grid x : ys) {
				sb.append("%s ");
				if (x.isExpendTag()) {
					int i = x.getCountAround();
					if (i == 9) {
						sb.append("M %s ");
					} else {
						sb.append(i).append(" %s ");
					}
				} else if (x.isFlagTag()) {
					sb.append("F %s ");
				} else if (x.isMineTag()) {
					sb.append("M %s ");
				} else {
					sb.append("X %s ");
				}
				this.setCursorText(sb.toString(), index++);
			}
		}
		return true;
	}
}
