import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Request {
    static ExtractableResponse<Response> doGetRequest(String endpoint, int pageId) {
        RestAssured.defaultParser = Parser.JSON;
        if (pageId == 0) {
            return
                    given().contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .when().get(endpoint)
                            .then().contentType(ContentType.JSON).statusCode(200).extract();
        }
        return
                given().contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when().get(endpoint, pageId)
                        .then().contentType(ContentType.JSON).statusCode(200).extract();
    }

    public static String setUrl(){
        return RestAssured.baseURI = "https://reqres.in";
    }
}
