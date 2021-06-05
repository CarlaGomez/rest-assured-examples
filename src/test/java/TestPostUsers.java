import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestPostUsers {

    String baseUrl = "https://reqres.in/api";

    @Test
    public void postUserUsingHashMap() {

        RestAssured.baseURI = baseUrl;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Carla");
        map.put("job", "QA Engineer");
        System.out.println(map);

        JSONObject request = new JSONObject(map);

        given().
                body(request.toJSONString()).
                when().
                post("users?page=2").
                then().
                statusCode(201);
    }
}
