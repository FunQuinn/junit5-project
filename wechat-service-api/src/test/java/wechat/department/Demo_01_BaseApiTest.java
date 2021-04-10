package wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: department show demo
 * 基础脚本：执行创建部门、修改部门、查询部门、删除部门的接口并进行校验
 * 缺点：需要依赖第一个create方法，且只能执行一次
 * @Date 2020/12/21 22:08
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_01_BaseApiTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_BaseApiTest.class);

    static String accessToken;
    static String departmentId;

    @BeforeAll
    public static void getAccessToken() {
        accessToken = given().log().all()
                .when()
                .param("corpid", "ww42ddcc27cfe6b2d7")
                .param("corpsecret", "Q3DZ1XfsuFZyjyJviyG9mz9iKiQfJz-PgHu9mWlnRTo")
                .param("", "https://qyapi.weixin.qq.com/cgi-bin/agent/set_scope?access_token=ACCESS_TOKEN")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");

    }

    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment() {
        String createBody = "{\n" +
                "   \"name\": \"部门研发中心\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}\n";
        Response response = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then().log().all()
                .extract()
                .response();
        departmentId = response.path("id").toString();
//        logger.info(accessToken);
    }

    @DisplayName("更新部门")
    @Test
    @Order(2)
    void updateDepartment() {
        String updateBody = "{\n" +
                "   \"id\": " + departmentId + ",\n" +
                "   \"name\": \"北京研发中心小组\",\n" +
                "   \"name_en\": \"GRBC\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response response = given().log().all()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", response.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void searchDepartment() {
        Response response = given().log().all()
                .when()
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", response.path("errcode").toString());
        assertEquals(departmentId, response.path("department.id[0]").toString());
    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {
        Response response = given().log().all()
                .contentType("application/json")
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response();
        assertEquals("0", response.path("errcode").toString());

    }
}
