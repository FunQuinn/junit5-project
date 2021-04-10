import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author FunQuinn
 * @Description: BaseCase
 * @Date 2020/11/27 23:19
 */
public class ParamBaseTest {
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument){
        assertNotNull(argument);

    }

static Stream<String> stringProvider(){
        return Stream.of("apple","orange");
}

@Test
    void search(){
        ChromeDriver driver=new
                ChromeDriver();
driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
driver.get("https://ceshiren.com/");
driver.findElement(By.id("search-button")).click();
driver.findElement(By.id("search-term")).sendKeys("API Documentation");

}


}
