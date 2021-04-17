package com.minesweeper.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
public class OpenGridTest2 {
    static Map map;
    @Before
    public void setUp() throws Exception {
        map = new Map();
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }

    @Test
    //查看游戏获胜是否生效
    public void gameWinTest() throws Exception {
        Map map1=new Map();
        OpenGrid open1=new OpenGrid();
        open1.gameWin(map1.map);
        System.out.println("gamewin");
        assertEquals(true,open1.isGameOver());
    }

    @Test
    //查看游戏获胜是否生效
    public void gameOverTest() throws Exception {
        Map map1=new Map();
        OpenGrid open1=new OpenGrid();
        open1.gameLose(map1.map);
        System.out.println("gameover");
        assertEquals(true,open1.isGameOver());
    }

    @Test
    //查看游戏获胜是否生效
    public void resetTest() throws Exception {
        Map map1=new Map();
        OpenGrid open1=new OpenGrid();
        open1.reset();
        System.out.println("reset");
        assertEquals(false,open1.isGameOver());
    }
}
