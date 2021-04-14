package com.minesweeper.display;

import java.util.ArrayList;
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
    if (posCount == 0) return false;
    if (this.cursorPosY == -1) {
      this.cursorPosY = 0;
      this.cursorPosX = 0;
    }
    if (this.availablePos.isEmpty()) {
      ArrayList<GridWindowInfo> array = new ArrayList<GridWindowInfo>();
      GridWindowInfo info = new GridWindowInfo();
      info.setArray(findAllPosX(text));
    }
  }

  public boolean setCursorText(String text, int posY, int posX, int posCount) {

  }

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
      text = text.replace("%s", "");
      text = text.replace("%s", "");
      res.add(index + 2);
    }
    return res;
  }

  private boolean checkPos(int posY, int posX) {
    return posY + 1 <= height && posX + 1 <= width;
  }
}
