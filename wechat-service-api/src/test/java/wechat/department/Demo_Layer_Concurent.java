package wechat.department;

import wechat.apiobject.DepartmentObject;
import wechat.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FakerUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description:  并发测试
 * @Date 2021/3/7 23:19
 */
public class Demo_Layer_Concurent {
    private static final Logger logger = LoggerFactory.getLogger(Demo_Layer_Concurent.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken= TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

    @DisplayName("创建部门")
    @Test
    @RepeatedTest(10)
    void createDepartment() {
        String backendStr = Thread.currentThread().getId()+ FakerUtils.getTimeStamp()+"";

        String name = "name"+ backendStr;
        String enName = "en_name"+backendStr;

        Response response= DepartmentObject.createDepartment(name,enName,accessToken);
        assertEquals("0",response.path("errcode").toString());

    }
    @DisplayName("修改部门")
    @Test
    @RepeatedTest(10)
    void updateDepartment() {
        String backendStr = Thread.currentThread().getId()+ FakerUtils.getTimeStamp()+"";

        String createName = "name"+ backendStr;
        String creatEnName = "en_name"+backendStr;

        Response creatResponse = DepartmentObject.createDepartment(createName,creatEnName,accessToken);
        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;

        String updateName = "name"+ backendStr;
        String updateEnName = "en_name"+backendStr;

        Response updateResponse = DepartmentObject.updateDepartment(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }
}
