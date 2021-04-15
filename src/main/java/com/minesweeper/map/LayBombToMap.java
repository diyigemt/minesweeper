package com.minesweeper.map;

import java.util.Random;

/**
 * 包括布置雷的函数，同时计算每个方块周围雷数并写入方块
 * @author duo
 *
 */
public class LayBombToMap {
	static int layBombCount=0;
	//布置雷的函数，在游戏开始后第一次点击方块时call
	public static void layBomb(Grid[][] map,int row,int col) {
		
		Random random = new Random();
		while (layBombCount < Gate.mineSumCount) {
			int x = random.nextInt(Gate.sumRow);
			int y = random.nextInt(Gate.sumCol);
			if (map[x][y].isMineTag() == false && !(x == row && y == col)) {//第二个条件满足第一次点击不为雷
				map[x][y].setMineTag(true);
				map[x][y].setCountAround(9);//周围雷数暂时设为9

				layBombCount++;
			}
		}
		computeCountAround(map);
	}
	//计算每个方块周围雷数，写入方块中
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
