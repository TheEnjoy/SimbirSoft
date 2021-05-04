import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class Request {
    public static String path = "";

    public static ExtractableResponse<Response> doGetRequest(HashMap<String, Integer> paramsEndpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().log().all().contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .params(paramsEndpoint)
                        .when().get(path)
                        .then().contentType(ContentType.JSON).statusCode(200).extract();
    }

    public static void setBaseURL(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public static void setPath(String path) {
//        RestAssured.basePath = path;
//        "/api/users"
    }
}
