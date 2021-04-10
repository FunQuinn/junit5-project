package com.unit_concurrent;

import com.util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author FunQuinn
 * @Description: 并发测试
 */


public class Parallel {
    /**
     * 并行
     */
    @RepeatedTest(10)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println(result);
    }


    /**
     * 加锁，串行访问
     */

    @RepeatedTest(10)
    public void countSynTest() throws InterruptedException {
        int result = Calculator.countsynTest(1);
        System.out.println(result);
    }



}
