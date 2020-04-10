package com.luu.jvm.memoryAllocation;

/**
 * 动态对象年龄判定
 * 虚拟机并不是永远要求对象的年龄必须达到了MaxTenuringThreshold才能晋升老年代，如果Survivor空间中，相同年龄对象的大小之和大于Survivor空间大小的一半，就可以直接进入老年代。
 *
 *
 */
public class TenuringThresholdDemo {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM 参 数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
     * */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        //allocation1+allocation2大于Survivo空间的一半
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];

    }

    public static void main(String[] args) {
        testTenuringThreshold();
    }
}
