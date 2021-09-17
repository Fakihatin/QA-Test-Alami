package fakeapi;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class FakeApiTest {

    @Test
    public void ApiProduct_isAlive() {
        RestAssured.given()
                .when().get("https://fakestoreapi.com/products")
                .then().statusCode(200);
    }
    @Test
    public void ApiProduct_verifyProduct() {
        RestAssured.given()
                .when().get("https://fakestoreapi.com/products/1")
                .then()
                .body("id", equalTo(1))
                .body("title", equalTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"))
        ;
    }
    @Test
    public void ApiProduct_addProduct() {

        JsonObject json = new JsonObject();
        json.addProperty("title", "New Product");
        json.addProperty("price", 13.5);
        json.addProperty("description", "This is new");
        json.addProperty("image", "https://i.pravatar.cc");
        json.addProperty("category", "electronic");

        RestAssured.given()
                .body(json.toString())
                .when().post("https://fakestoreapi.com/products")
                .then()
                .statusCode(200)
        ;
    }

}
