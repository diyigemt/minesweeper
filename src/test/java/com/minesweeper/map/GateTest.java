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
	public void testMineSumCount() {
		assertEquals(Gate.mineSumCount, Gate.mineSumCount);
	}

	@Test
	public void testSumRow() {
		assertEquals(9, Gate.sumRow);
	}
	@Test
	public void testSumCol() {
		assertEquals(9, Gate.sumCol);
	}

}
