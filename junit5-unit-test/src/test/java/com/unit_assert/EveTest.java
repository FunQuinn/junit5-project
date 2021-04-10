package com.unit_assert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EveTest {
  @Test
  public  void envTest(){
    assertEquals(1,3,"实际值与期望值不一致");
  }
}
