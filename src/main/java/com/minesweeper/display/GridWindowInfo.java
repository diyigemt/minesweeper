package com.minesweeper.display;

import java.util.ArrayList;

/**
 * 网格型窗体信息
 * @author diyigemt
 */
public class GridWindowInfo {
  private ArrayList<Integer> array = new ArrayList<Integer>(); // 可用的x坐标集合
  private int posY = -1; // 自己的y坐标

  public GridWindowInfo() {

  }

  /**
   * 创建时设置自己的y坐标
   * @param posY 设置的y坐标
   */
  public GridWindowInfo(int posY) {
    this.posY = posY;
  }

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
