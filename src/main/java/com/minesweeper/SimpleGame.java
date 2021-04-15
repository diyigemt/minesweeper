package com.minesweeper;

import com.minesweeper.map.Grid;
import com.minesweeper.map.LayBombToMap;
import com.minesweeper.map.Map;
import com.minesweeper.map.OpenGrid;

import java.util.Scanner;

public class SimpleGame {
  public static void main(String[] args) throws Exception {
    Map map = new Map();
    LayBombToMap.layBomb(map.map, 0, 0);
    OpenGrid grid = new OpenGrid();
    grid.openGrid(map.map, 0, 0);
    Scanner scanner = new Scanner(System.in);
    while (!grid.isGameOver()) {
      printMap(map.map);
      int x = scanner.nextInt();
      int y = scanner.nextInt();
      grid.openGrid(map.map, x, y);
    }
    System.out.println("game over");
    printMap(map.map);
  }
  public static void printMap(Grid[][] map) {
    System.out.println("    0  1  2  3  4  5  6  7  8");
    System.out.println("    -------------------------");
    int index = 0;
    for (Grid[] ys : map) {
      System.out.print((index++) + " | ");
      for (Grid x : ys) {
        if (x.isExpendTag()) {
          int i = x.getCountAround();
          System.out.print(i == 9 ? "M  ": i + "  ");
        } else if (x.isFlagTag()) {
          System.out.print("F  ");
        } else if (x.isMineTag()) {
          System.out.print("M  ");
        } else {
          System.out.print("X  ");
        }
      }
      System.out.println("");
    }
  }
}
