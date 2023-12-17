package org.example;
import io.restassured.RestAssured;
import org.example.Post;
import org.testng.annotations.Test;
import com.google.gson.Gson;

public class PostExample {
    @Test
    public void postingSomeData(){
        Post newPost = new Post();
        newPost.setUserId(1);
        newPost.setTitle("My New Post");
        newPost.setBody("This is the content of my new post.");

        Gson gson = new Gson();
        String json = gson.toJson(newPost);

        int postId = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

    }
}
