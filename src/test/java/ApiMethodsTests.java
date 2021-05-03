import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiMethodsTests {
    static ExtractableResponse<Response> doGetRequest(String endpoint, int id) {
        RestAssured.defaultParser = Parser.JSON;
        if (id == 0) {
            return
                    given().contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .when().get(endpoint)
                            .then().contentType(ContentType.JSON).statusCode(200).extract();
        }
        return
                given().contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when().get(endpoint, id)
                        .then().contentType(ContentType.JSON).statusCode(200).extract();
    }

    @BeforeSuite
    void set_base_url() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void
    posts_response_return_200_with_expected_george_users() {
        Response response = doGetRequest(EndPoints.USERS, 0).response();
        List<Integer> idList = response.jsonPath().getList("data");
        List<Map<String, String>> userList = response.jsonPath().getList("data");
        List<Map<String,String>> filtered = userList.stream()
                .filter(map -> "George".equals(map.get("first_name")) && "Bluth".equals(map.get("last_name")))
                .distinct()
                .collect(Collectors.toList());
        filtered.forEach(entry ->
                assertThat(entry.get("email").toLowerCase(Locale.ROOT), equalTo("george.bluth@reqres.in")));

    }

    @Test
    void
    posts_response_return_200_with_expected_michael() {
        Response response = doGetRequest(EndPoints.USERS, 0).response();
        int totalPages = response.jsonPath().getInt("total_pages");
        List<Map<String,String>> filtered = null;
        List<Map<String, String>> userList = null;
        for (int i = 1; i <= totalPages; i++) {
            response = doGetRequest(EndPoints.PAGE, i).response();
            //assertThat(response, hasSize(10));
            userList = response.jsonPath().getList("data");
            filtered = userList.stream()
                    .filter(map -> "Michael".equals(map.get("first_name"))
                            && "Lawson".equals(map.get("last_name"))
                            && map.get("email").startsWith("michael.lawson@reqres"))
                    .distinct()
                    .collect(Collectors.toList());
        }
        Assert.assertFalse(filtered.isEmpty());
    }
}
