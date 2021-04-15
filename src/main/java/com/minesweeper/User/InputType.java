package com.minesweeper.User;

/**
 * 用户输入的有效类型
 * @author lzy
 * */
public enum InputType {
    /**
     * UP,DOWN,LEFT,RIGHT 对应wasd（上下左右）功能
     * TURN 对应翻开格子功能，SIGN 对应标记格子功能，CANCEL 对应取消标记功能
     * QUIT 对应退出游戏功能，RETURN 对应返回主界面功能
     * */
    UP("w"),DOWN("s"), LEFT("a"), RIGHT("d"),
    TURN("t"),SIGN("g"),CANCEL("c"),
    DEFAULT(" "),QUIT("q"),RETURN("U");

    private String type;

    InputType(String type) {
        this.type = type.toLowerCase();
    }

    public String getType() {
        return this.type;
    }
}
