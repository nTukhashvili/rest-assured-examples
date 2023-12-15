package org.example;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BasicExamples {

    @Test
    public void getRequest_1() {
        Response response = get("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void methodChaining() {
        get("https://www.google.com")
                .then()
                .statusCode(200);
    }

    @Test
    public void gettingDataFromBody() {
        Response response =  get("https://jsonplaceholder.typicode.com/posts");
        System.out.println(response.body.toString());
    }

    @Test
    public void getRequest() {
        baseURI = "https://jsonplaceholder.typicode.com";

        Response response = get("/posts/1");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
    }

    @Test
    public void PostRequest() {
        baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{\"title\": \"New Post\", \"body\": \"This is a new post.\"}";
        //ფლუენტ API-ი გულისხმობს მეთოდების ჩეინინგის საშუალებას
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .post("/posts");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
    }

    @Test
    public void PutRequest() {
        baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{\"title\": \"Updated Post\", \"body\": \"This post has been updated.\"}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .put("/posts/1");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
    }

    @Test
    public void DeleteRequest() {
        baseURI = "https://jsonplaceholder.typicode.com";

        Response response = delete("/posts/1");

        int statusCode = response.getStatusCode();

        System.out.println("Status Code: " + statusCode);
    }


}

