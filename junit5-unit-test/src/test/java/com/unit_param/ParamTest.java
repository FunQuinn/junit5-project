package com.unit_param;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamTest {

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3})
  void testWithValueSource (int argument){
    assertTrue(argument > 0 && argument <4);
  }

  @ParameterizedTest
  @NullSource
  @EmptySource
  @ValueSource(strings = {" ","  ","\t","\n"})
  void nullEmptyAndBlankString(String text){
    assertTrue(text ==null || text.trim().isEmpty());
  }

  @ParameterizedTest
  @MethodSource

  void testWithMethod(String argument)
  {assertNotNull(argument);
  }

  static Stream<String> testWithMethod(){
    return Stream.of("apple","balala","oil");
  }
}
