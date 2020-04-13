package com.luu.jvm.jvmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存泄露直到溢出
 */
public class DumpDemo {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        System.out.println("-------------mytime----------------");
        Map<String,Object> m = new HashMap<>();
        int i = 0;
        do{
            byte[] test = new byte[_1MB];
            m.put(String.valueOf(i), test);
            i++;
        }while(i<100000);
    }
}
