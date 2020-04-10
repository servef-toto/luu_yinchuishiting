package com.luu.jvm.memoryAllocation;

/**
 * 对象优先在Eden分配
 * 大多情况，对象在新生代Eden区分配。当Eden区没有足够空间进行分配时，虚拟机将进行一次Minor GC。虚拟机提供了参数 -XX:+PrintGCDetails ，在虚拟机发生垃圾收集行为时打印内存回收日志。
 *
 * 新生代Minor GC 事例
 * 定义了4个字节数组对象，3个2MB大小、1个4MB大小，
 * 通过-Xms20M -Xmx20M -Xmn10M 三个参数限制了Java堆大小为 20M ，不可扩展，其中的 10MB 分配给新生代，剩下 10MB 分配给老年代
 * -XX:SurvivorRatio=8 新生代 Eden 与 Survivor 区空间比例是 8:1:1
 *
 * 执行之后从控制到能看到信息：
 * 从输出结果可以清晰看到 “eden space 8192K、from space 1024K、to space 1024K”
 * 新生代总可用空间为 9216KB (Eden区空间大小 + 1个Survivor区的总容量)
 * 这次GC发生的原因是给allocation4对象分配内存的时候，发现Eden区已经被占用了6MB，剩余空间已经不足以分配4MB的内存，因此发生了MinorGC。
 * GC期间有发现已有的3个2MB大小的对象已经无法全部放入Survivor空间（只有1MB大小）,
 * 所以只好通过分配担保机制提前将这三个对象转移到老年代去了。
 */
public class MinorGCDemo {

    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args){
        testAllocation();
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
//        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation4 = new byte[4 * _1MB]; // 出现一次 Minor GC

    }
}
