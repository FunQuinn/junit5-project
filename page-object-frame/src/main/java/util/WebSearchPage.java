package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Author FunQuinn
 * @Description:
 * @Date 2020/12/4 22:25
 */
public class WebSearchPage extends WebBasePage{
    public WebSearchPage(WebDriver driver){
        super(driver);
    }

    public void search(String keyword){
        click(By.id("search-button"));
        sendKeys(By.id("search-term"),keyword);
    }
}
