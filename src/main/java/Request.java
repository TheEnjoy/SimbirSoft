import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Request {
    private static final String URL = "https://reqres.in";

    protected ExtractableResponse<Response> get(String path, HashMap<String, Integer> params) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().log()
                        .all()
                        .baseUri(URL)
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .params(params)
                        .when().get(path)
                        .then().contentType(ContentType.JSON).statusCode(200).extract();
    }

}
