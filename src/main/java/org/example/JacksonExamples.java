package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class JacksonExamples {
    @Test
    public static void simpleExample() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");

        String json = mapper.writeValueAsString(user);
        System.out.println(json);

    }

    @Test
    public static void jsonToJavaObj() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"name\":\"John Doe\",\"email\":\"john@example.com\"}";

        User user = mapper.readValue(json, User.class);
        System.out.println(user.getName());

    }

    @Test
    public static void postRequestExample() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("John Doe", 30, "jond@doe.com");
        String json = mapper.writeValueAsString(user);

        // Send POST request
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body();
    }
}

