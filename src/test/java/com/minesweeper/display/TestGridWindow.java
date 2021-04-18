package com.minesweeper.display;

import com.minesweeper.display.GridWindow;
import com.minesweeper.map.LayBombToMap;
import com.minesweeper.map.Map;
import com.minesweeper.map.OpenGrid;
import org.junit.Assert;
import org.junit.Test;

public class TestGridWindow {
	@Test
	public void testGridWindowSetText() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.staticShow();
	}

	@Test
	public void testGridWindowLeft() {
		GridWindow window = new GridWindow();
    window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.staticShow();
		Assert.assertFalse(window.left());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.left());
	}

	@Test
	public void testGridWindowRight() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.staticShow();
		Assert.assertFalse(window.left());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.right());
		Assert.assertFalse(window.right());
		Assert.assertTrue(window.left());
	}

	@Test
	public void testGridWindowNext() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.setCursorText("%sHell%s %sHell%s %sHell%s", 6);
		window.setCursorText("%sDown Test%s", 7);
		window.staticShow();
		Assert.assertFalse(window.left());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.left());
		Assert.assertTrue(window.next());
		Assert.assertTrue(window.next());
	}

	@Test
	public void testGridWindowNext2() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sDown Test%s", 5);
		window.setCursorText("Down Test", 6);
		window.staticShow();
		Assert.assertTrue(window.next());
	}

	@Test
	public void testGridWindowPrev() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.setCursorText("%sHell%s %sHell%s %sHell%s", 6);
		window.setCursorText("%sDown Test%s", 7);
		window.staticShow();
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.next());
		Assert.assertTrue(window.next());
		Assert.assertFalse(window.next());
		Assert.assertTrue(window.prev());
	}

	@Test
	public void testGridWindowPrev2() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.setCursorText("%sHell%s %sHell%s %sHell%s", 6);
		window.setCursorText("%sDown Test%s", 7);
		window.staticShow();
		Assert.assertTrue(window.right());
		Assert.assertTrue(window.next());
		Assert.assertTrue(window.next());
		Assert.assertFalse(window.next());
		Assert.assertTrue(window.prev());
	}

	@Test
	public void testResetCursor() {
		GridWindow window = new GridWindow();
		window.setCursorText("%sHello%s %sHello%s %sHello%s", 5);
		window.setCursorText("%sHell%s %sHell%s %sHell%s", 6);
		window.setCursorText("%sDown Test%s", 7);
		window.staticShow();
		Assert.assertTrue(window.right());
		Assert.assertEquals(window.getCurrentPosX(), 1);
		Assert.assertEquals(window.getCurrentPosY(), 0);
	}

	@Test
	public void testGameMap() {
		GameWindow window = new GameWindow();
		Map map = new Map();
		LayBombToMap.layBomb(map.map, 0, 0);
		OpenGrid grid = new OpenGrid();
		grid.openGrid(map.map, 0, 0);
		window.setGameMap(map.map);
		window.staticShow();
	}
}
