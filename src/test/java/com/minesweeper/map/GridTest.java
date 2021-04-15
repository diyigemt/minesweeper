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
 * 方块构造函数的测试，参数注入，包含无效测试
 * @author duo
 *
 */
//指定参数化运行器
@RunWith(Parameterized.class)
public class GridTest {
	private Grid grid;
	//参数注入
	@Parameter
	public int row;
	@Parameter(1)
	public int col;
	@Parameter(2)
	public int rowExpect;
	@Parameter(3)
	public int colExpect;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		grid=null;
	}
	//准备测试数据
	@Parameters(name="{index}:[{0}][{1}]= [{2}][{3}]")
	public static Collection <Object[]> testData() {
		return Arrays.asList(new Object[][]{
			{0,0,0,0},
			{8,8,8,8},
			{1,4,1,4},
			{3,5,3,5},
			{9,9,-1,-1},
			{9,2,-1,2}
		});
	}
	
	//执行测试
	@Test
	public void testGrid() {
		try {
			grid = new Grid(row,col);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(grid.getRowx(), rowExpect);
		assertEquals(grid.getColy(), colExpect);
	}

}
