package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.PostNameAndJobBodyModelLombok;
import models.pojo.PostNameAndJobBodyModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static specs.LoginSpec.loginSpec;
import static specs.LoginSpec.statusCode201Spec;

public class PostNameAndJobTests {
    @Test
    void nameAndJobModelPogoTest(){

        PostNameAndJobBodyModel postNameAndJobBodyModel = new PostNameAndJobBodyModel();
        postNameAndJobBodyModel.setName("morpheus");
        postNameAndJobBodyModel.setJob("leader");

        given()
                .log().uri()
                .log().body()
                .log().headers()
                .body(postNameAndJobBodyModel)
                .contentType(JSON)
        .when()
                .post("https://reqres.in/api/users")
       .then()
                .body("name",is(postNameAndJobBodyModel.getName()))
                .body("job",is(postNameAndJobBodyModel.getJob()))
                .statusCode(201);
    }

    @Test
    void nameAndJobModelLombokTest(){

        PostNameAndJobBodyModelLombok postNameAndJobBodyModelLombok = new PostNameAndJobBodyModelLombok();
        postNameAndJobBodyModelLombok.setName("morpheus");
        postNameAndJobBodyModelLombok.setJob("leader");

        given()
                .log().uri()
                .log().body()
                .log().headers()
                .body(postNameAndJobBodyModelLombok)
                .contentType(JSON)
       .when()
                .post("https://reqres.in/api/users")
       .then()
                .body("name",is(postNameAndJobBodyModelLombok.getName()))
                .body("job",is(postNameAndJobBodyModelLombok.getJob()))
                .statusCode(201);
    }
    @Test
    void nameAndJobModelLombokAllureTest(){

        PostNameAndJobBodyModelLombok postNameAndJobBodyModelLombok = new PostNameAndJobBodyModelLombok();
        postNameAndJobBodyModelLombok.setName("morpheus");
        postNameAndJobBodyModelLombok.setJob("leader");

        given()
               .filter(new AllureRestAssured())
                .log().uri()
                .log().body()
                .log().headers()
                .body(postNameAndJobBodyModelLombok)
                .contentType(JSON)
        .when()
                .post("https://reqres.in/api/users")
                .then()
                .body("name",is(postNameAndJobBodyModelLombok.getName()))
                .body("job",is(postNameAndJobBodyModelLombok.getJob()))
                .statusCode(201);
    }
    @Test
    void nameAndJobModelLombokCustomAllureTest(){

        PostNameAndJobBodyModelLombok postNameAndJobBodyModelLombok = new PostNameAndJobBodyModelLombok();
        postNameAndJobBodyModelLombok.setName("morpheus");
        postNameAndJobBodyModelLombok.setJob("leader");

        given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(postNameAndJobBodyModelLombok)
                .contentType(JSON)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .body("name",is(postNameAndJobBodyModelLombok.getName()))
                .body("job",is(postNameAndJobBodyModelLombok.getJob()))
                .statusCode(201);
    }

    @Test
    void nameAndJobModelLombokCustomAllureStepTest(){

        PostNameAndJobBodyModelLombok postNameAndJobBodyModelLombok = new PostNameAndJobBodyModelLombok();
        postNameAndJobBodyModelLombok.setName("morpheus");
        postNameAndJobBodyModelLombok.setJob("leader");
        step("Put your name and job to check the match",()->{
        given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(postNameAndJobBodyModelLombok)
                .contentType(JSON)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .body("name",is(postNameAndJobBodyModelLombok.getName()))
                .body("job",is(postNameAndJobBodyModelLombok.getJob()))
                .statusCode(201);
        });
    }
    @Test
    void nameAndJobModelLombokCustomAllureStepSpecsTest(){

        PostNameAndJobBodyModelLombok postNameAndJobBodyModelLombok = new PostNameAndJobBodyModelLombok();
        postNameAndJobBodyModelLombok.setName("morpheus");
        postNameAndJobBodyModelLombok.setJob("leader");
        step("Put your name and job to check the match",()->{
            given()
                    .spec(loginSpec)
                    .body(postNameAndJobBodyModelLombok)
           .when()
                    .post("https://reqres.in/api/users")
           .then()
                    .body("name",is(postNameAndJobBodyModelLombok.getName()))
                    .body("job",is(postNameAndJobBodyModelLombok.getJob()))
                    .spec(statusCode201Spec);
        });
    }
}
