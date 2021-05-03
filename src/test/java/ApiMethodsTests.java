import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiMethodsTests {
    @BeforeSuite
    void set_base_url() {
        Request.setUrl();
    }

    @Test
    public void posts_response_return_200_with_expected_george_users() {
        Response response = Request.doGetRequest(EndPoints.USERS, 0).response();
        List<Map<String, String>> userList = response.jsonPath().getList("data");
        List<Map<String, String>> filtered = userList.stream()
                .filter(map -> "George".equals(map.get("first_name")) && "Bluth".equals(map.get("last_name")))
                .distinct()
                .collect(Collectors.toList());
        filtered.forEach(entry -> assertThat(entry.get("email").toLowerCase(Locale.ROOT),
                equalTo("george.bluth@reqres.in")));

    }

    @Test
    public void posts_response_return_200_with_expected_michael() {
        Response response = Request.doGetRequest(EndPoints.USERS, 0).response();
        int totalPages = response.jsonPath().getInt("total_pages");
        List<Map<String, String>> filtered = null;
        List<Map<String, String>> userList;
        for (int i = 1; i <= totalPages; i++) {
            response = Request.doGetRequest(EndPoints.PAGE, i).response();
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
