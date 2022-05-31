package API_Test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Api_Test {

    String headerContentType;
    String headerObject;

    @BeforeTest
    public void precondition() {
        headerContentType = "Content-Type";
        headerObject = "application/json";
        baseURI = "https://catalog.onliner.by/";
    }

    @Test(priority = 1, description = "Get response to get apple phones in catalog", enabled = true)
    public void getAppleInCatalog_Test() {
        Response response = given().when().get("sdapi/catalog.api/search/mobile?mfr[0]=apple&group=1");

        assertStatusCode(response, 200);
        Assert.assertTrue(getDataFromResponseAsString(response, "products.full_name[0]").contains("Apple"));
        Assert.assertEquals(getDataFromResponseAsString(response, "products.name_prefix[0]"), "Смартфон");
        Assert.assertEquals(getDataFromResponseAsString(response, "products.status[0]"), "active");
    }

    @Test(priority = 2, description = "Incorrect login via post request", enabled = true)
    public void incorrectLogin_test() {
        Response response = given().when().header(headerContentType, headerObject)
                .post("sdapi/user.api/login?login=vitaliiyz&password=qweasdzxc");

        assertStatusCode(response, 400);
        Assert.assertEquals(getDataFromResponseAsString(response, "errors.key[0]"), "invalid_login_or_password");
        Assert.assertEquals(getDataFromResponseAsString(response, "errors.message[0]"), "Неверный логин или пароль");
    }

    @Test(priority = 3, description = "Get laptops on sale", enabled = true)
    public void getLaptopsOnSale_Test() {
        Response response = given().when().get("sdapi/catalog.api/search/notebook?on_sale=1");

        assertStatusCode(response, 200);
        Assert.assertEquals(getDataFromResponseAsString(response, "products.name_prefix[0]"), "Ноутбук");
        Assert.assertTrue(getDataFromResponseAsBoolean(response, "products.sale[0].is_on_sale"));
        Assert.assertTrue(getDataFromResponseAsInt(response, "products.sale[0].discount") > 0);
    }

    @Test(priority = 4, description = "Get cat food for sterilized сat", enabled = true)
    public void getFoodForSterilizedCat() {
        Response response = given().when().get("sdapi/catalog.api/search/catfood?mfr[0]=whiskas&typefood4cat[0]=dryfood&typefood4cat[operation]=union&partic_catfood[0]=sterilise&partic_catfood[operation]=union&group=1");

        assertStatusCode(response, 200);
        Assert.assertEquals(getDataFromResponseAsString(response, "products.name_prefix[0]"), "Сухой корм для кошек");
        Assert.assertTrue(getDataFromResponseAsString(response, "products.full_name").contains("для стерилизованных"));
    }

    private String getDataFromResponseAsString(Response response, String dataPath) {
        return response.then().extract().jsonPath().getString(dataPath);
    }

    private Integer getDataFromResponseAsInt(Response response, String dataPath) {
        return response.then().extract().jsonPath().getInt(dataPath);
    }

    private Boolean getDataFromResponseAsBoolean(Response response, String dataPath) {
        return response.then().extract().jsonPath().getBoolean(dataPath);
    }

    private void assertStatusCode(Response response, int expectedCode) {
        response.then().assertThat().statusCode(expectedCode);
    }

}
