package ApiTests;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import model.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utils.ExcelReader;

import java.util.List;

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

    @Test
    public void TC01_registrationSuccessful_Excel()
    {
        RestAssured.baseURI ="http://restapi.demoqa.com";
        RestAssured.basePath = "/customer/register";
        RequestSpecification request = given();

        List<User> users = ExcelReader.Reader("data/Users.xlsx");
        Gson gson = new Gson();

        for (User user : users) {
            String json = gson.toJson(user);
            request.body(json);
            Response response = request.post();

            int statusCode = response.getStatusCode();
            Assert.assertEquals(201, statusCode);
            String successCode = response.jsonPath().get("SuccessCode");
            Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
            System.out.println("body: " + json);
        }
    }
}
