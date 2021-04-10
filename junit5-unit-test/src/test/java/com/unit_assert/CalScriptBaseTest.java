package com.unit_assert;

import com.util.Calculator;
import org.junit.jupiter.api.Test;

/**
 * 基础脚本——加减乘除、计数操作
 */
public class CalScriptBaseTest {
  @Test
  public void addTest()throws InterruptedException{
    int add_result = Calculator.add(5,3);
    System.out.println(add_result);
  }

  @Test
  public void subTractTest()throws InterruptedException{
    int sub_result = Calculator.subtract(5,3);
    System.out.println(sub_result);
  }

  @Test
  public void multiplyTest(){
    int mul_result = Calculator.multiply(5,6);
    System.out.println(mul_result);
  }

  @Test
  public void devideTest(){
    int devide_result =Calculator.devide(10,2);
    System.out.println(devide_result);
  }

  @Test
  public void countTest () throws InterruptedException{
    int count_result =Calculator.count(1);
    count_result = Calculator.count(1);
    System.out.println(count_result);
  }
}
