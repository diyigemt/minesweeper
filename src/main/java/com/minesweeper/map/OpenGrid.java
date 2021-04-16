package com.minesweeper.map;

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
            }
        }
        this.isGameOver = true;
    }
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
                        map[posX][posY].setExpendTag(true);
                    }
                }
            }
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

    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public void reset() {
        this.isGameOver = false;
    }

}
