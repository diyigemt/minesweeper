package com.minesweeper.display;

import java.util.ArrayList;

public class GridWindowInfo {
  private ArrayList<Integer> array = new ArrayList<Integer>();
  private int posY = -1;

  public ArrayList<Integer> getArray() {
    return array;
  }

  public void setArray(ArrayList<Integer> array) {
    this.array = array;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }
}
