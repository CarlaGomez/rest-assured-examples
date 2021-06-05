import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestGetUsers {

    String baseUrl = "https://reqres.in/api";

    @Test
    void getAllUsers() {

        RestAssured.baseURI = baseUrl;

        given().
                param("page", "2").
                get(baseUrl + "/users").
                then().
                statusCode(200).log().body();
    }

    @Test
    public void getUserById() {

        RestAssured.baseURI = baseUrl;

        given().
                param("page", 2).
                param("id", 8).
                when().
                get(baseUrl + "/users").
                then().
                statusCode(200).
                assertThat().body("data.id", equalTo(8)).and().
                body("data.first_name", is("Lindsay")).
                log().body();
    }
}
