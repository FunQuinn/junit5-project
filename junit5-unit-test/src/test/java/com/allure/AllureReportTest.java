package com.allure;

import com.util.Calculator;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("计算演练")
@Feature("Feature SmokeTest")
public class AllureReportTest {

  @Test
  @Order(1)
  @Description("Des 详细描述")
  @Story("Story 加法测试")
  @DisplayName("DisplayName 加法测试")
  @Severity(SeverityLevel.BLOCKER)
  @Issue("https://www.baidu.com")
  @Link(name = "Link 社区贴", type = "linking", url = "https://ceshiren.com/t/topic/2377")
  public void addTest() throws InterruptedException {
    int add_result_01 = Calculator.add(5, 3);
    System.out.println(add_result_01);

    int add_result_02 = Calculator.add(6, 3);
    System.out.println(add_result_02);

    int add_result_03 = Calculator.add(7, 3);
    System.out.println(add_result_03);

    Allure.addAttachment("pic", "image/jpg", this.getClass().getResourceAsStream("/Beauty.jpg"), ".jpg");

    assertAll("加法计算结果验证：",
        () -> assertEquals(8, add_result_01),
        () -> assertEquals(6, add_result_02),
        () -> assertEquals(10, add_result_03)
    );


  }

  @Test
  @Order(2)
  @Description("Des 详细描述")
  @Story("Story 减法测试")
  @DisplayName("DisplayName 减法测试")
  @Severity(SeverityLevel.BLOCKER)
  @Issue("https://blog.csdn.net/Jomei_Z/article/details/108864620")
  @Link(name = "Link 社区贴", type = "linking", url = "https://ceshiren.com/t/topic/2377")
  public void subTractTest() throws InterruptedException {
    int sub_result_01 = Calculator.subtract(10, 5);
    System.out.println(sub_result_01);

    int sub_result_02 = Calculator.subtract(10, 2);
    System.out.println(sub_result_02);

    int sub_result_03 = Calculator.subtract(10, 10);
    System.out.println(sub_result_03);
    Allure.addAttachment("落日", "image/jpg", this.getClass().getResourceAsStream("/落日.jpg"), ".jpg");
    assertAll("减法计算结果验证：",
        () -> assertEquals(5, sub_result_01),
        () -> assertEquals(5, sub_result_02),
        () -> assertEquals(0, sub_result_03));

  }

  @Test
  @Order(3)
  @Description("Des 详细描述")
  @Story("Story 乘法测试")
  @DisplayName("DisplayName 乘法测试")
  @Severity(SeverityLevel.BLOCKER)
  @Issue("http://192.168.15.243:63671/index.html#suites/b96e1221820be58982d853acaa701be0/242146fe0013c3b0/")
  @Link(name = "Link 社区贴", type = "linking", url = "https://ceshiren.com/t/topic/2377")
  public void multiplyTest() {
    int mul_result_01 = Calculator.multiply(10, 2);
    System.out.println(mul_result_01);
    int mul_result_02 = Calculator.multiply(20, 2);
    System.out.println(mul_result_02);

    int mul_result_03 = Calculator.multiply(30, 2);
    System.out.println(mul_result_03);
    assertAll("乘法计算结果验证：",
        () -> assertEquals(20, mul_result_01),
        () -> assertEquals(40, mul_result_02),
        () -> assertEquals(60, mul_result_03));
  }

  @Test
  @Order(4)
  @Description("Des 详细描述")
  @Story("Story 除法测试")
  @DisplayName("DisplayName 除法测试")
  @Severity(SeverityLevel.BLOCKER)
  @Issue("https://www.cnblogs.com/lierabbit/p/8299155.html")
  @Link(name = "Link 社区贴", type = "linking", url = "https://ceshiren.com/t/topic/2377")
  public void devideTest() {
    int devide_result_01 = Calculator.devide(10, 5);
    System.out.println(devide_result_01);

    int devide_result_02 = Calculator.devide(30, 5);
    System.out.println(devide_result_02);

    int devide_result_03 = Calculator.devide(100, 5);
    System.out.println(devide_result_03);
    assertAll("除法计算结果验证：",
        () -> assertEquals(2, devide_result_01),
        () -> assertEquals(6, devide_result_02),
        () -> assertEquals(20, devide_result_03));
  }

  @Test
  @Order(5)
  @Description("Des 详细描述")
  @Story("Story 总和测试")
  @DisplayName("DisplayName 总和测试")
  @Severity(SeverityLevel.BLOCKER)
  @Issue("https://www.oschina.net/")
  @Link(name = "Link 社区贴", type = "linking", url = "https://ceshiren.com/t/topic/2377")
  public void count() throws InterruptedException {
    int count_result_01 = Calculator.count(1);
    System.out.println(count_result_01);

    int count_result_02 = Calculator.count(2);
    System.out.println(count_result_02);

    int count_result_03 = Calculator.count(3);
    System.out.println(count_result_03);
    assertAll("累加值计算结果验证：",
        () -> assertEquals(1, count_result_01),
        () -> assertEquals(2, count_result_02),
        () -> assertEquals(3, count_result_03)
    );

  }

}
