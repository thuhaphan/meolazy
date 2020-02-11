package ApiTests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.*;
import org.junit.runners.MethodSorters;
import utils.Utils;

import java.util.HashMap;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //For Ascending order test execution
public class MakesTest {

    private Response response = null;
    private JsonPath jsonPath = null;

    //Instantiate a Helper Test Methods (htm) Object
    HelperTestMethods helperTestMethods = new HelperTestMethods();

    @Before
    public void setup() {
        //Test Setup
        Utils.setBaseURI(); //Setup Base URI
        Utils.setBasePath("vrs/powersports/makes"); //Setup Base Path
        Utils.setContentType(ContentType.JSON); //Setup Content Type
        Utils.createSearchQueryPath("4yb2g62my3z5detd38zt9839", "1", "500");
        response = Utils.getResponse(); //Get response
        jsonPath = Utils.getJsonPath(response); //Set JsonPath
    }

    @Test
    public void T01_statusCodeTest() {
        //Verify the http response status returned. Check Status Code is 200?
        helperTestMethods.checkStatusIs200(response);
    }

    @Test
    public void T02_verifyNumberOfMakes() {
        //Verify the number of records is correct
        Assert.assertEquals("The number of makes is wrong!", 331, jsonPath.getInt("count"));
    }

    @Test
    public void T03_verifyMakeName() {
        // Verify makeName matches makeId
        String makeName = null;
        List<HashMap<String, Object>> makes = jsonPath.getList("items");
        for (HashMap<String, Object> make : makes) {
            if ((Integer) make.get("makeId") == 2) {
                makeName = make.get("make").toString();
                break;
            }
        }

        Assert.assertEquals("MakeName doesn't match!", "Adly", makeName);
    }

    @After
    public void afterTest() {
        //Reset Values
        Utils.resetBaseURI();
        Utils.resetBasePath();
    }
}

