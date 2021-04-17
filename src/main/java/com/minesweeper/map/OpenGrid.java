package com.minesweeper.map;

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
            }
        }
        this.isGameOver = true;
    }
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
                        map[posX][posY].setExpendTag(true);
                    }
                }
            }
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
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public void reset() {
        this.isGameOver = false;
    }
}
