package com.wechatweb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WeChatLoginTest {
        public static WebDriver driver;


        @BeforeAll
        public static void InitLogin() {
            driver = new ChromeDriver();
/**
 * 隐式等待写法
 */
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        @Test
        public void weChatTest() throws IOException, InterruptedException {

            driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome_qyh_redirect");

            Thread.sleep(15000);
            Set<Cookie> cookies = driver.manage().getCookies();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            //保存cookies
            mapper.writeValue(new File("cookies.yaml"), cookies);


        }

        @Test
        public void weChatLoginTest() throws IOException, InterruptedException {

            driver.get("https://work.weixin.qq.com/wework_admin/frame");


            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference=new TypeReference<List<HashMap<String, Object>>>() {};
            List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
            System.out.println(cookies);

            cookies.forEach(cookieMap->{
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });


            driver.navigate().refresh();
        }
    }




