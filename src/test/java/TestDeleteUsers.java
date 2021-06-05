import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class TestDeleteUsers {

    String baseUrl = "https://reqres.in/api";

    @Test
    public void deleteUser() {

        RestAssured.baseURI = baseUrl;

        when().
                delete(baseUrl + "/users/945").
                then().
                statusCode(204);
    }

    @DataProvider(name = "DeleteData")
    public Object[] dataForDelete() {
        return new Object[]{
                712, 988
        };
    }

    @Test(dataProvider = "DeleteData")
    public void deleteUserById(int userId) {

        RestAssured.baseURI = baseUrl;
        when().
                delete(baseUrl + "/users/" + userId).
                then().
                statusCode(204);
    }
}
