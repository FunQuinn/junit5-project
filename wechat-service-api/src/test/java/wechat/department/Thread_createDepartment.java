package wechat.department;

import wechat.apiobject.DepartmentObject;
import wechat.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * @Author FunQuinn
 * @Description:  对创建部门进行并发测试
 * @Date 2021/4/7 23:06
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Thread_createDepartment {
    private static final Logger logger = LoggerFactory.getLogger(Thread_createDepartment.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);

    }

    @DisplayName("创建部门")
    @RepeatedTest(100)
    @Execution(CONCURRENT)
    void createDepartment() {
        String createName = "namelucky";
        String createEnName = "en_namelucky01";

        Response creatResponse = DepartmentObject.createDepartment(createName, createEnName, accessToken);
        assertEquals("0", creatResponse.path("errcode").toString());
    }
}