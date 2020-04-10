package com.luu.jvm.memoryAllocation;

/**
 * 大对象直接进入老年代
 * 所谓大对象是指，需要大量连续内存空间的Java对象，经常出现大对象容易导致内存还有不少空间时就提前触发垃圾收集以获取足够的连续空间来为大对象分配内存。
 * 虚拟机提供了一个-XX:PretenureSizeThreshold 参数，让大于该值得对象直接进入老年代。这样做的目的是避免在新生代Eden区及两个Survivor区之间发生大量的内存复制。
 *
 * PretenureSieThreshold 参数只对 Serial 和 ParNew 两款收集器有效，Parallel Scavenge 收集器不识别这个参数，并且该收集器一般不需要设置。如果必须使用此参数的场合，可以考虑ParNew加CMS的收集器组合。
 *
 *
 */
public class FullGCDemo {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:+PrintCommandLineFlags -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
     * 因为使用的是jdk1.8，所以此处特指定了使用垃圾收集器Serial
     * 大于3M的对象直接进入老年代
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];//直接分配在老年代
    }

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
