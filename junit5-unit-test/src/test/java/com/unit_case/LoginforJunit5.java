package com.unit_case;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class LoginforJunit5 {
  public  static HashMap<String,Object>dataMap= new HashMap<String, Object>();

  @Test
  void LoginTest1(){
    dataMap.put("Login","登入成功");
    System.out.println("Login");
  }

  @Nested
  class PayTest{
    @Test
    void payTest(){
      if(null!=dataMap.get("buy")){
        System.out.println("支付中");
      }else{
        System.out.println("未支付，请先支付");
      }
    }


  }

  @Nested
  class BuyTest{
    @Test
    void buyTest(){
      if(  dataMap.get("Login").equals("登入成功")){
        System.out.println("登入成功，開始購買物品");
      dataMap.put("buy","购买巧克力");
      }
      else{
        System.out.println("先登入再購物");

      }

    }
  }



}
