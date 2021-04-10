package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Author FunQuinn
 * @Description:
 * @Date 2020/12/3 23:04
 */
public class WebBasePage {
    WebDriver driver;


    public WebBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebBasePage() {

    }

    void click(By by) {
        driver.findElement(by).click();

    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }
}
