package wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FakerUtils;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author FunQuinn
 * @Description: 优化项：部门名称入参重复，再次执行会报错，需去除重复
 *               优化方案：增加时间戳命名方法动态生成数据，避免重复数据
 * @Date 2020/12/21 22:08
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03_01_RepeateTimeStampsApiTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_03_01_RepeateTimeStampsApiTest.class);

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
        String name = "name" + FakerUtils.getTimeStamp();
        String enName = "enName" + FakerUtils.getTimeStamp();
        String createBody = "{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"name_en\": \""+enName+"\",\n" +
                "   \"parentid\": 1}";
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
        String name = "name" + FakerUtils.getTimeStamp();
        String enName = "enName" + FakerUtils.getTimeStamp();
        String createBody = "{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"name_en\": \""+enName+"\",\n" +
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
                "   \"name\": \""+name+"\",\n" +
                "   \"name_en\": \""+enName+"\",\n" +
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
        String name = "name" + FakerUtils.getTimeStamp();
        String enName = "enName" + FakerUtils.getTimeStamp();
        String createBody = "{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"name_en\": \""+enName+"\",\n" +
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
        String name = "name" + FakerUtils.getTimeStamp();
        String enName = "enName" + FakerUtils.getTimeStamp();
        String createBody = "{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"name_en\": \""+enName+"\",\n" +
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
