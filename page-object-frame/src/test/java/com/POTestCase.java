package com;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import util.TestCase;
import util.WebMainPage;
import util.WebSearchPage;

/**
 * @Author FunQuinn
 * @Description: 测试用例裂变&替换yaml文件的变量
 * @Date 2020/12/01 23:01
 */
public class POTestCase extends TestCase {

    private ChromeDriver driver;
    private WebElement currentElement;


    public void run() {
        steps.forEach(step -> {
            if (step.keySet().contains("WebMainPage")) {
                WebMainPage page = new WebMainPage();

            }
            if (step.keySet().contains("WebSearchPage")) {
                WebSearchPage page = new WebSearchPage(driver);
            }

        });

    }

}
