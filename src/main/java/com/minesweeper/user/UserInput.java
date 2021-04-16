package com.minesweeper.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import static com.minesweeper.utils.Constant.ERROR_INPUT;

public class UserInput{
    /**
     * 死循环直到用户正确输入
     * @param input 属于正确输入的预设
     * @return 返回正确的输入
     * */
    public String getNextInput(InputType input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String op = null;
        try {
            op = reader.readLine().toLowerCase(Locale.US);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isValid(op, input) ? op : ERROR_INPUT;
    }

    /**
     * 检验用户的输入是否有效
     * @param op 用户输入的字符
     * @param input 预设的输入类型
     * @return 用户输入的字符是否合法
     * */
    public boolean isValid(String op, InputType input){
        op = op.toLowerCase(Locale.ROOT);
        if (input.getValidInputString() == null) return false;
        return input.getValidInputString().contains(op);
    }

}
