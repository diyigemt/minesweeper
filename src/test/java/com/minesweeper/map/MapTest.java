package com.minesweeper.map;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * 地图边界测试
 * @author duo
 *
 */
public class MapTest {

	static Map map;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		map=new Map();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		map=null;
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	/**
	 * 测试存在最小值
	 */
	@Test
	public void testMapExistMin() {
		assertNotNull(map.map[0][0]);
	}
	/**
	 * 测试存在最大值
	 */
	@Test
	public void testMapExistMax() {
		assertNotNull(map.map[Gate.sumRow-1][Gate.sumCol-1]);
	}
}
