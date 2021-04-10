package wechat.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @Author FunQuinn
 * @Description:封装token
 * @Date 2021/2/18 23:19
 */
public class TokenHelper {
    public static String getAccessToken() {
     String accessToken = given()
                .when()
                .param("corpid", "ww42ddcc27cfe6b2d7")
                .param("corpsecret", "Q3DZ1XfsuFZyjyJviyG9mz9iKiQfJz-PgHu9mWlnRTo")
                .param("", "https://qyapi.weixin.qq.com/cgi-bin/agent/set_scope?access_token=ACCESS_TOKEN")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response()
                .path("access_token");
        return accessToken;
    }
}
