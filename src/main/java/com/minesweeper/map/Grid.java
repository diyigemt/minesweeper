package com.minesweeper.map;
<<<<<<< HEAD

import java.io.Serializable;

=======
>>>>>>> origin/main
/**
 * 地图方块定义
 * @author duo
 * 
 */

<<<<<<< HEAD
public class Grid implements Serializable {
	private boolean mineTag=false;	//是否是雷标记
	private boolean expendTag=false;	//是否翻开
	private boolean flagTag=false;	//是否插旗
	private int rowx;
	private int coly;
	private int countAround;	//周围雷数
=======
public class Grid {
	/**是否是雷标记*/
	private boolean mineTag=false;	
	/**是否翻开*/
	private boolean expendTag=false;
	/**是否插旗*/
	private boolean flagTag=false;	
	
	private int rowx;
	private int coly;
	/**周围雷数*/
	private int countAround;	
>>>>>>> origin/main
	
	public Grid() {
		
	}
<<<<<<< HEAD

	public Grid(int x, int y) throws Exception {
		this.setRowx(x);
		this.setColy(y);
		if(x>=Gate.sumRow)
			this.setRowx(-1);
		if(y>=Gate.sumCol)
			this.setColy(-1);		
		if(x<0||y<0)
			throw new Exception("行列数不得为负数");
			
	}

	@Override
	public String toString() {
		return  mineTag +
				" " + expendTag +
				" " + flagTag +
				" " + rowx +
				" " + coly +
				" " + countAround;
=======
	public Grid(int x, int y) throws Exception {
		this.setRowx(x);
		this.setColy(y);
		if(x>=Gate.sumRow) {
			this.setRowx(-1);
		}
		if(y>=Gate.sumCol) {
			this.setColy(-1);	
		}
		if(x<0||y<0) {
			throw new Exception("行列数不得为负数");
		}
>>>>>>> origin/main
	}

	public boolean isMineTag() {
		return mineTag;
	}

	public void setMineTag(boolean mineTag) {
		this.mineTag = mineTag;
	}

	public boolean isExpendTag() {
		return expendTag;
	}

	public void setExpendTag(boolean expendTag) {
		this.expendTag = expendTag;
	}

	public boolean isFlagTag() {
		return flagTag;
	}

	public void setFlagTag(boolean flagTag) {
		this.flagTag = flagTag;
	}

	public int getCountAround() {
		return countAround;
	}

	public void setCountAround(int countAround) {
		this.countAround = countAround;
	}

	public int getRowx() {
		return rowx;
	}

	public void setRowx(int rowx) {
		this.rowx = rowx;
	}

	public int getColy() {
		return coly;
	}

	public void setColy(int coly) {
		this.coly = coly;
	}
	
}
