package com.minesweeper.display;

import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Utils;

import java.io.IOException;

public class Window {
  private int height;
  private int width;
  private String[] contain;
  private int nextPosY;
  private int startPosY = 0;
  public Window() {
//    ProcessBuilder pb = new ProcessBuilder("powershell", "/c", "$host.UI.RawUI.WindowSize.Height");
//    try {
//      height = pb.inheritIO().start().getInputStream().read();
//      pb.command("powershell", "/c", "$host.UI.RawUI.WindowSize.Width");
//      width = pb.inheritIO().start().getInputStream().read();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    this.width = 120;
    this.height = 50;
    this.contain = new String[this.height];
    this.nextPosY = this.height / 2;
  }
  public Window(int startPosY) {
    this();
    if (startPosY > this.height) startPosY = this.height / 2;
    this.nextPosY = this.startPosY = startPosY;
  }
  public boolean setStartPosY(int startPosY) {
    if (startPosY > height) startPosY = height / 2;
    this.nextPosY = this.startPosY = startPosY;
    return true;
  }
  public enum TextPos {
    Center, Left, Right
  }
  public boolean setText(String text) {
    if (!checkPos(this.nextPosY)) return false;
    this.contain[this.nextPosY++] = Utils.getCenterText(this.height, this.width, text);
    return true;
  }
  public boolean setText(String text, TextPos pos) {
    if (!checkPos(this.nextPosY)) return false;
    switch (pos) {
      case Left: this.contain[this.nextPosY++] = text; break;
      case Right: this.contain[this.nextPosY++] = Utils.getRightText(this.height, this.width, text); break;
      case Center:
      default: setText(text);
    }
    return true;
  }
  public boolean setText(String text, int posY) {
    if (!checkPos(this.nextPosY)) return false;
    this.contain[posY] = text;
    return true;
  }
  private boolean checkPos(int target) {
    return target + 1 <= height;
  }
  public void show() {
    ClearScreen.clsCmd();
    for (String s : this.contain) {
      if (s == null) {
        System.out.println("");
        continue;
      }
      System.out.println(s);
    }
  }
}
