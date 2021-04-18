package com.minesweeper.user;

import com.minesweeper.utils.Constant;

import java.util.ArrayList;

/**
 * 用户输入的有效类型
 * @author lzy
 * */
@SuppressWarnings("AlibabaSwitchStatement")
public enum InputType {

    /* 主菜单 */
    MENU,
    /* 游戏 */
    GAME;

    /**
     * UP,DOWN,LEFT,RIGHT 对应wasd（上下左右）功能
     * TURN 对应翻开格子功能，SIGN 对应标记格子功能，CANCEL 对应取消标记功能
     * QUIT 对应退出游戏功能，RETURN 对应返回主界面功能
     * */



    public ArrayList<String> getValidInputString() {
        switch (this) {
            case MENU: {
                return Constant.MENU_LIST;
            }
            case GAME: {
                return Constant.GAME_LIST;
            }
        }
        return null;
    }
}
