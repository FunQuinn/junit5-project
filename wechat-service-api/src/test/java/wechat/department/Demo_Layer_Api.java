package wechat.department;

import wechat.apiobject.DepartmentObject;
import wechat.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.EnvHelperTask;
import utils.FakerUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: 分层逻辑，减少代码重复率及维护成本
 * @Date 2021/2/18 23:24
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Demo_Layer_Api {
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
//        调用task包下的方法
        EnvHelperTask.clearDepartMentTask(accessToken);

//        Response listResponse = DepartmentObject.searchDepartment("1", accessToken);
//        //多个部门,集合方式，遍历迭代器
//        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
//        for (int departmentId : departmentIdList) {
//            if (1 == departmentId) {
//                continue;
//            }
//            Response deleteResponse = DepartmentObject.deleteDepartment(departmentId + "", accessToken);
//        }
    }

    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment() {
        String name = "name" + FakerUtils.getTimeStamp();
        String enName = "enName" + FakerUtils.getTimeStamp();
        Response createResponse = DepartmentObject.createDepartment(name, enName, accessToken);
        assertEquals("0", createResponse.path("errcode").toString());

    }

    @DisplayName("更新部门")
    @Test
    @Order(2)
    void updateDepartment() {
        String updateName="name" + FakerUtils.getTimeStamp();
        String updateEnName ="enName" + FakerUtils.getTimeStamp();
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response updateResponse = DepartmentObject.updateDepartment(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0", updateResponse.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void searchDepartment() {
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response searchResponse = DepartmentObject.searchDepartment(departmentId,accessToken);
        assertEquals("0", searchResponse.path("errcode").toString());
        assertEquals(departmentId, searchResponse.path("department.id[0]").toString());
    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {
        String departmentId =DepartmentObject.createDepartment(accessToken);
        Response deleteResponse = DepartmentObject.deleteDepartment(departmentId,accessToken);
        assertEquals("0", deleteResponse.path("errcode").toString());

    }
}

