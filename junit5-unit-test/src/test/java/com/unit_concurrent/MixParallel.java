package com.unit_concurrent;

import com.util.Calculator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: 混合并发测试
 */


public class MixParallel {

    @RepeatedTest(10)
    public void addTest() throws InterruptedException {
        int add_result = Calculator.add(5, 3);
        System.out.println(add_result);
        assertEquals(8, add_result);
    }

   @RepeatedTest(10)
    public void subTractTest() throws InterruptedException {
        int sub_result = Calculator.subtract(5, 3);
        System.out.println(sub_result);
        assertEquals(2, sub_result);
    }
}
