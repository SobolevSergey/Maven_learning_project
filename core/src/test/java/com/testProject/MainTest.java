package com.testProject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Before testing class com.testProject.Main");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("After testing class com.testProject.Main");
    }

    @Test
    public void main() {
        System.out.println("com.testProject.Main class testing");
    }

}