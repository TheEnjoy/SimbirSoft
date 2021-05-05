import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.HashMap;

public class UserRequests {

    public static ExtractableResponse<Response> getUsers(HashMap<String, Integer> params) {
        String path = "/api/users";
        Request customRequest = new Request();
        return customRequest.get(path, params);
    }

}
