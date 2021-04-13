package com.minesweeper.utils;

import java.io.IOException;

public class Utils {
	private static final int DIV_POS = 10;

	private static void initConsoleSize() {
		// 获取控制台窗口宽高
    ProcessBuilder pb = new ProcessBuilder("powershell", "/c", "$host.UI.RawUI.WindowSize.Height");
    try {
      int height = pb.inheritIO().start().getInputStream().read();
      pb.command("powershell", "/c", "$host.UI.RawUI.WindowSize.Width");
      int width = pb.inheritIO().start().getInputStream().read();
      Store.setConsoleSize(height, width);
    } catch (IOException e) {
      e.printStackTrace();
    }
	}

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
			sb.append(getEmpty((int) Math.ceil(((float) width - DIV_POS) / 2)));
			sb.append(s, 0, width - DIV_POS);
			sb.append('\n');
			sb.append(getCenterText(height, width, tmp));
		} else {
			sb.append(getEmpty((int) Math.ceil(((float) width - length) / 2)));
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

	public static String getPosText(int height, int width, String s, int left) {
		int length = s.length();
		StringBuilder sb = new StringBuilder();
		sb.append(getEmpty(left));
		sb.append(s);
		return sb.toString();
	}
}
