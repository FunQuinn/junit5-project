package wechat.department;

import wechat.apiobject.DepartmentObject;
import wechat.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.EnvHelperTask;
import utils.FakerUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: 使用lambdas软断言，增加脚本的容错性
 * @Date 2021/3/7 22:02
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_SoftAssert {
        private static final Logger logger = LoggerFactory.getLogger(wechat.department.Demo_03_02_RepeatEvnClearTest.class);
        static String accessToken;


        @BeforeAll
        public static void getAccessToken() {
            //调用tokenhelep
            accessToken = TokenHelper.getAccessToken();
            logger.info(accessToken);
        }

        @BeforeEach
        @AfterEach
        void clearDepartment() {
            EnvHelperTask.clearDepartMentTask(accessToken);
        }

        @DisplayName("查询部门")
        @Test
        void searchDepartment() {
            String createName = "name" + FakerUtils.getTimeStamp();
            String creatEnName = "enName" + FakerUtils.getTimeStamp();
            Response createResponse = DepartmentObject.createDepartment(createName, creatEnName, accessToken);

            String departmentId = createResponse.path("id"!=null ? createResponse.path("id").toString():null);
            Response searchResponse = DepartmentObject.searchDepartment(departmentId,accessToken);
         assertAll("断言结果",
             ()->assertEquals("1",searchResponse.path("errcode").toString()),
                        ()->assertEquals(departmentId+"x",searchResponse.path("department.id[0]").toString()),
                        ()->assertEquals(createName+"x",searchResponse.path("department.name[0]").toString()),
                        ()->assertEquals(creatEnName+"x",searchResponse.path("department.name_en[0]").toString())
            );
        }

    }



