package com.minesweeper.User;

import com.minesweeper.display.Window;
import com.minesweeper.display.Window;

import java.util.Scanner;

public class UserInput{
    /**
     * 死循环直到用户正确输入
     * @param input 属于正确输入的预设
     * @return 返回正确的输入
     * */
    public InputType getNextInput(InputType input) {
        Scanner scan = new Scanner(System.in);
        String op = " ";
        while(op.equals(" ")){
            op = scan.next();
            op = op.toLowerCase();
            if(!isValid(op)){
                op = " ";
            }
        }
        return find(op);
    }

    /**
     * 检验用户的输入是否有效
     * @param op 用户输入的字符
     * @return 用户输入的字符是否合法
     * */
    public boolean isValid(String op){
        op = op.toLowerCase();
        for(InputType input: InputType.values()){
            if(!op.equals(" ") && input.getType().equals(op)){
                return true;
            }
        }
        return false;
    }

    /**
     * 在枚举类中查找与用户输入相对应的枚举类
     * @param op 用户输入的字符
     * @return 与用户输入相对应的枚举类
     * */
    public InputType find(String op){
        for(InputType input: InputType.values()){
            if(!op.equals(" ") && input.getType().equals(op)){
                return input;
            }
        }
        return null;
    }

}
