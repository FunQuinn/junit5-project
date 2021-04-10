package wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: 优化项：部门名称入参重复，再次执行会报错，需对数据进行还原
 *               优化方案：每个方法执行前后都对历史数据进行清理，确保每次执行的脚本数据环境的一致性
 *               缺点：多人使用同一套环境的情况下，不太方便执行该脚本；该脚本只适用于查看断言结果，但数据不可追溯，无法统计，不利于分析
 * @Date 2020/12/21 22:32
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03_02_RepeatEvnClearTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_03_02_RepeatEvnClearTest.class);

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
        logger.info(accessToken);
    }

    @BeforeEach
    @AfterEach
    void clearDepartment() {
        Response listResponse = given().log().all()
                .when()
                .param("id", 1) //查询根部门id
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        //多个部门,集合方式，遍历迭代器
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for (int departmentId : departmentIdList) {
            if (1 == departmentId) {
                continue;
            }
            Response deleteResponse = given().log().all()
                    .contentType("application/json")
                    .param("access_token", accessToken)
                    .param("id", departmentId)
                    .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                    .then()
                    .log().body()
                    .extract().response();
        }
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
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then().log().all()
                .extract()
                .response();
        departmentId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;
        assertEquals("0", createResponse.path("errcode").toString());

    }

    @DisplayName("更新部门")
    @Test
    @Order(2)
    void updateDepartment() {
        String createBody = "{\n" +
                "   \"name\": \"研发中心\",\n" +
                "   \"name_en\": \"GPDZ\",\n" +
                "   \"parentid\": 1}";
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .log().all()
                .extract()
                .response();
        String departmentId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;

        String updateBody = "{\n" +
                "   \"id\": " + departmentId + ",\n" +
                "   \"name\": \"一点通小组\",\n" +
                "   \"name_en\": \"ABC\",\n" +
                "   \"order\": 1\n" +
                "}";
        Response updateResponse = given().log().all()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", updateResponse.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void searchDepartment() {
        String createBody = "{\n" +
                "   \"name\": \"研发中心\",\n" +
                "   \"name_en\": \"GPDZ\",\n" +
                "   \"parentid\": 1}";
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then().log().all()
                .extract()
                .response();
        String departmentId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;

        Response searchResponse = given().log().all()
                .when()
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", searchResponse.path("errcode").toString());
        assertEquals(departmentId, searchResponse.path("department.id[0]").toString());
    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {
        String createBody = "{\n" +
                "   \"name\": \"研发中心北京分公司\",\n" +
                "   \"name_en\": \"XYZ\",\n" +
                "   \"parentid\": 1}";
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then().log().all()
                .extract()
                .response();
        String departmentId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;

        Response deleteResponse = given().log().all()
                .contentType("application/json")
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response();
        assertEquals("0", deleteResponse.path("errcode").toString());

    }
}
