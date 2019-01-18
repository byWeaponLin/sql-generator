package com.weaponlin.dsl;

import org.springframework.cglib.core.ReflectUtils;
import sun.applet.Main;

public class MainTest {

    public static void main(String[] args) {
//        System.out.println(Main.class.getEnclosingMethod().getName());
        test();
    }

    public static String test() {
        // 1
//        String methodName = new Object() {}
//                .getClass()
//                .getEnclosingMethod()
//                .getName();
//        System.out.println(methodName);

        // 2
//        StackTraceElement[] stackTrace = Thread.currentThread()
//                .getStackTrace();
//        System.out.println(stackTrace[1].getMethodName());

        // 3
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        System.out.println(stackTrace[0].getMethodName());

        return null;
    }
}
