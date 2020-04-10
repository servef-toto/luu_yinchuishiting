package com.luu.jvm.jvmcmd;


public class JVMDemo {

    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args){
        testAllocation();
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * */
    public static void testAllocation() {
        System.out.println("Hello World");

    }
}
