package com.luu.jvm.classLoad;

/**
 * 测试题：最后输出的会是什么？
 * 公布答案了：
 *
 * 书的静态代码块
 * Hello ShuYi.
 *
 * 这里要在Book中main执行，那么就要执行Book的初始化，于是打印了"书的静态代码块"，
 * 然后这里并没有使用new 等相关代码去初始化类，所以直接就执行main的打印语句了。
 *
 */
public class Book {
    public static void main(String[] args)
    {
        System.out.println("Hello ShuYi.");
    }

    Book()
    {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }

    {
        System.out.println("书的普通代码块");
    }

    int price = 110;

    static
    {
        System.out.println("书的静态代码块");
    }

    static int amount = 112;
}