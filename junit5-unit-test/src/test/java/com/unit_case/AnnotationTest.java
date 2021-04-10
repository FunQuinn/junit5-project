package com.unit_case;

import org.junit.jupiter.api.*;

/**
 * @des : 练习Junit5的注解
 *        @Test @BeforeEach @AfterEach @BeforeAll @AfterAll
 *        @Tag @DisplayName @RepeatedTest @Disabled
 */

public class AnnotationTest {
  @Test
@Tag("testfunction")
  @DisplayName("test 测试方法")
//  @RepeatedTest(2)
  void fun() {
    System.out.println("@test fun");
  }

  @Test
  @Tag("testfunction2")
  @DisplayName("test 测试方法2")
//  @RepeatedTest(2)
  void fun_test() {
    System.out.println("@test funnnn");
  }

  @Test
//  @Disabled
  @DisplayName("test 测试")
  @Tag("testdemo")
  void testfun() {
    System.out.println("@test funn");
  }

  @BeforeAll
  public static void before() {
    System.out.println("@before fun");
  }

  @AfterAll
  public static void after() {
    System.out.println("@after fun");
  }

  @BeforeEach
  void before_each() {
    System.out.println("@before Each");
  }

  @AfterEach
  void after_each() {
    System.out.println("@after Each");
  }


}

