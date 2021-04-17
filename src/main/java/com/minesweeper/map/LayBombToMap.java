package com.minesweeper.map;

import java.util.Random;

/**
 * 包括布置雷的函数，同时计算每个方块周围雷数并写入方块
 * @author duo
 *
 */
public class LayBombToMap {
	static int layBombCount = 0;
	
	/**布置雷的函数，在游戏开始后第一次点击方块时call
	 * @param map
	 * @param row
	 * @param col
	 */
	public static int layBomb(Grid[][] map,int row,int col) {
		int BombCount=0;
		Random random = new Random();
		while (BombCount < Gate.mineSumCount) {
			int x = random.nextInt(Gate.sumRow);
			int y = random.nextInt(Gate.sumCol);
			boolean firstGrid = (x == row && y == col);
			//第二个条件满足第一次点击不为雷
			if (map[x][y].isMineTag() == false && !firstGrid) {
				map[x][y].setMineTag(true);
				//地雷方块周围雷数设为9
				map[x][y].setCountAround(9);
				BombCount++;
			}
		}
		computeCountAround(map);
		return BombCount;
	}
	/**
	 * 计算每个方块周围雷数，写入方块中
	 * @param map
	 */
	public static void computeCountAround(Grid[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].isMineTag() == false) {
					int count = 0;
					for (int x = Math.max(0, i - 1); x <= Math.min(
							Gate.sumRow - 1, i + 1); x++) {
						for (int y = Math.max(0, j - 1); y <= Math.min(
								Gate.sumCol - 1, j + 1); y++) {
							if (map[x][y].isMineTag() == true) {
								count++;
							}
						}
					}
					map[i][j].setCountAround(count);
				}

			}

		}
	}
}
