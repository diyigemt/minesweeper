package com.minesweeper.map;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
<<<<<<< HEAD

import java.util.Scanner;

=======
>>>>>>> origin/main
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
<<<<<<< HEAD
	//测试存在最小值
=======
	/**
	 * 测试存在最小值
	 */
>>>>>>> origin/main
	@Test
	public void testMapExistMin() {
		assertNotNull(map.map[0][0]);
	}
<<<<<<< HEAD
	//测试存在最大值
=======
	/**
	 * 测试存在最大值
	 */
>>>>>>> origin/main
	@Test
	public void testMapExistMax() {
		assertNotNull(map.map[Gate.sumRow-1][Gate.sumCol-1]);
	}
<<<<<<< HEAD

	public void testMapPrint() {
		LayBombToMap.layBomb(map.map, 0, 0);
		OpenGrid grid = new OpenGrid();
		grid.openGrid(map.map, 0, 0);
		Scanner scanner = new Scanner(System.in);
		while (!grid.isGameOver()) {
			System.out.println("    0  1  2  3  4  5  6  7  8");
			System.out.println("    -------------------------");
			int index = 0;
			for (Grid[] ys : map.map) {
				System.out.print((index++) + " | ");
				for (Grid x : ys) {
					if (x.isExpendTag()) {
						System.out.print(x.getCountAround() + "  ");
					} else if (x.isFlagTag()) {
						System.out.print("F  ");
					} else if (x.isMineTag()) {
						System.out.print("M  ");
					} else {
						System.out.print("X  ");
					}
				}
				System.out.println("");
			}
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			grid.openGrid(map.map, x, y);
		}
	}
=======
>>>>>>> origin/main
}
