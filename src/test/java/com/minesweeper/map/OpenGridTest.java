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
@RunWith(Parameterized.class)
public class OpenGridTest {
    static Map map;
    int x;
    int y;
    int countAround;

    //构造函数注入
    public OpenGridTest(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Parameterized.Parameters(name="{index}:grid is({0},{1})")
    public static Collection<Object []> testData(){
        return Arrays.asList(new Object[][]{
                {0,0},
                {0,1},
                {0,2},
                {0,3},
                {0,4},
                {0,5},
                {0,6},
                {0,7},
                {0,8},

                {1,0},
                {1,1},
                {1,2},
                {1,3},
                {1,4},
                {1,5},
                {1,6},
                {1,7},
                {1,8},

                {2,0},
                {2,1},
                {2,2},
                {2,3},
                {2,4},
                {2,5},
                {2,6},
                {2,7},
                {2,8},

                {3,0},
                {3,1},
                {3,2},
                {3,3},
                {3,4},
                {3,5},
                {3,6},
                {3,7},
                {3,8},

                {4,0},
                {4,1},
                {4,2},
                {4,3},
                {4,4},
                {4,5},
                {4,6},
                {4,7},
                {4,8},

                {5,0},
                {5,1},
                {5,2},
                {5,3},
                {5,4},
                {5,5},
                {5,6},
                {5,7},
                {5,8},

                {6,0},
                {6,1},
                {6,2},
                {6,3},
                {6,4},
                {6,5},
                {6,6},
                {6,7},
                {6,8},

                {7,0},
                {7,1},
                {7,2},
                {7,3},
                {7,4},
                {7,5},
                {7,6},
                {7,7},
                {7,8},

                {8,0},
                {8,1},
                {8,2},
                {8,3},
                {8,4},
                {8,5},
                {8,6},
                {8,7},
                {8,8},

        });

    }
    @Before
    public void setUp() throws Exception {
        map = new Map();
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }

    @Test
    //查看标记是否生效
    public void markGridTest() {
        LayBombToMap.layBomb(map.map,x,y);
        OpenGrid open1=new OpenGrid();
        open1.markGrid(map.map,x,y);
        System.out.println("mark grid is:"+"("+x+" , "+y+")");
        System.out.println();
        assertEquals(true,map.map[x][y].isFlagTag());
    }

    @Test
    //查看打开是否生效
    public void openGridTest() {
        LayBombToMap.layBomb(map.map,x,y);
        OpenGrid open1=new OpenGrid();
        open1.openGrid(map.map,x,y);
        System.out.println("open grid is:"+"("+x+" , "+y+")");
        assertEquals(true,map.map[x][y].isExpendTag());
    }
}