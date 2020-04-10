package com.luu.jvm.memoryAllocation;

/**
 * 长期存活的对象将进入老年代
 * 虚拟机使用了分代收集的思想来管理内存，内存回收时为了区分哪些对象应放在新生代，哪些应该放在老年代，虚拟机为每个对象定义了一个对象年龄（Age）计数器。
 *
 * VM 参 数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 *
 * 如果对象被分配在Eden区并经过第一次Minor GC 后仍然存活，并且能被Survivor容乃的情况下，将被移动到Survivor中，对象年龄设为1。
 * 在Survivor区每经过一次Minor GC，年龄就加1，当对象的年龄到达一定程度时（默认15岁），就会晋升到老年代。
 * 对象晋升到老年代的阈值，可以通过参数：-XX:MaxTenuringThreshold 设置。
 *
 *
 *-XX:MaxTenuringThreshold=1 （jdk1.8）运行结果：
 *
 * Heap
 *  PSYoungGen      total 9216K, used 6994K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 85% used [0x00000007bf600000,0x00000007bfcd4b68,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *  ParOldGen       total 10240K, used 8192K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 80% used [0x00000007bec00000,0x00000007bf400020,0x00000007bf600000)
 *  Metaspace       used 3104K, capacity 4568K, committed 4864K, reserved 1056768K
 *   class space    used 338K, capacity 392K, committed 512K, reserved 1048576K
 *
 * -XX:MaxTenuringThreshold=15 （jdk1.8）运行结果：
 *
 * Heap
 *  PSYoungGen      total 9216K, used 6994K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 85% used [0x00000007bf600000,0x00000007bfcd4b68,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *  ParOldGen       total 10240K, used 8192K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 80% used [0x00000007bec00000,0x00000007bf400020,0x00000007bf600000)
 *  Metaspace       used 3104K, capacity 4568K, committed 4864K, reserved 1056768K
 *   class space    used 338K, capacity 392K, committed 512K, reserved 1048576K
 *
 *
 */
public class MaxTenuringThresholdDemo {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM 参 数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[4 * _1MB];
        //什么时候进入老年代取决于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];

        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testTenuringThreshold();
    }
}
