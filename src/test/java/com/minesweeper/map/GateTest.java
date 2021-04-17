package com.minesweeper.map;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 地图参数（行数、列数、总雷数）测试
 * @author duo
 *
 */
public class GateTest {

	@Test
<<<<<<< HEAD
	public void mineSumCountTest() {
=======
	public void testMineSumCount() {
>>>>>>> origin/main
		assertEquals(10, Gate.mineSumCount);
	}
	
	@Test
<<<<<<< HEAD
	public void sumRowTest() {
		assertEquals(9, Gate.sumRow);
	}
	@Test
	public void sumColTest() {
=======
	public void testSumRow() {
		assertEquals(9, Gate.sumRow);
	}
	@Test
	public void testSumCol() {
>>>>>>> origin/main
		assertEquals(9, Gate.sumCol);
	}

}
