package wechat.department;

import wechat.apiobject.DepartmentObject;
import wechat.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.EnvHelperTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: 覆盖不同的入参组合，以数据驱动的方式将入参将代码剥离
 * @Date 2021/2/18 23:24
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Demo_Param {
    private static final Logger logger = LoggerFactory.getLogger(Demo_03_02_RepeatEvnClearTest.class);
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


    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    void createDepartment(String createName,String createEnName,String returnCode) {
        Response createResponse = DepartmentObject.createDepartment(createName, createEnName, accessToken);
        assertEquals(returnCode, createResponse.path("errcode").toString());

    }

//    @DisplayName("更新部门")
//    @ParameterizedTest
//    @CsvFileSource(resources = "/data/updateDepartment.csv",numLinesToSkip = 1)
//    void updateDepartment(String updateName,String updateEnName,String returnCode) {
//
//        String departmentId = DepartmentObject.createDepartment(accessToken);
//        Response updateResponse = DepartmentObject.updateDepartment(updateName,updateEnName,departmentId,accessToken);
//        assertEquals(returnCode, updateResponse.path("errcode").toString());
//    }
//
//    @DisplayName("查询部门")
//    @Test
//    @Order(3)
//    void searchDepartment() {
//        String departmentId = DepartmentObject.createDepartment(accessToken);
//        Response searchResponse = DepartmentObject.searchDepartment(departmentId,accessToken);
//        assertEquals("0", searchResponse.path("errcode").toString());
//        assertEquals(departmentId, searchResponse.path("department.id[0]").toString());
//    }
//
//    @DisplayName("删除部门")
//    @Test
//    @Order(4)
//    void deleteDepartment() {
//        String departmentId =DepartmentObject.createDepartment(accessToken);
//        Response deleteResponse = DepartmentObject.deleteDepartment(departmentId,accessToken);
//        assertEquals("0", deleteResponse.path("errcode").toString());
//
//    }
}

