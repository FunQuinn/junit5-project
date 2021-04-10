package com.wechatweb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseToWeb {
  WebDriver driver;


  public BaseToWeb(WebDriver driver) {
    this.driver = driver;
  }

  public BaseToWeb() {

  }

  void click(By by) {
    driver.findElement(by).click();

  }

  void sendKeys(By by, String content) {
    driver.findElement(by).sendKeys(content);
  }

}
