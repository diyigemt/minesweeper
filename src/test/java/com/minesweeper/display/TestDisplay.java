package com.minesweeper.display;

import com.minesweeper.utils.Utils;
import org.junit.Test;

public class TestDisplay {
  @Test
  public void testGetFullText() {
    System.out.println(Utils.getCenterText(0, 50, "aaaa"));
  }

  @Test
  public void getCursorText() {
    System.out.println(Utils.getCursorWideText(120, "   hahaha   "));
  }
}
