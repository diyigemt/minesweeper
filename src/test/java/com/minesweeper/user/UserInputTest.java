package com.minesweeper.user;

import com.minesweeper.User.InputType;
import com.minesweeper.User.UserInput;
import com.minesweeper.display.Window;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class UserInputTest {


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"a",true},
                {"A",true},
                {"d",true},
                {"D",true},
                {"w",true},
                {"W",true},
                {"s",true},
                {"S",true},
                {"u",true},
                {"U",true},
                {"g",true},
                {"G",true},
                {"c",true},
                {"C",true},
                {"f",false},
                {"y",false},
                {"h",false},
                {"H",false},
                {";",false},
                {" ",false}});
    }

    @Parameterized.Parameter
    public String input;

    @Parameterized.Parameter(1)
    public boolean expectOutput;


    @Test
    public void isValid() {
        UserInput userInput = new UserInput();
        Assert.assertEquals(expectOutput,userInput.isValid(input));
    }
}