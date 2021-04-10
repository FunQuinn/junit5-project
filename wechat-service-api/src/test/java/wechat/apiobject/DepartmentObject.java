package wechat.apiobject;

import io.restassured.response.Response;
import utils.FakerUtils;

import static io.restassured.RestAssured.given;

/**
 * @Author FunQuinn
 * @Description: 封装部门
 * @Date 2021/2/18 23:30
 */
public class DepartmentObject {
    public static Response createDepartment(String createName,String createEnName,String accessToken) {
        String createBody = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +    //使用参数进行数据驱动
                "   \"name_en\": \"" + createEnName + "\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}\n";
        Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then().log().all()
                .extract()
                .response();
        return createResponse;
    }

    //生成随机数
    public static String createDepartment(String accessToken) {
        String createName = "name" + FakerUtils.getTimeStamp();
        String createEnName = "en_name" + FakerUtils.getTimeStamp();
        Response createResponse = createDepartment(createName, createEnName, accessToken);//重载createDepartment
        String departmentId = createResponse.path("id") != null ? createResponse.path("id").toString() : null;
        return departmentId;
    }

//更新部门封装
    public static Response updateDepartment(String updateName, String updateEnName, String departmentId, String accessToken) {
        String updateBody = "{\n" +
                "   \"id\": " + departmentId + ",\n" +
                "   \"name\": " + updateName + "\",\n" +
                "   \"name_en\": " + updateEnName + "\",\n" +
                "   \"order\": 1\n" +
                "}\n";
        Response updateResponse = given().log().all()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        return updateResponse;
    }

    //查询部门参数化封装
    public static Response searchDepartment(String departmentId, String accessToken) {
        Response searchResponse = given().log().all()
                .when()
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        return searchResponse;
    }

//删除部门封装
    public static Response deleteDepartment(String departmentId, String accessToken) {
        Response deleteResponse = given().log().all()
                .contentType("application/json")
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response();
        return deleteResponse;
    }
}
