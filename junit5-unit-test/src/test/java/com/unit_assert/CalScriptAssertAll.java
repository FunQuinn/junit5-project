package com.unit_assert;

import com.util.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * assertAll对加减乘除、计数进行断言，增加脚本容错性
 */
public class CalScriptAssertAll {

  @Test
  public void addTest() throws InterruptedException {
    int add_result_01 = Calculator.add(5, 3);
    System.out.println(add_result_01);

    int add_result_02 = Calculator.add(6, 3);
    System.out.println(add_result_02);

    int add_result_03 = Calculator.add(7, 3);
    System.out.println(add_result_03);
    assertAll("加法计算结果验证：",
        () -> assertEquals(8, add_result_01),
        () -> assertEquals(6, add_result_02),
        () -> assertEquals(10, add_result_03)
    );

  }

  @Test
  public void subTractTest() throws InterruptedException {
    int sub_result_01 = Calculator.subtract(10, 5);
    System.out.println(sub_result_01);

    int sub_result_02 = Calculator.subtract(10, 2);
    System.out.println(sub_result_02);

    int sub_result_03 = Calculator.subtract(10, 10);
    System.out.println(sub_result_03);

    assertAll("减法计算结果验证：",
        () -> assertEquals(5, sub_result_01),
        () -> assertEquals(5, sub_result_02),
        () -> assertEquals(0, sub_result_03));

  }

  @Test
  public void multiplyTest() {
    int mul_result_01 = Calculator.multiply(10, 2);
    System.out.println(mul_result_01);
    int mul_result_02 = Calculator.multiply(20, 2);
    System.out.println(mul_result_02);

    int mul_result_03 = Calculator.multiply(30, 2);
    System.out.println(mul_result_03);
    assertAll("乘法计算结果验证：",
        () -> assertEquals(20, mul_result_01),
        () -> assertEquals(30, mul_result_02),
        () -> assertEquals(20, mul_result_03));
  }

  @Test
  public void devideTest() {
    int devide_result_01 = Calculator.devide(10, 5);
    System.out.println(devide_result_01);

    int devide_result_02 = Calculator.devide(30, 5);
    System.out.println(devide_result_02);

    int devide_result_03 = Calculator.devide(100, 5);
    System.out.println(devide_result_03);
    assertAll("除法计算结果验证：",
        () -> assertEquals(2, devide_result_01),
        () -> assertEquals(2, devide_result_02),
        () -> assertEquals(2, devide_result_03));
  }

  @Test
  public void count() throws InterruptedException {
    int count_result_01 = Calculator.count(1);
    System.out.println(count_result_01);

    int count_result_02 = Calculator.count(2);
    System.out.println(count_result_02);

    int count_result_03 = Calculator.count(3);
    System.out.println(count_result_03);
    assertAll("累加值计算结果验证：",
        () -> assertEquals(2, count_result_01),
        () -> assertEquals(2, count_result_02),
        () -> assertEquals(2, count_result_03)
    );

  }

}

