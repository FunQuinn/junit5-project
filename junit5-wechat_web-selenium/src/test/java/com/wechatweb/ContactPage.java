package com.wechatweb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * 将页面功能使用PO定义成一个类将核心对象定义完成（一个页面可以定义多个PO）
 */
public class ContactPage extends BaseToWeb {

  // po原则2：不要暴露页面内部实现细节
  private By parterInfo = By.cssSelector(".js_party_info");

  public ContactPage(WebDriver driver) {
    // 保存driver
    super(driver);
  }

  public ContactPage() {

  }

  // po原则6：添加成功的时候与添加失败返回的页面是不一样的，需要封装不同的方法

  /**
   * 添加成员
   */

  public ContactPage addMember(String username, String accountid, String mobile) {

    click(By.cssSelector("[node-type=addmember]"));
//    click(By.linkText("添加成员"));
    sendKeys(By.name("username"), "张三");
    sendKeys(By.id("memberAdd_acctid"), "Sunson");
    sendKeys(By.id("memberAdd_phone"), "18888888888");
    click(By.linkText("保存"));
    return this;
  }


  /**
   * 查询部门
   */


// po原则3：不要在po方法内部加断言
// po原则5：无需实现所有的方法，根据需求实现
  public ContactPage searchDepart(String departName) {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    sendKeys(By.id("memberSearchInput"), "火星测试部");
    String content = driver.findElement(parterInfo).getText();
    click(By.cssSelector(".ww_icon_AddMember"));
    return this;

  }

  //po原则1：使用公共方法代表页面所提供的功能
  public String getPartyInfo() {
    String content = driver.findElement(parterInfo).getText();
    return content;
  }

  /**
   * 添加部门用例
   */

  public ContactPage addNewDepart(String departName) throws InterruptedException {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    click(By.linkText("通讯录"));
    Thread.sleep(15000);
    click(By.cssSelector(".member_colLeft_top_addBtn"));
    click(By.linkText("添加部门"));
    sendKeys(By.name("name"), departName);
    click(By.linkText("选择所属部门"));
//        Thread.sleep(15000);
    driver.findElements(By.linkText("尹侦测试账号")).get(1).click();
//        click(By.xpath("(//ul[@role='group'])/[1]/li"));
    click(By.linkText("确定"));
    return this;
  }

  public void clearAllDeparts() {
    searchDepart("尹侦测试账号");
  }


  /**
   * 部门更新名称
   */
  public ContactPage updateDepartName(String departRename) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    click(By.linkText("通讯录"));
//    click(By.linkText("企划部"));
//    click(By.xpath("(//a[contains(text(),'企划部')]/span)"));
//    click(By.xpath("(//*/a)[21]"));
    click(By.id("1688853796192696_anchor"));
    click(By.xpath("//a[(@id='1688853796192696_anchor')]//span[@class='icon jstree-contextmenu-hover']"));
//    click(By.xpath("(//ul[@role='group'])/[1]/li"));

    click(By.linkText("修改名称"));
//    click(By.name("partyid"));
    sendKeys(By.name("name"), departRename);
    click(By.linkText("保存"));
    return this;

  }
}
