import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

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
                post(baseUrl + "/users?page=2").
                then().
                statusCode(201);
    }

    @Test
    public void postUserUsingJsonRequest() {

        RestAssured.baseURI = baseUrl;

        given()
                .accept(ContentType.JSON)
                .body("{\"name\":\"juan\", \"job\":\"developer\"}") //I am using a JSON request body, the best way to do it is creating a POJO instead
                .post(baseUrl + "/users")
                .then().log().ifValidationFails()
                .statusCode(201)
                .body("$", hasKey("id"))
                .and()
                .body("$", hasKey("createdAt")).log().all()
        ;
    }

    @DataProvider(name = "DataForPost")
    public Object[][] dataForPost() {

        return new Object[][]{
                {"Carla", "QA Engineer"},
                {"Manuel", "Project Manager"}
        };
    }

    @Test(dataProvider = "DataForPost")
    public void postUserDataDriven(String name, String job) {

        RestAssured.baseURI = baseUrl;

        JSONObject request = new JSONObject();
        request.put("name", name);
        request.put("job", job);
        System.out.println(request.toJSONString());

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type", "application/json").
                body(request.toJSONString()).
                when().
                post(baseUrl + "/users?page=2").
                then().
                statusCode(201).
                log().all();
    }
}
