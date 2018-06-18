package com.testProject.load;

import com.testProject.base.classes.FirstLessonObject;

public class ClassToLoad extends FirstLessonObject {

    public void sayHelloFiveTimes(String name) {
        for(int i = 0; i < 5; ++i) {
            System.out.println("Hi, " + name);
        }

    }
    public static void saySomething(String text) {
        System.out.println(text + " from static method");
    }
}
