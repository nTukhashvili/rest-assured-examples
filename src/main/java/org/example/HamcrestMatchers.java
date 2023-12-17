package org.example;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchers {

    @Test
    public void basicExamples(){
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"))
                .body("username", equalTo("Bret"))
                .body("email", equalTo("Sincere@april.biz"))
                .body("address.street", equalTo("Kulas Light"))
                .body("address.suite", equalTo("Apt. 556"))
                .body("address.city", equalTo("Gwenborough"));

    }

    @Test
    public void hasString(){
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"))
                .body("email", containsString("@"));

    }

    @Test
    public void hamcrestStructure(){
        //given() მეთოდით ვამზადებთ requests პარამეტრების და ჰეადერების მინიჭებით
        //when() ვასრულებთ requests
        //then() ამ მეთოდის შემდეგ ვაკეთებთ assertions
        given().
            header("Content-Type","application/json")
        .when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
            .statusCode(200);

    }

    @Test
    public void hamcrestAPI(){
        given().
            header("Content-Type","application/json")
        .when()
            .get("https://jsonplaceholder.typicode.com/users/1")
            .then()
            .statusCode(200)
            .body("name", equalTo("Leanne Graham"))
            .body("email", containsString("@"))
            .body("address.city", not(equalTo("Tbilisi")))
            .body("suite", endsWith("556"));
    }

    @Test
    public void verifyingMultipleElementCount(){
        given().
                header("Content-Type","application/json")
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200)
                .body("posts.size()",equalTo(10));

    }


}
