import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.HashMap;

public class UserRequests {

    public static ExtractableResponse<Response> getUserRequest(HashMap<String, Integer> params) {
        String path = "/api/users";
        String url = "https://reqres.in";
        Request customRequest = new Request();
        return customRequest.get(url, path, params);
    }

}
