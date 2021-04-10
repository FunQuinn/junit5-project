package com.unit_concurrent;

import com.util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

public class ConCurrentTest {
  @RepeatedTest(10)
  public void countTest() throws InterruptedException{
 int result = Calculator.count(1);
    System.out.println(result);
  }

  @RepeatedTest(10)
  public void countsyTest() throws InterruptedException{
    int Syresult = Calculator.countsynTest(1);
    System.out.println(Syresult);
  }
}
