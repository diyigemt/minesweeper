package com.minesweeper.user;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;

import static com.minesweeper.utils.Constant.ERROR_INPUT;

public class TestUserNextInput {

    @Before
    public void setUp() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("u".getBytes());
        System.setIn(in);
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
    }

    @Test
    public void getNextInput() {
        InputType inputType = InputType.MENU;
        UserInput userInput = new UserInput();
        Assert.assertEquals(ERROR_INPUT, userInput.getNextInput(inputType));
    }
}