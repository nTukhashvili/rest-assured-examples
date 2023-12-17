package org.example;
import com.google.gson.Gson;
import static io.restassured.RestAssured.*;
import org.example.User;
import org.testng.annotations.Test;

public class SerializationExample {

    @Test
    public static void basicSerializationExample(){
        User user1 = new User("Nika",25,"ntukhashvili@cu.ge");
        Gson gson = new Gson();
        String json = gson.toJson(user1);

        baseURI = "https://api.example.com";
        port = 443;
        basePath = "/v1";

        given()
                .header("Authorization", "Bearer YOUR_TOKEN")
                .cookie("sessionId", "12345")
                .when()
                .get("/someEndpoint");



        given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();
    }




}
