package com.wechatweb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPOTest {
  private static MainPage main;


  @BeforeAll
  static void beforeAll() throws IOException, InterruptedException {
    main = new MainPage();
    main.contact().clearAllDeparts();

  }

  /**
   * 部门查询
   */
  @Test

  void departSearch() throws IOException, InterruptedException {

    MainPage mainPage = new MainPage();
    ContactPage contactPage = mainPage.contact();

    contactPage.searchDepart("火星测试部");
    String content = contactPage.getPartyInfo();
    assertTrue(content.contains("当前部门无任何成员"));
  }

  @Test
  void testDepartSearchChain() throws IOException, InterruptedException {
    assertTrue(new MainPage().contact().searchDepart("企划部一期").getPartyInfo().contains("当前部门无任何成员"));
  }


  /**
   * 创建部门
   */
  @Test
  @Order(1)
  void creatNewDepart() throws InterruptedException {
    String departName = "企划部";
    assertTrue(main.contact().addNewDepart(departName).searchDepart(departName).getPartyInfo().contains(departName));

  }

  /**
   * 更新部门信息-重命名
   */
  @Test
  void updateDepart() {
    String departRename = "企划部一期";
    String departName ="企划部";
    assertTrue(main.contact().updateDepartName(departRename).searchDepart(departName).getPartyInfo().contains(departName));
  }

  /**
   * 部门内增加成员
   */
  @Test
  void addMember() throws IOException, InterruptedException {
    MainPage mainPage = new MainPage();
    ContactPage contactPage = mainPage.contact();
    contactPage.addMember("张三", "Sunson", "18888888888");
    String content = contactPage.getPartyInfo();
    assertTrue(content.contains("张三"));

//   assertTrue(main.contact().addMember("张三","Sunson","18888888888").addMember
//        ("张三","Sunson","18888888888").getPartyInfo().contains("张三"));

  }

}
