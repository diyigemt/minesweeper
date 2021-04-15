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
public class LayBombToMapTest {
	static Map map;
	int x;
	int y;
	int expectBombCount;
	
	//构造函数注入
	public LayBombToMapTest(int x,int y,int expectBombCount) {
		this.x = x;
		this.y = y;
		this.expectBombCount = expectBombCount;
	}
	
	@Parameterized.Parameters(name="{index}:first click is({0},{1}),bombNum= {2}")
	public static Collection<Object []> testData(){
		return Arrays.asList(new Object[][]{
			{0,0,10},
			{0,1,10},
			{0,2,10},
			{0,3,10},
			{0,4,10},
			{0,5,10},
			{0,6,10},
			{0,7,10},
			{0,8,10},
			
			{1,0,10},
			{1,1,10},
			{1,2,10},
			{1,3,10},
			{1,4,10},
			{1,5,10},
			{1,6,10},
			{1,7,10},
			{1,8,10},
			
			{2,0,10},
			{2,1,10},
			{2,2,10},
			{2,3,10},
			{2,4,10},
			{2,5,10},
			{2,6,10},
			{2,7,10},
			{2,8,10},
			
			{3,0,10},
			{3,1,10},
			{3,2,10},
			{3,3,10},
			{3,4,10},
			{3,5,10},
			{3,6,10},
			{3,7,10},
			{3,8,10},
			
			{4,0,10},
			{4,1,10},
			{4,2,10},
			{4,3,10},
			{4,4,10},
			{4,5,10},
			{4,6,10},
			{4,7,10},
			{4,8,10},
			
			{5,0,10},
			{5,1,10},
			{5,2,10},
			{5,3,10},
			{5,4,10},
			{5,5,10},
			{5,6,10},
			{5,7,10},
			{5,8,10},
			
			{6,0,10},
			{6,1,10},
			{6,2,10},
			{6,3,10},
			{6,4,10},
			{6,5,10},
			{6,6,10},
			{6,7,10},
			{6,8,10},
			
			{7,0,10},
			{7,1,10},
			{7,2,10},
			{7,3,10},
			{7,4,10},
			{7,5,10},
			{7,6,10},
			{7,7,10},
			{7,8,10},
			
			{8,0,10},
			{8,1,10},
			{8,2,10},
			{8,3,10},
			{8,4,10},
			{8,5,10},
			{8,6,10},
			{8,7,10},
			{8,8,10},
			
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
	//游戏开始后随机点开一个方格，查看游戏雷数是否为10
	public void layBombCountTest() {
//		Random random = new Random();
//		x=random.nextInt(Gate.sumRow);
//		y=random.nextInt(Gate.sumCol);
		LayBombToMap.layBomb(map.map,this.x,this.y);
		System.out.println("layBombCountTest:firstclick is:"+"("+x+" , "+y+")");
		System.out.println();
		assertEquals(expectBombCount, LayBombToMap.layBombCount);
	}
	
	@Test
	//
	public void computeCountAroundTest() {
//		Random random = new Random();
//		x=random.nextInt(Gate.sumRow);
//		y=random.nextInt(Gate.sumCol);
		LayBombToMap.layBomb(map.map, this.x,this.y);
		System.out.println("computeCountAroundTest:firstclick is:"+"("+x+" , "+y+")");
		Random random = new Random();
		x=random.nextInt(Gate.sumRow);
		y=random.nextInt(Gate.sumCol);
		System.out.println("computeCountAroundTest:computeTestGrid id"+"("+x+" , "+y+")");
		int expectCountAround = 0;
		if (map.map[x][y].isMineTag() == false) {
			for (int i = Math.max(0, x-1); i <= Math.min(
					Gate.sumRow - 1, x + 1); i++) {
				for (int j = Math.max(0, y - 1); j <= Math.min(
						Gate.sumCol - 1, y + 1); j++) {
					if (map.map[i][j].isMineTag() == true) {
						expectCountAround++;
					}
				}
			}
		}
		else expectCountAround = 9;//选择测试方块恰好为雷
		System.out.println("computeCountAroundTest:expectCountAround is:"+expectCountAround);
		System.out.println("computeCountAroundTest:realCountAround is:"+ map.map[x][y].getCountAround());
		System.out.println();
		assertEquals(expectCountAround, map.map[x][y].getCountAround());
		
	}

}
