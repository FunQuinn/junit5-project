package com.util;

public class Calculator {
  public static int result=0;

  public static int add(int x, int y) throws InterruptedException {
//    int result = x+y;  //混合并发 加上int ,区分不同的方法结果
    result = x+y;  //混合并发 不加上int，会用同一个方法结果
    Thread.sleep(2000);
    return result;
  }

  public static int subtract(int x,int y) throws InterruptedException{
//    int result = x-y;
    result = x-y;
    Thread.sleep(2000);
    return result;
  }

  public static int multiply(int x,int y){
    result = x * y;
    return result;
  }

  public static int devide(int x,int y){
    result= x/y;
    return result;
  }

  public static int count(int x) throws InterruptedException{
    int i=result;
    Thread.sleep(3000);
    result=i+x;
    return result;
  }
//使用synchronized线程锁控制顺序执行
  public synchronized static int countsynTest(int x) throws InterruptedException{
    int i=result;
    Thread.sleep(3000);
    result=i+x;
    return result;
  }
  public static void clear(){
    result =0;
    System.out.println("当前清零处理");
  }

}
