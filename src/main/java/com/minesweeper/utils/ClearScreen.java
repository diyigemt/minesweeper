package com.minesweeper.utils;

import java.io.IOException;

public class ClearScreen {

	public static void clsCmd() {
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
		try {
			pb.inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}