package ApiTests;

import com.jayway.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class HelperTestMethods {
    //Verify the http response status returned. Check Status Code is 200?
    public void checkStatusIs200 (Response res) {
        assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }
}
