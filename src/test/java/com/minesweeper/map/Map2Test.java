package com.minesweeper.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
/**
 * 地图正确性（方格行列正确）测试，参数注入，全覆盖
 * @author duo
 *
 */
@RunWith(Parameterized.class)
public class Map2Test{
	static Map map;
	/**
	 * 参数注入
	 */
	
	@Parameter
	public int row;
	@Parameter(1)
	public int col;
	@Parameter(2)
	public int rowExpect;
	@Parameter(3)
	public int colExpect;
	
	/**
	 * 准备测试数据
	 * @return
	 */
		@Parameters(name="{index}:[{0}][{1}]= [{2}][{3}]")
		public static Collection <Object[]> testData() {
			return Arrays.asList(new Object[][]{
				{0,0,0,0},{0,1,0,1},{0,2,0,2},
				{0,3,0,3},{0,4,0,4},{0,5,0,5},
				{0,6,0,6},{0,7,0,7},{0,8,0,8},
				
				{1,0,1,0},{1,1,1,1},{1,2,1,2},
				{1,3,1,3},{1,4,1,4},{1,5,1,5},
				{1,6,1,6},{1,7,1,7},{1,8,1,8},
				
				{2,0,2,0},{2,1,2,1},{2,2,2,2},
				{2,3,2,3},{2,4,2,4},{2,5,2,5},
				{2,6,2,6},{2,7,2,7},{2,8,2,8},
				
				{3,0,3,0},{3,1,3,1},{3,2,3,2},
				{3,3,3,3},{3,4,3,4},{3,5,3,5},
				{3,6,3,6},{3,7,3,7},{3,8,3,8},
				
				{4,0,4,0},{4,1,4,1},{4,2,4,2},
				{4,3,4,3},{4,4,4,4},{4,5,4,5},
				{4,6,4,6},{4,7,4,7},{4,8,4,8},
				
				{5,0,5,0},{5,1,5,1},{5,2,5,2},
				{5,3,5,3},{5,4,5,4},{5,5,5,5},
				{5,6,5,6},{5,7,5,7},{5,8,5,8},
				
				{6,0,6,0},{6,1,6,1},{6,2,6,2},
				{6,3,6,3},{6,4,6,4},{6,5,6,5},
				{6,6,6,6},{6,7,6,7},{6,8,6,8},
			
				{7,0,7,0},{7,1,7,1},{7,2,7,2},
				{7,3,7,3},{7,4,7,4},{7,5,7,5},
				{7,6,7,6},{7,7,7,7},{7,8,7,8},
				
				{8,0,8,0},{8,1,8,1},{8,2,8,2},
				{8,3,8,3},{8,4,8,4},{8,5,8,5},
				{8,6,8,6},{8,7,8,7},{8,8,8,8},

			});
		}
		
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

	@Test
	public void testMapRow() {
		assertEquals(map.map[row][col].getRowx(),rowExpect);
	}
	@Test
	public void testMapCol() {
		assertEquals(map.map[row][col].getColy(),colExpect);
	}
	

}
