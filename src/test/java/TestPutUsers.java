import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestPutUsers {

    String baseUrl = "https://reqres.in/api";

    @Test
    public void updateUser() {

        RestAssured.baseURI = baseUrl;

        JSONObject request = new JSONObject();
        request.put("name", "Carla");
        request.put("job", "Test Analyst");
        System.out.println(request.toJSONString());

        given().
                body(request.toJSONString()).
                when().
                put(baseUrl + "/users/945").
                then().
                statusCode(200).log().all();
    }
}
