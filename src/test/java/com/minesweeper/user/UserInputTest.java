package com.minesweeper.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

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
                {"m",true},
                {"M",true},
                {"p",true},
                {"P",true},
                {"q",true},
                {"Q",true},
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
        Assert.assertEquals(expectOutput , userInput.isValid(input, InputType.GAME));
    }
}