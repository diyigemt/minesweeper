package com.minesweeper;

import com.minesweeper.display.NaviWindow;
import com.minesweeper.display.Window;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    boolean running = true;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    NaviWindow window = new NaviWindow();
    window.setText("Mine Sweeper", 5);
    window.setCursorText("新游戏", 10);
    window.setCursorText("生涯", 12);
    window.setCursorText("退出", 14);
    String select = null;
    window.staticShow();
    while (running) {
      try {
        select = reader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (select == null) continue;
      switch (select) {
        case "q": {
          running = false;
          break;
        }
        case "s":
        case "S": {
          window.next();
          break;
        }
        case "w":
        case "W": {
          window.prev();
          break;
        }
        case "": {
          int pos = window.getCurrentCursorPos();
          if (pos == 2) {
            running = false;
            ClearScreen.clsCmd();
            break;
          }
        }
        default:
      }
    }
  }
}
