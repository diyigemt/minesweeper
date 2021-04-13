package com.minesweeper;

import com.minesweeper.display.Window;
import com.minesweeper.utils.ClearScreen;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    boolean running = true;
    Scanner scanner = new Scanner(System.in);
    while (running) {
      int select = scanner.nextInt();
      if (select == 1) {
        running = false;
      }
      Runtime runtime = Runtime.getRuntime();
      try {
        String s = System.getProperty("os.name").toLowerCase();
        if (s.contains("linux")) {
          runtime.exec("clear");
        } else {
          Window window = new Window();
          window.setText("Hello");
          window.show();
          window.setText("Hello2", 6);
          window.show(true);
//          window.setText("Hello3", 6);
//          window.show(false);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
