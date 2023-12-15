package org.example;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import org.testng.annotations.Test;


public class BasicExamples {
    @Test
    public void getRequest(){
        baseURI = "https://jsonplaceholder.typicode.com";

        Response response = get("/posts/1");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
    }
}


