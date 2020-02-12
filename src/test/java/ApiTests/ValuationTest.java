package ApiTests;

import com.github.javafaker.Faker;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

//@SuppressWarnings("unchecked")
public class ValuationTest {

    @Test
    public void TC01_registrationSuccessful()
    {
        RestAssured.baseURI ="http://restapi.demoqa.com";
        RestAssured.basePath = "/customer/register";
        RequestSpecification request = given();

        Faker faker = new Faker();

        String username = faker.name().username();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "First"); // Cast
        requestParams.put("LastName", "Last");
        requestParams.put("UserName", username);
        requestParams.put("Password", "password1");
        requestParams.put("Email",  username+"@gmail.com");

        request.body(requestParams.toString());
        Response response = request.post();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }

    //@Test
    public void T04_postMethod() {
        RestAssured.baseURI ="https://sandbox.api.kbb.com/vrs";
        RequestSpecification request = given();

        JSONObject requestParams = new JSONObject();
        JSONObject child = new JSONObject();
        child.put("vehicleId", "413460");
        requestParams.put("configuration", child);
        requestParams.put("Mileage", "20000");
        requestParams.put("ZipCode", "92503");

        request.body(requestParams.toString());
        Response response = request.post("/valuation/values?api_key=4yb2g62my3z5detd38zt9839");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }
}
