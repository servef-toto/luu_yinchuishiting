package com.luu.jvm.classLoad;

/**
 * 一道面试题
 * 控制台最后输出的会是什么？
 * 与类的加载机制有关，理解类加载机制的可以解答出来
 *
 * 正确答案是：
 *  爷爷在静态代码块
 *  爸爸在静态代码块
 *  爸爸的岁数:25
 *
 * 解题思路：
 * 首先程序到 main 方法这里，使用标准化输出 Son 类中的 factor 类成员变量，但是 Son 类中并没有定义这个类成员变量。
 * 于是往父类去找，我们在 Father 类中找到了对应的类成员变量，于是触发了 Father 的初始化。
 * 但根据我们上面说到的初始化的 5 种情况中的第 3 种（当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化）。
 * 我们需要先初始化 Father 类的父类，也就是先初始化 Grandpa 类再初始化 Father 类。于是我们先初始化 Grandpa 类输出：「爷爷在静态代码块」，再初始化 Father 类输出：「爸爸在静态代码块」。
 * 最后，所有父类都初始化完成之后，Son 类才能调用父类的静态变量，从而输出：「爸爸的岁数：25」。
 *
 * 也许会有人问为什么没有输出「儿子在静态代码块」这个字符串？
 * 这是因为对于静态字段，只有直接定义这个字段的类才会被初始化（执行静态代码块）。因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
 *
 */
public class LoadDemo {
    public static void main(String[] args)
    {
        System.out.println("爸爸的岁数:" + Son.factor);	//入口
    }
}

class Grandpa
{
    static
    {
        System.out.println("爷爷在静态代码块");
    }
}
class Father extends Grandpa
{
    static
    {
        System.out.println("爸爸在静态代码块");
    }

    public static int factor = 25;

    public Father()
    {
        System.out.println("我是爸爸~");
    }
}
class Son extends Father
{
    static
    {
        System.out.println("儿子在静态代码块");
    }

    public Son()
    {
        System.out.println("我是儿子~");
    }
}