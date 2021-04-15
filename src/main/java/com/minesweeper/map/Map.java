package com.minesweeper.map;
/**
 * 地图类，包含方块数组
 * @author duo
 *
 */
public class Map {
	//地图，由方块数组构成
	public Grid[][] map=new Grid[Gate.sumRow][Gate.sumCol];
	
	public Map() throws Exception {
		initMap();
	}
	//地图初始化，创建9x9方块（0~8，0~8）
	private void initMap() throws Exception {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Grid(i, j);
			}
		}
	}
}
