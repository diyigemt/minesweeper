package com.minesweeper.map;
/**
 * 地图类，包含方块数组
 * @author duo
 *
 */
public class Map {
<<<<<<< HEAD
	//地图，由方块数组构成
	public Grid[][] map=new Grid[Gate.sumRow][Gate.sumCol];
=======
	/** 地图，由方块数组构成*/
	Grid[][] map=new Grid[Gate.sumRow][Gate.sumCol];
>>>>>>> origin/main
	
	public Map() throws Exception {
		initMap();
	}
<<<<<<< HEAD
	//地图初始化，创建9x9方块（0~8，0~8）
=======
	/**
	 * 地图初始化，创建9x9方块（0~8，0~8）
	 * @throws Exception
	 */
>>>>>>> origin/main
	private void initMap() throws Exception {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Grid(i, j);
			}
		}
	}
<<<<<<< HEAD

	private void initMapFromLocal() {

	}
=======
>>>>>>> origin/main
}
