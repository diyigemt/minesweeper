package com.minesweeper;

import com.minesweeper.display.GridWindow;
import com.minesweeper.display.NaviWindow;
import com.minesweeper.display.Window;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

  private static Window currentWindow;

  public static void main(String[] args) {
    boolean running = true;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    NaviWindow mainWindow = new NaviWindow();
    mainWindow.setText("Mine Sweeper", 5);
    mainWindow.setCursorText("新游戏", 10);
    mainWindow.setCursorText("生涯", 12);
    mainWindow.setCursorText("退出", 14);
    GridWindow gameWindow = new GridWindow();
    gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s", 5);
    gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s", 6);
    gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s", 7);
    gameWindow.setCursorText("%s X %s %s X %s %s X %s %s X %s", 8);
    currentWindow = mainWindow;
    String select = null;
    currentWindow.staticShow();
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
          if (currentWindow instanceof NaviWindow) {
            ((NaviWindow)currentWindow).next();
          } else if (currentWindow instanceof GridWindow) {
            ((GridWindow)currentWindow).next();
          }
          break;
        }
        case "w":
        case "W": {
          if (currentWindow instanceof NaviWindow) {
            ((NaviWindow)currentWindow).prev();
          } else if (currentWindow instanceof GridWindow) {
            ((GridWindow) currentWindow).prev();
          }
          break;
        }
        case "a":
        case "A": {
          if (currentWindow instanceof GridWindow) {
            ((GridWindow) currentWindow).left();
          }
          break;
        }
        case "d":
        case "D": {
          if (currentWindow instanceof GridWindow) {
            ((GridWindow) currentWindow).right();
          }
          break;
        }
        case "": {
          int pos = mainWindow.getCurrentCursorPos();
          switch (pos) {
            case 0: {
              if (currentWindow == gameWindow) {
                currentWindow.staticShow();
                break;
              }
              currentWindow = gameWindow;
              currentWindow.staticShow();
              break;
            }
            case 1:{
              break;
            }
            case 2:{
              running = false;
              ClearScreen.clsCmd();
              break;
            }
            default:
          }
        }
        default:
          currentWindow.staticShow();
      }
    }
  }
}
