package com.unit_case;

import org.junit.jupiter.api.*;

public class AnnitationChildTest extends AnnotationTest {


  @Test
  void fun_testChild() {
    System.out.println("@testChild funnnn");
  }

  @Test
  void testfunChild() {
    System.out.println("@testChild funn");
  }

  @BeforeAll
  public static void beforeChild() {
    System.out.println("@beforeChild fun");
  }

  @AfterAll
  public static void afterChild() {
    System.out.println("@afterChild fun");
  }

  @BeforeEach
  void before_each_Child() {
    System.out.println("@beforeChild Each");
  }

  @AfterEach
  void after_each_Child() {
    System.out.println("@afterChild Each");
  }


}



