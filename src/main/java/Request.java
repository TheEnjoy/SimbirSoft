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
    protected ExtractableResponse<Response> get(String url, String path, HashMap<String, Integer> params) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().log()
                        .all()
                        .baseUri(url)
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .params(params)
                        .when().get(path)
                        .then().contentType(ContentType.JSON).statusCode(200).extract();
    }

}
