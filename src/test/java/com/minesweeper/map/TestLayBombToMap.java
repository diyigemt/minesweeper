package com.minesweeper.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
/**
 * 布置雷函数测试，构造函数注入，
 * 测试1：第一次点击后，测试生成雷数是否为10，为全覆盖测试
 * 测试2：翻开一个方块，查看周围雷数是否正确
 * @author duo
 *
 */
@RunWith(Parameterized.class)
public class TestLayBombToMap {
	static Map map;
	int x;
	int y;
	int expectBombCount;

	/**
	 * 构造函数注入
	 * @param x
	 * @param y
	 * @param expectBombCount
	 */
	public TestLayBombToMap(int x, int y, int expectBombCount) {
		this.x = x;
		this.y = y;
		this.expectBombCount = expectBombCount;
	}
	
	@Parameterized.Parameters(name="{index}:first click is({0},{1}),bombNum= {2}")
	public static Collection<Object []> testData(){
		return Arrays.asList(new Object[][]{
				{0,0,Gate.mineSumCount},{0,1,Gate.mineSumCount},{0,2,Gate.mineSumCount},
				{0,3,Gate.mineSumCount},{0,4,Gate.mineSumCount},{0,5,Gate.mineSumCount},
				{0,6,Gate.mineSumCount},{0,7,Gate.mineSumCount},{0,8,Gate.mineSumCount},

				{1,0,Gate.mineSumCount},{1,1,Gate.mineSumCount},{1,2,Gate.mineSumCount},
				{1,3,Gate.mineSumCount},{1,4,Gate.mineSumCount},{1,5,Gate.mineSumCount},
				{1,6,Gate.mineSumCount},{1,7,Gate.mineSumCount},{1,8,Gate.mineSumCount},

				{2,0,Gate.mineSumCount},{2,1,Gate.mineSumCount},{2,2,Gate.mineSumCount},
				{2,3,Gate.mineSumCount},{2,4,Gate.mineSumCount},{2,5,Gate.mineSumCount},
				{2,6,Gate.mineSumCount},{2,7,Gate.mineSumCount},{2,8,Gate.mineSumCount},

				{3,0,Gate.mineSumCount},{3,1,Gate.mineSumCount},{3,2,Gate.mineSumCount},
				{3,3,Gate.mineSumCount},{3,4,Gate.mineSumCount},{3,5,Gate.mineSumCount},
				{3,6,Gate.mineSumCount},{3,7,Gate.mineSumCount},{3,8,Gate.mineSumCount},

				{4,0,Gate.mineSumCount},{4,1,Gate.mineSumCount},{4,2,Gate.mineSumCount},
				{4,3,Gate.mineSumCount},{4,4,Gate.mineSumCount},{4,5,Gate.mineSumCount},
				{4,6,Gate.mineSumCount},{4,7,Gate.mineSumCount},{4,8,Gate.mineSumCount},

				{5,0,Gate.mineSumCount},{5,1,Gate.mineSumCount},{5,2,Gate.mineSumCount},
				{5,3,Gate.mineSumCount},{5,4,Gate.mineSumCount},{5,5,Gate.mineSumCount},
				{5,6,Gate.mineSumCount},{5,7,Gate.mineSumCount},{5,8,Gate.mineSumCount},

				{6,0,Gate.mineSumCount},{6,1,Gate.mineSumCount},{6,2,Gate.mineSumCount},
				{6,3,Gate.mineSumCount},{6,4,Gate.mineSumCount},{6,5,Gate.mineSumCount},
				{6,6,Gate.mineSumCount},{6,7,Gate.mineSumCount},{6,8,Gate.mineSumCount},

				{7,0,Gate.mineSumCount},{7,1,Gate.mineSumCount},{7,2,Gate.mineSumCount},
				{7,3,Gate.mineSumCount},{7,4,Gate.mineSumCount},{7,5,Gate.mineSumCount},
				{7,6,Gate.mineSumCount},{7,7,Gate.mineSumCount},{7,8,Gate.mineSumCount},

				{8,0,Gate.mineSumCount},{8,1,Gate.mineSumCount},{8,2,Gate.mineSumCount},
				{8,3,Gate.mineSumCount},{8,4,Gate.mineSumCount},{8,5,Gate.mineSumCount},
				{8,6,Gate.mineSumCount},{8,7,Gate.mineSumCount},{8,8,Gate.mineSumCount},

		});
		
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		map = new Map();
	}

	@After
	public void tearDown() throws Exception {
		map = null;
	}
	@Test
	/**
	 * 游戏开始后随机点开一个方格，查看游戏雷数是否为10
	 */
	public void testLayBombCount() {
		int res = LayBombToMap.layBomb(map.map,this.x,this.y);
		System.out.println("layBombCountTest:firstclick is:"+"("+x+" , "+y+")");
		System.out.println();
		assertEquals(expectBombCount, res);
	}
	
	@Test
	/**
	 * 计算周围雷数函数测试
	 */
	public void testComputeCountAround() {
		LayBombToMap.layBomb(map.map, x,y);
		System.out.println("computeCountAroundTest:firstclick is:"+"("+x+" , "+y+")");
		Random random = new Random();
		int a=random.nextInt(Gate.sumRow);
		int b=random.nextInt(Gate.sumCol);
		System.out.println("computeCountAroundTest:computeTestGrid id"+"("+a+" , "+b+")");
		int expectCountAround = 0;
		if (map.map[a][b].isMineTag() == false) {
			for (int i = Math.max(0, a-1); i <= Math.min(Gate.sumRow - 1, a+ 1); i++) {
				for (int j = Math.max(0, b- 1); j <= Math.min(Gate.sumCol - 1, b + 1); j++) {
					if (map.map[i][j].isMineTag() == true) {
						expectCountAround++;
					}
				}
			}
		}
		else {
			//选择测试方块恰好为雷
			expectCountAround = 9;
		}
		
		System.out.println("computeCountAroundTest:expectCountAround is:"+expectCountAround);
		System.out.println("computeCountAroundTest:realCountAround is:"+ map.map[a][b].getCountAround());
		System.out.println();
		assertEquals(expectCountAround, map.map[a][b].getCountAround());
		
	}

}
