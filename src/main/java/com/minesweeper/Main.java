package com.minesweeper;

import com.minesweeper.utils.ClearScreen;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    boolean running = true;
    Scanner scanner = new Scanner(System.in);
    while (running) {
      System.out.println("Hello");
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
//          System.out.print("\033[H\033[2J");
//          System.out.flush();
//          runtime.exec("cls");

          ClearScreen.clsCmd();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
