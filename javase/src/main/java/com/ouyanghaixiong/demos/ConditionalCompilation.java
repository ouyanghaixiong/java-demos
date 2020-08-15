package com.ouyanghaixiong.demos;

/**
 * @author ouyanghaixiong@forchange.tech
 * @date 2020/8/13 - 10:06 下午
 * @desc 如果if语句里的判断条件是final的, 且值为false, 因为final变量只能赋值一次, 所以这个条件恒为false,
 *  编译器会直接跳过这段代码的编译, 即条件编译.
 */
public class ConditionalCompilation {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        final boolean condition = false;
        if (condition) {
            System.out.println("the piece of code will never be compiled.");
            System.out.println("and this statement will never be shown.");
        }
        System.out.println("I am the first statement you will see.");
    }
}
