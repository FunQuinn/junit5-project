package com.unit_concurrent;

import com.util.Calculator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConCurrentMixTest {
  @Order(1)
  @RepeatedTest(5)
  public void addTest() throws InterruptedException {
    int add_result = Calculator.add(5,3);
    System.out.println(add_result);
    assertEquals(8,add_result);
  }
  @Order(2)
  @RepeatedTest(10)
  public void subTractTest()throws InterruptedException{
    int sub_result = Calculator.subtract(5,3);
    System.out.println(sub_result);
    assertEquals(2,sub_result);
  }
}
