package org.example;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ResponseExamples {
    @Test
    public void parsingSimpleObject(){
        Response response = get("https://jsonplaceholder.typicode.com/users/1");

        String name = response.jsonPath().getString("name");
        String email = response.jsonPath().getString("email");
        String city = response.jsonPath().getString("address.city");

        System.out.println("Name: " + name + ", Email: " + email + ", City: " + city);

    }

    @Test
    public void gettingMultipleObjects(){
        Response response = get("https://jsonplaceholder.typicode.com/posts");
        List<String> titles = response.jsonPath().getList("title");
        titles.forEach(title -> System.out.println("Title: " + title));


    }

    @Test
    public void advancedExammmples(){
        Response usersResponse = get("https://jsonplaceholder.typicode.com/users");
        List<Integer> userIds = usersResponse.jsonPath().getList("id");

        userIds.forEach(userId -> {
            Response postsResponse = given()
                    .queryParam("userId", userId)
                    .get("https://jsonplaceholder.typicode.com/posts");

            List<Map<String, Object>> posts = postsResponse.jsonPath().getList("");

            // Assuming we want the first two posts only
            posts.stream().limit(2).forEach(post -> {
                String title = (String) post.get("title");
                String body = (String) post.get("body");
                System.out.println("User ID: " + userId + ", Post Title: " + title + ", Post Body: " + body);
            });
        });


    }
}
