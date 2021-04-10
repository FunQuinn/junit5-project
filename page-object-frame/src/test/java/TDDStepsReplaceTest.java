import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

/**
 * @Author FunQuinn
 * @Description: runner引擎
 * @Date 2020/12/01 23:00
 */
public class TDDStepsReplaceTest {
    @ParameterizedTest
    @MethodSource()
    void searchMethod(TDDStepReplaceCaseTest tddStepReplaceCaseTest){
        System.out.println(tddStepReplaceCaseTest);
//        runner引擎
        tddStepReplaceCaseTest.run();
    }

//    方法传回数据流，将数据转换成流
    static List<TDDStepReplaceCaseTest> searchMethod() throws IOException {
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());

       TDDStepReplaceCaseTest tddStepReplaceCaseTest = mapper.readValue(
//               通过yaml读取
                TDDStepsReplaceTest.class.getResourceAsStream("/framework/search_step_replace.yaml"),
                TDDStepReplaceCaseTest.class);
 return tddStepReplaceCaseTest.testcaseGenerate();
    }



}
