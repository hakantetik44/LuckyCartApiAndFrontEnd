package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import pages.LuckCart;
import pojos.GetBaseUrl;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class LuckStep {

    LuckCart luckCart = new LuckCart();
    GetBaseUrl getBaseUrl = new GetBaseUrl();


    @Given("User go to site {string} and validate {int} status codes")
    public void userGoToSiteAndValidateStatusCodes(String Url, int Code401) {
        String jsonBody = "{\n" +
                "    \"cartId\": \"eligible_test_2\",\n" +
                "    \"totalAti\": 60.00,\n" +
                "    \"shopperId\": \"eligible_test_2\",\n" +
                "    \"shopperEmail\": \"eligible_test_2@luckycart.com\",\n" +
                "    \"auth_v\": \"2.0\",\n" +
                "    \"auth_key\": \"toa1S6\",\n" +
                "    \"auth_ts\": \"16491600\",\n" +
                "    \"auth_sign\": \"c723c649c389d68d8ab3feb4f53875f7f7eb87d27ec575f1f06a66e3dae4dc30\"\n" +
                "}\n}";

        Response response = given().headers(

                        "Accept", ContentType.JSON)
                .when()
                .body(jsonBody.toString()) // this comes from JsonModels class for Country and States
                .post(Url)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        response.prettyPrint();
        response.then().assertThat().statusCode(Code401);  // assertion

    }

    @Given("User go to site {string} and validate {int} status codes for without game")
    public void userGoToSiteAndValidateStatusCodesForWithoutGame(String url, int Code200) {

           String jsonBody200="{\n" +
                   "    \"cartId\": \"not_eligible_test_1\",\n" +
                   "    \"totalAti\": 30.00,\n" +
                   "    \"shopperId\": \"not_eligible_test_1\",\n" +
                   "    \"shopperEmail\": \"not_eligible_test_1@luckycart.com\",\n" +
                   "    \"auth_v\": \"2.0\",\n" +
                   "    \"auth_key\": \"tVIoa1S6\",\n" +
                   "    \"auth_ts\": \"1640991600\",\n" +
                   "    \"auth_sign\": \"c723c649c389d68d8ab3feb4f53875f7f7eb87d27ec575f1f06a66e3dae4dc30\"\n" +
                   "}";


        Response response=given().
                contentType(ContentType.JSON).
                body(jsonBody200.toString()).
                when().
                post(url);

        response.prettyPrint();
        response.then().assertThat().statusCode(Code200);       // assertion






    }

    @Given("User go to site {string} and validate {int} status codes for with game")
    public void userGoToSiteAndValidateStatusCodesForWithGame(String Url, int Code200) {

        String jsonBody200="{\n" +
                "    \"cartId\": \"eligible_test_2\",\n" +
                "    \"totalAti\": 60.00,\n" +
                "    \"shopperId\": \"eligible_test_2\",\n" +
                "    \"shopperEmail\": \"eligible_test_2@luckycart.com\",\n" +
                "    \"auth_v\": \"2.0\",\n" +
                "    \"auth_key\": \"tVIoa1S6\",\n" +
                "    \"auth_ts\": \"1640991600\",\n" +
                "    \"auth_sign\": \"c723c649c389d68d8ab3feb4f53875f7f7eb87d27ec575f1f06a66e3dae4dc30\"\n" +
                "}\n";


        Response response=given().
                contentType(ContentType.JSON).
                body(jsonBody200.toString()).
                when().
                post(Url);

        response.prettyPrint();
        response.then().assertThat().statusCode(Code200);       // assertion




    }

    @Given("entered {string},{string},{string} Go to de basedesktopUrl")
    public void enteredGoToDeBasedesktopUrl(String cartId, String shopperId, String shopperEmail) throws InterruptedException {
        HashMap<String, Object> expectedData=new HashMap<>();

        expectedData.put("cartId",cartId);
        expectedData.put("shopperId",shopperId);
        expectedData.put("shopperEmail ",shopperEmail);
        expectedData.put("auth_v","2.0");
        expectedData.put("auth_key", "tVIoa1S6");
        expectedData.put("auth_ts", "1640991600");
        expectedData.put("auth_sign", "c723c649c389d68d8ab3feb4f53875f7f7eb87d27ec575f1f06a66e3dae4dc30");
        Response response=given().
                contentType(ContentType.JSON).
                body(expectedData).
                when().
                post("https://api.luckycart.com/cart/ticket");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();


        Driver.getDriver().get(jsonPath.getString("baseDesktopUrl"));
    }



    @And("Home page  click on {string}")
    public void homePageClickOn(String arg0) {
    WebElement iframe= Driver.getDriver().findElement(By.id("lc-experience-frame"));
     Driver.getDriver().switchTo().frame(iframe);
     luckCart.playNow.click();




    }

    @And("Game page  click on {string}")
    public void gamePageClickOn(String arg0) {
        luckCart.gamePage.click();

    }

    @And("Validate that the game result text")
    public void validateThatTheGameResultText() {

        Assert.assertTrue(luckCart.CongratsyouwonNothing.isDisplayed());
    }



}
