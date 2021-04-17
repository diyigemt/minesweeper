package com.minesweeper.map;

<<<<<<< HEAD
public class OpenGrid {
    private boolean isGameOver = false;
    private int expandTags=0;
    public int getExpandTags() {
        return expandTags;
    }

    public void setExpandTags(int expandTags) {
        this.expandTags = expandTags;
    }

    public Point[] getPoint(int posX,int posY) {//超过一个以上的返回要用数组
        //定义一个坐标对象数组
        Point[] point=new Point[8];
        //左边
        point[0]=new Point(posX-1,posY);
        //左上
        point[1]=new Point(posX-1,posY-1);
        //上边
        point[2]=new Point(posX,posY-1);
        //右上
        point[3]=new Point(posX+1,posY-1);
        //右边
        point[4]=new Point(posX+1,posY);
        //右下
        point[5]=new Point(posX+1,posY+1);
        //下边
        point[6]=new Point(posX,posY+1);
        //左下
        point[7]=new Point(posX-1,posY+1);
        return point;
    }
    public void gameOver(Grid[][] map){
        for (Grid[] grids : map) {
            for (Grid grid : grids) {
                if (grid.isMineTag())
                    grid.setExpendTag(true);
            }
        }
        this.isGameOver = true;
    }
    public void gameWin(Grid[][] map){
        for (Grid[] grids : map) {
            for (Grid grid : grids) {
                if (grid.isMineTag())
                    grid.setExpendTag(true);
=======
import java.awt.*;

public class OpenGrid {
    public int getExpandtags() {
        return expandtags;
    }

    public void setExpandtags(int expandtags) {
        this.expandtags = expandtags;
    }

    private int expandtags=0;
    private boolean isGameOver = false;

    public OpenGrid() {
    }

    public Point[] getPoint(int posX, int posY) {
        Point[] point = new Point[]{new Point(posX - 1, posY), new Point(posX - 1, posY - 1), new Point(posX, posY - 1), new Point(posX + 1, posY - 1), new Point(posX + 1, posY), new Point(posX + 1, posY + 1), new Point(posX, posY + 1), new Point(posX - 1, posY + 1)};
        return point;
    }
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

        this.isGameOver = true;
    }
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
>>>>>>> origin/main
            }
        }
        this.isGameOver = true;
    }
<<<<<<< HEAD
    public void markGrid(Grid[][] map, int posX, int posY){
        if(!map[posX][posY].isFlagTag())
            map[posX][posY].setFlagTag(true);
        else if(map[posX][posY].isFlagTag())
            map[posX][posY].setFlagTag(false);
    }
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
=======
    public void markGrid(Grid[][] map, int posX, int posY) {
        if (!map[posX][posY].isFlagTag()) {
            map[posX][posY].setFlagTag(true);
        } else if (map[posX][posY].isFlagTag()) {
            map[posX][posY].setFlagTag(false);
        }

    }

    public void openGrid(Grid[][] map, int posX, int posY) {
        this.setExpandtags(getExpandtags()+1);
        if (map[posX][posY].getCountAround() == 0) {
            map[posX][posY].setExpendTag(true);
            Point[] p = this.getPoint(posX, posY);

            for(int k = 0; k < p.length; ++k) {
                Point point = p[k];
                if (point.x >= 0 && point.x < 9 && point.y >= 0 && point.y < 9) {
                    int count = map[point.x][point.y].getCountAround();
                    if (count >= 0 && count != 9 && !map[point.x][point.y].isExpendTag()) {
                        this.openGrid(map, point.x, point.y);
                    } else {
>>>>>>> origin/main
                        map[posX][posY].setExpendTag(true);
                    }
                }
            }
<<<<<<< HEAD
        } else if (map[posX][posY].getCountAround() == 1)
            map[posX][posY].setExpendTag(true);
        else if (map[posX][posY].getCountAround() == 2)
            map[posX][posY].setExpendTag(true);
        else if (map[posX][posY].getCountAround() == 3)
            map[posX][posY].setExpendTag(true);
        else if (map[posX][posY].isMineTag()) {
            gameOver(map);
        }
        if(this.getExpandTags()+Gate.mineSumCount==Gate.sumRow*Gate.sumCol)
            gameWin(map);
=======
        } else if (map[posX][posY].getCountAround() == 1) {
            map[posX][posY].setExpendTag(true);
        } else if (map[posX][posY].getCountAround() == 2) {
            map[posX][posY].setExpendTag(true);
        } else if (map[posX][posY].getCountAround() == 3) {
            map[posX][posY].setExpendTag(true);
        } else if (map[posX][posY].isMineTag()) {
            this.gameLose(map);
        }
        if(this.getExpandtags()+Gate.mineSumCount==Gate.sumCol*Gate.sumRow)
            this.gameWin(map);

>>>>>>> origin/main
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public void reset() {
        this.isGameOver = false;
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
}
