package com.minesweeper.utils;

import java.io.IOException;
/**
 * @author diyigemt
 */
public class Utils {
	private static final int DIV_POS = 10;

	/**
	 * 使用powershell的API获取控制台宽高
	 */
	private static void initConsoleSize() {
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

	/**
	 * 返回定长空字符串
	 * @param length 长度
	 * @return 空串
	 */
	private static String getEmpty(int length) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < length) {
			sb.append(" ");
			i++;
		}
		return sb.toString();
	}

	/**
	 * a / b 并向下取整
	 * @param a 除数
	 * @param b 被除数
	 * @return 向下取整的结果
	 */
	private static int divAndCeil(int a, int b) {
		return (int) Math.ceil(((float) a) / b);
	}

	/**
	 * 获取居中字符串
	 * @param height 窗口高度
	 * @param width 窗口宽度
	 * @param s 原字符串
	 * @return 居中的字符串
	 */
	public static String getCenterText(int height, int width, String s) {
		s = s.trim();
		int length = s.length();
		StringBuilder sb = new StringBuilder();
		if (length > width) {
			String tmp = s.substring(width - DIV_POS);
			sb.append(getEmpty(divAndCeil(width - DIV_POS, 2)));
			sb.append(s, 0, width - DIV_POS);
			sb.append('\n');
			sb.append(getCenterText(height, width, tmp));
		} else {
			sb.append(getEmpty(divAndCeil((width - length), 2)));
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 获取靠右字符串
	 * @param height 窗口高度
	 * @param width 窗口宽度
	 * @param s 原字符串
	 * @return 靠右的字符串
	 */
	public static String getRightText(int height, int width, String s) {
		int length = s.length();
		StringBuilder sb = new StringBuilder();
		sb.append(getEmpty(width - length));
		sb.append(s);
		return sb.toString();
	}

	/**
	 * 获取指定位置的字符串
	 * @param height 窗口高度
	 * @param width 窗口宽度
	 * @param s 原字符串
	 * @param left 左对齐位置
	 * @return 结果字符串
	 */
	public static String getPosText(int height, int width, String s, int left) {
		int length = s.length();
		StringBuilder sb = new StringBuilder();
		sb.append(getEmpty(left));
		sb.append(s);
		return sb.toString();
	}

	/**
	 * 获取比较宽的有光标的字符串
	 * @param width 窗口宽度
	 * @param s 原字符串
	 * @return 添加了光标的字符串
	 */
	public static String getCursorWideText(int width, String s) {
		return getCursorText(width, s, 2);
	}

	/**
	 * 获取比较窄的有光标的字符串
	 * @param width 窗口宽度
	 * @param s 原字符串
	 * @return 添加了光标的字符串
	 */
	public static String getCursorNarrowText(int width, String s) {
		return getCursorText(width, s, 1);
	}

	/**
	 * 获取指定宽度的有光标的字符串
	 * @param width 窗口宽度
	 * @param s 原字符串
	 * @param space 指定的宽度
	 * @return 添加了光标的字符串
	 */
	private static String getCursorText(int width, String s, int space) {
		if (s == null || s.equals("")) return ">> <<";
		StringBuilder sb = new StringBuilder();
		String trim = s.trim();
		sb.append(">>").append(getEmpty(space)).append(trim).append(getEmpty(space)).append("<<");
		return getCenterText(0, width, sb.toString());
	}
}
