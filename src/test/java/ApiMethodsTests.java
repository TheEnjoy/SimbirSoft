import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiMethodsTests {
    @BeforeClass
    void setBaseUrl() {
        Request.setBaseURL("https://reqres.in");
//        Request.setPath("");
        Request.path = "/api/users";
    }

    @Test
    public void postsResponseWithExpectedGeorgeUsers() {
        HashMap<String, Integer> paramUrl = new HashMap<>();
        Response response = Request.doGetRequest(paramUrl).response();
        List<Map<String, String>> userList = response.jsonPath().getList("data");
        List<Map<String, String>> filtered = userList.stream()
                .filter(map -> "George".equals(map.get("first_name")) && "Bluth".equals(map.get("last_name")))
                .distinct()
                .collect(Collectors.toList());
        filtered.forEach(entry -> assertThat(entry.get("email").toLowerCase(Locale.ROOT),
                equalTo("george.bluth@reqres.in")));

    }

    @Test
    public void postsResponseWithExpectedMichael() {
        HashMap<String, Integer> paramUrl = new HashMap<>();
        Response response = Request.doGetRequest(paramUrl).response();
        int totalPages = response.jsonPath().getInt("total_pages");
        List<Map<String, String>> filtered = null;
        List<Map<String, String>> userList;
        for (int i = 1; i <= totalPages; i++) {
            paramUrl.put("page", i);
            response = Request.doGetRequest(paramUrl).response();
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
