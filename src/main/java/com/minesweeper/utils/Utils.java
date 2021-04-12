package com.minesweeper.utils;

public class Utils {
  private static int DIV_POS = 10;
  private static String getEmpty(int length) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < length) {
      sb.append(" ");
      i++;
    }
    return sb.toString();
  }

  public static String getCenterText(int height, int width, String s) {
    int length = s.length();
    StringBuilder sb = new StringBuilder();
    if (length > width) {
      String tmp = s.substring(width - DIV_POS);
      sb.append(getEmpty((int)Math.ceil(((float) width - DIV_POS) / 2)));
      sb.append(s, 0, width - DIV_POS);
      sb.append('\n');
      sb.append(getEmpty((int)Math.ceil(((float) width - tmp.length()) / 2)));
      sb.append(tmp);
    } else {
      sb.append(getEmpty((int)Math.ceil(((float) width - length) / 2)));
      sb.append(s);
    }
    return sb.toString();
  }

  public static String getRightText(int height, int width, String s) {
    int length = s.length();
    StringBuilder sb = new StringBuilder();
    sb.append(getEmpty(width - length));
    sb.append(s);
    return sb.toString();
  }
}
