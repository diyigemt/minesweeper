package com.minesweeper.display;

import com.minesweeper.map.Map;
import com.minesweeper.utils.ClearScreen;
import com.minesweeper.utils.Store;
import com.minesweeper.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

public class TestUtils {
	@Test
	public void testCls() {
		ClearScreen.clsCmd();
	}

	@Test
	public void testStoreWindow() {
		Store.setConsoleSize(100, 100);
		Assert.assertEquals(Store.getConsoleHeight(), 100);
		Assert.assertEquals(Store.getConsoleWidth(), 100);
	}

	@Test
	public void testStoreGameMap() {
		Map map = new Map();
		Store.setGameMap(map);
		Assert.assertEquals(Store.getGameMap(), map);
	}

	@Test
	public void testEmptyString() {
		Assert.assertEquals(Utils.getEmpty(0), "");
		Assert.assertEquals(Utils.getEmpty(5), "     ");
		Assert.assertEquals(Utils.getEmpty(-1), "");
	}

	@Test
	public void testDiv() {
		Assert.assertEquals(Utils.divAndCeil(5, 1), 5);
		Assert.assertEquals(Utils.divAndCeil(5, 2), 2);
		Assert.assertEquals(Utils.divAndCeil(5, 3), 1);
	}

	@Test
	public void testTextPos() {
		Assert.assertEquals(Utils.getCenterText(0, 5, "a"), "  a");
		Assert.assertEquals(Utils.getCenterText(0, 6, "a"), "  a");
		Assert.assertEquals(Utils.getCenterText(0, 5, "aa"), " aa");
		Assert.assertEquals(Utils.getCenterText(0, 6, "aa"), "  aa");
		Assert.assertEquals(Utils.getRightText(0, 5, "aa"), "   aa");
		Assert.assertEquals(Utils.getRightText(0, 6, "aa"), "    aa");
		Assert.assertEquals(Utils.getPosText(0, 6, "aa", 2), "  aa");
		Assert.assertEquals(Utils.getPosText(0, 6, "aa", 8), "");
	}

	@Test
	public void testTextCursor() {
		Assert.assertEquals(Utils.getCursorWideText(10, "aa"), ">>  aa  <<");
		Assert.assertEquals(Utils.getCursorWideText(10, "aaa"), ">> <<");
		Assert.assertEquals(Utils.getCursorWideText(12, "aaa"), ">>  aaa  <<");
		Assert.assertEquals(Utils.getCursorNarrowText(10, "aa"), " >> aa <<");
		Assert.assertEquals(Utils.getCursorNarrowText(10, "aaa"), ">> aaa <<");
		Assert.assertEquals(Utils.getCursorNarrowText(12, "aaa"), " >> aaa <<");
	}
}
