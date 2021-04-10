package util;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @Author FunQuinn
 * @Description:
 * @Date 2020/12/3 23:04
 */
public class WebMainPage extends WebBasePage {

    //初始化
    public WebMainPage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
    }

    public WebSearchPage search() {
        return new WebSearchPage(driver);

    }
}
