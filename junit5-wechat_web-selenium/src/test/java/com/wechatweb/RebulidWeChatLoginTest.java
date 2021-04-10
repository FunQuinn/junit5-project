package com.wechatweb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RebulidWeChatLoginTest {
    public static WebDriver driver;


    public static void needLogin() throws IOException, InterruptedException {

        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //保存cookies
        mapper.writeValue(new File("cookies.yaml"), cookies);
        System.exit(0);

    }

    @BeforeAll
    public static void beforeAll() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        if (file.exists()) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };

            List<HashMap<String, Object>> cookies = mapper.readValue(file, typeReference);
            System.out.println(cookies);

            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });


            driver.navigate().refresh();
        } else {
            needLogin();
        }
    }

    @Test
    public void addMember() {
        driver.findElement(By.cssSelector("[node-type=addmember]")).click();
        driver.findElement(By.name("username")).sendKeys("sarah");
        driver.findElement(By.id("memberAdd_acctid")).sendKeys("sarah123");
        driver.findElement(By.name("mobile")).sendKeys("18901016666");
        driver.findElement(By.linkText("保存")).click();

    }

    @Test
    public void departSearch() {
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"), "火箭组");
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();

        click(By.cssSelector(".ww_icon_AddMember"));
         content = driver.findElement(By.cssSelector(".js_party_info")).getText();

        assertTrue(content.contains("当前部门无任何成员"));
    }

    /**
     * 对元素进行封装
     */
    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    @Test
    public void secordAddMemTest() {
        click(By.cssSelector("[node-type=addmember]"));
        sendKeys(By.name("username"), "Sarah");
        sendKeys(By.id("memberAdd_acctid"), "sarah123");
        sendKeys(By.name("mobile"), "18901016666");
        click(By.linkText("保存"));
    }
}




