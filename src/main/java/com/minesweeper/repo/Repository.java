package com.minesweeper.repo;

import com.minesweeper.map.Map;

/**
 * create by CarriSun 2021/4/18
 */

public class Repository {
    private static Repository INSTANCE;

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    public boolean saveGameContext(Map gameMap) {
        return false;
    }

    public boolean readContext(Map gameMap) {
        return false;
    }

    public int updateHistory() {
        return -1;
    }

    public boolean readHistory() {
        return false;
    }

    // 测试
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
