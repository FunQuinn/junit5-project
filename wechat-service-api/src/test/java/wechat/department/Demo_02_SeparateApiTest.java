package wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: 优化项：方法间进行解耦，使每个方法可以独立运行
 *               优化方案：每个方法加上create方法
 *               缺点:入参数据固定，只能执行一次，再执行会数据重复，导致报错，需手动修改参数值再执行
 * @Date 2020/12/21 22:08
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_02_SeparateApiTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_02_SeparateApiTest.class);

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
        departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;
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
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then()
                .log().all()
                .extract()
                .response();
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;

        String updateBody = "{\n" +
                "   \"id\": "+departmentId+",\n" +
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
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;

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
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;

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
