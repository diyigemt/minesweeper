package com.minesweeper.user;

import com.minesweeper.User.InputType;
import com.minesweeper.User.UserInput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class UserNextInputTest {

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
        InputType inputType = InputType.RETURN;
        UserInput userInput = new UserInput();
        Assert.assertEquals(inputType,userInput.getNextInput(inputType));
    }
}