import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.nio.file.Files.size;
import static org.hamcrest.Matchers.*;


public class TestsApi {
    @Test
    void getSingleUserTest() {
        given()
                .log().uri()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .log().all()
                .body("page", is(2))
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12))
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"))
                .statusCode(200);
    }

    @Test
    void postCreateTest(){
        String nameAndJob = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        given()
             .body(nameAndJob)
             .contentType(JSON)
        .when()
                .log().uri()
                .post("https://reqres.in/api/users")
        .then()
            .body("name",is("morpheus"))
            .body("job",is("leader"))
            .statusCode(201);
    }


    @Test
    void putUser2Test(){
        String changeNameAndJob = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        given()
                .body(changeNameAndJob)
                .contentType(JSON)
       .when()
                .log().body()
                .put("https://reqres.in/api/users/2")
       .then()
                .body("name", is("morpheus"))
                .body("job",is("zion resident"))
                .statusCode(200);
    }

    @Test
    void deleteUser2Test(){
        given()
                .log().all()
                .delete("https://reqres.in/api/users/2")
       .then()
                .statusCode(204);
    }
    @Test
    void getUser2Error404Test(){
        given()
                .log().all()
                .get("https://reqres.in/api/users/notUser")
        .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void postUser2Test(){
        String nameAndJob = "{\"name\": \"jo\", \"job\": \"driver\"}";
        given()
                .body(nameAndJob)
                .contentType(JSON)
                .when()
                .log().uri()
                .post("https://reqres.in/api/users")
                .then()
                .body("name",is("jo"))
                .body("job",is("driver"))
                .statusCode(201);
    }

}
