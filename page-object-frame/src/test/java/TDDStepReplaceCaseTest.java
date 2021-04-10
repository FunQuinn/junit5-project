import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author FunQuinn
 * @Description: 测试用例裂变&替换yaml文件的变量
 * @Date 2020/12/01 23:01
 */
public class TDDStepReplaceCaseTest {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    private ChromeDriver driver;
    private WebElement currentElement;
    public int index = 0;

    /**
     * 测试用例裂变，基于数据自动生成多份测试用例
     * @return
     */

    //根据data的数量生成多份，每一份存一个不同的index值，进行初始化
    public List<TDDStepReplaceCaseTest> testcaseGenerate() {

        List<TDDStepReplaceCaseTest> testCaseList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {

            TDDStepReplaceCaseTest testCaseNew = new TDDStepReplaceCaseTest();
            //赋值
            testCaseNew.index = i;
            testCaseNew.steps=steps;
            testCaseNew.data=data;
            //每完成一个就加进去
            testCaseList.add(testCaseNew);
        }
        return testCaseList;

}

    //step统一替换
    private Object getValue(HashMap<String, Object> step, String key, Object defaultValue) {
        return step.getOrDefault(key, defaultValue);
    }

    /**
     * 统一替换yaml中的变量；复杂结构需要使用递归
     * @param step
     * @param key
     * @return
     */

    private Object getValue(HashMap<String, Object> step, String key) {
        //取出值
        Object value = step.get(key);
        //判断这个值是否是字符串
        if (value instanceof String) {
//           进行替换,返回结果 ->复杂结构需使用递归判断变量
            return ((String) value).replace("{data}", data.get(index));
        } else {
            return value;
        }
    }


    public void run() {
        steps.forEach(step -> {
            if (step.keySet().contains("chrome")) {
                driver = new
                        ChromeDriver();
            }
            if (step.keySet().contains("quit")) {
                driver.quit();
            }
            if (step.keySet().contains("sleep")){
                try {
                    Thread.sleep(Long.valueOf(getValue(step,"sleep").toString()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (step.keySet().contains("implicitly_wait")) {
                driver.manage().timeouts().implicitlyWait(
                        (int) getValue(step, "implicitly_wait", 5), TimeUnit.SECONDS);
            }

            if (step.keySet().contains("get")) {
                driver.get(getValue(step, "get").toString());
            }

            if (step.keySet().contains("find")) {
                ArrayList<By> bys = new ArrayList<>();
                ((HashMap<String, String>) getValue(step, "find")).entrySet().forEach(stringStringEntry -> {

                    if (stringStringEntry.getKey().contains("id")) {
                        bys.add(By.id(stringStringEntry.getValue()));
                    }
                    if (stringStringEntry.getKey().contains("xpath")) {
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }

                });

                currentElement = driver.findElement(bys.get(0));

            }

            if (step.keySet().contains("click")) {
                currentElement.click();
            }

            if (step.keySet().contains("send_keys")) {
                currentElement.sendKeys(getValue(step, "send_keys").toString());
            }

        });

    }

}
