package com.ouyanghaixiong.demos;

/**
 * @author ouyanghaixiong@forchange.tech
 * @date 2020/8/13 - 10:22 下午
 * @desc you can use the key word assert in java to keep your program run correctly.
 * assert statement is ignored while run the class normally,(eg.java className),
 * but can be activated using the command: java -enableassertions className or java -ea className.
 * this is a easy way to control whether to activate the assertion statements in your programs to run.
 */
public class AssertDemo {
    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }
        int number = Integer.parseInt(args[0]);
        assert number >= 0 : "the input number is negative";
        System.out.println("accept a positive number " + number);
    }
}
