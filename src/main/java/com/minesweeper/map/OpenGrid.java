package com.minesweeper.map;

/**
 * 包括打开格子和标记格子的函数，同时判断游戏胜负
 * @author cxy
 *
 */
public class OpenGrid {
    public int getExpandTags() {
        return expandtags;
    }

    public void setExpandTags(int expandtags) {
        this.expandtags = expandtags;
    }

    private int expandtags=0;
    private boolean isGameOver = false;
    private boolean isGameWin = true;

    public OpenGrid() {
    }
    /**判断点周围八邻域的点
     * @param posX 格子的行数
     * @param posY 格子的列数
     */
    public Point[] getPoint(int posX, int posY) {
        Point[] point = new Point[]{new Point(posX - 1, posY), new Point(posX - 1, posY - 1), new Point(posX, posY - 1), new Point(posX + 1, posY - 1), new Point(posX + 1, posY), new Point(posX + 1, posY + 1), new Point(posX, posY + 1), new Point(posX - 1, posY + 1)};
        return point;
    }
    /**判断游戏胜利，并将所有雷格子打开
     * @param map 使用的地图，为方块数组
     */
    public void gameLose(Grid[][] map) {
        Grid[][] var2 = map;
        int var3 = map.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Grid[] grids = var2[var4];
            Grid[] var6 = grids;
            int var7 = grids.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Grid grid = var6[var8];
                if (grid.isMineTag()) {
                    grid.setExpendTag(true);
                }
            }
        }
        this.isGameWin = false;
        this.isGameOver = true;
    }
    /**判断游戏失败，并将所有雷格子打开
     * @param map 使用的地图，为方块数组
     */
    public void gameWin(Grid[][] map) {
        Grid[][] var2 = map;
        int var3 = map.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Grid[] grids = var2[var4];
            Grid[] var6 = grids;
            int var7 = grids.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Grid grid = var6[var8];
                if (grid.isMineTag()) {
                    grid.setExpendTag(true);
                }
            }
        }
        this.isGameOver = true;
        this.isGameWin = true;
    }
    /**标记格子的函数，将格子设为标记状态
     * @param map 使用的地图，为方块数组
     * @param posX 点击的方块对应的行数
     * @param posY 点击的方块对应的列数
     */
    public void markGrid(Grid[][] map, int posX, int posY){
        if(!map[posX][posY].isFlagTag())
            map[posX][posY].setFlagTag(true);
        else if(map[posX][posY].isFlagTag())
            map[posX][posY].setFlagTag(false);
    }
    /**标记格子的函数，将格子设为标记状态
     * @param map 使用的地图，为方块数组
     * @param posX 点击的方块对应的行数
     * @param posY 点击的方块对应的列数
     */
    public void openGrid(Grid[][] map, int posX, int posY) {
        this.setExpandTags(this.getExpandTags()+1);
        if (map[posX][posY].getCountAround() == 0) {
            map[posX][posY].setExpendTag(true);
            Point[] p = this.getPoint(posX, posY);
            //循环遍历8个方向获得对应的格子对象
            for (int k = 0; k < p.length; k++) {
                //分别获得的方向对象
                Point point = p[k];
                if (point.x >= 0 && point.x < 9 && point.y >= 0 && point.y < 9) {
                    /**
                     * 判断当前坐标对象对应的格子对象内容是否为空格，
                     * 如果是空格实现递归调用，如果是数字就开启当前格子
                     */
                    int count = map[point.x][point.y].getCountAround();
                    if (count >= 0 && count != 9 && !map[point.x][point.y].isExpendTag()) {
                        //当前格子为空格的情况
                        this.openGrid(map, point.x, point.y);

                    } else {
                        //当前格子为数字的情况
                        map[posX][posY].setExpendTag(true);
                    }
                }
            }
        } else if (map[posX][posY].getCountAround() < 9) {
            map[posX][posY].setExpendTag(true);
        }  else if (map[posX][posY].isMineTag()) {
            this.gameLose(map);
        }
        if(this.getExpandTags()+Gate.mineSumCount==Gate.sumCol*Gate.sumRow)
            this.gameWin(map);
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public boolean isGameWin() {
        return this.isGameWin;
    }

    public void reset() {
        this.isGameWin = false;
        this.isGameOver = false;
    }
}
