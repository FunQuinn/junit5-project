import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @Author FunQuinn
 * @Description: runner引擎
 * @Date 2020/11/27 23:19
 */
public class TDDStepsTest {
//    通过参数化用例来指明该参数属于这个方法
    @ParameterizedTest
    @MethodSource()
    void searchMethod(TDDStepCaseTest tddStepCaseTest){
        System.out.println(tddStepCaseTest);
//        runner引擎
        tddStepCaseTest.run();
    }


//    方法传回数据流，将数据转换成流
    static Stream<TDDStepCaseTest> searchMethod() throws IOException {
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());

       TDDStepCaseTest tddStepCaseTest = mapper.readValue(
//               通过yaml读取
                TDDStepsTest.class.getResourceAsStream("/framework/search_step.yaml"),
                TDDStepCaseTest.class);
        return Stream.of(tddStepCaseTest);

    }



}
