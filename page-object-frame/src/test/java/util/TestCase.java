package util;

import com.POTestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author FunQuinn
 * @Description:
 * @Date 2020/12/4 23:51
 */
public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    public int index = 0;

    /**
     * 测试用例裂变，基于数据自动生成多份测试用例
     *
     * @return
     */

    //根据data的数量生成多份，每一份存一个不同的index值，进行初始化
    public List<POTestCase> testcaseGenerate() {

        List<POTestCase> poTestCaseList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {

            POTestCase poTestCase = new POTestCase();
            //赋值
            poTestCase.index = i;
            poTestCase.steps = steps;
            poTestCase.data = data;
            //每完成一个就加进去
            poTestCaseList.add(poTestCase);
        }
        return poTestCaseList;

    }

    //step统一替换
    public Object getValue(HashMap<String, Object> step, String key, Object defaultValue) {
        return step.getOrDefault(key, defaultValue);
    }

    /**
     * 统一替换yaml中的变量；复杂结构需要使用递归
     *
     * @param step
     * @param key
     * @return
     */

    public Object getValue(HashMap<String, Object> step, String key) {
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

    }
}
