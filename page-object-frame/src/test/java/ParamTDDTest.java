import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author FunQuinn
 * @Description: 数据驱动.yaml入门示例
 * @Date 2020/11/27 23:19
 */
public class ParamTDDTest {
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument){
        assertNotNull(argument);

    }

static Stream<String> stringProvider(){
        return Stream.of("apple","orange");
}

@ParameterizedTest
@ValueSource(strings = {"API","Junit"})
    void search(String keywords){
        ChromeDriver driver=new
                ChromeDriver();
driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
driver.get("https://ceshiren.com/");
driver.findElement(By.id("search-button")).click();
driver.findElement(By.id("search-term")).sendKeys(keywords);

}

    @ParameterizedTest
    @MethodSource()
    void searchMethod(String keywords){
        ChromeDriver driver=new
                ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys(keywords);

    }

    static List<String> searchMethod() throws IOException {
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
     TypeReference<List<String>> typeReference=new TypeReference<List<String>>() {};
        List<String> keywords = mapper.readValue(
                ParamTDDTest.class.getResourceAsStream("/framework/search.yaml"),
                typeReference);
        return keywords;

    }

}
