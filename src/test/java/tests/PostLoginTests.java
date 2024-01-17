package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.PostLoginBodyModelLombok;
import models.pojo.LoginResponseModel;
import models.pojo.PostLoginBodyModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.loginSpec;
import static specs.LoginSpec.statusCode200Spec;

public class PostLoginTests {

    @Test
    void loginModelPojoTest() {
        PostLoginBodyModel loginBodyModel = new PostLoginBodyModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("pistol");

        LoginResponseModel responseModel = given()
                .log().uri()
                .log().body()
                .log().headers()
                .body(loginBodyModel)
                .contentType(JSON)
        .when()
                .post("https://reqres.in/api/register")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        assertEquals("QpwL5tke4Pnpja7X4", responseModel.getToken());
        assertEquals(4, responseModel.getId());


    }

    @Test
    void loginModelLombokTest() {
        PostLoginBodyModelLombok loginBodyModelLombok = new PostLoginBodyModelLombok();
        loginBodyModelLombok.setEmail("eve.holt@reqres.in");
        loginBodyModelLombok.setPassword("pistol");

        LoginResponseModel responseModelLombok = given()
                .log().uri()
                .log().body()
                .log().headers()
                .body(loginBodyModelLombok)
                .contentType(JSON)
        .when()
                .post("https://reqres.in/api/register")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        assertEquals("QpwL5tke4Pnpja7X4", responseModelLombok.getToken());
        assertEquals(4, responseModelLombok.getId());


    }

    @Test
    void loginModelLombokAllureTest() {
        PostLoginBodyModelLombok loginBodyModelLombok = new PostLoginBodyModelLombok();
        loginBodyModelLombok.setEmail("eve.holt@reqres.in");
        loginBodyModelLombok.setPassword("pistol");

        LoginResponseModel responseModelLombok = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .log().body()
                .log().headers()
                .body(loginBodyModelLombok)
                .contentType(JSON)
       .when()
                .post("https://reqres.in/api/register")
       .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        assertEquals("QpwL5tke4Pnpja7X4", responseModelLombok.getToken());
        assertEquals(4, responseModelLombok.getId());


    }
    @Test
    void loginModelLombokCustomAllureTest() {
        PostLoginBodyModelLombok loginBodyModelLombok = new PostLoginBodyModelLombok();
        loginBodyModelLombok.setEmail("eve.holt@reqres.in");
        loginBodyModelLombok.setPassword("pistol");

        LoginResponseModel responseModelLombok = given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(loginBodyModelLombok)
                .contentType(JSON)
        .when()
                .post("https://reqres.in/api/register")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        assertEquals("QpwL5tke4Pnpja7X4", responseModelLombok.getToken());
        assertEquals(4, responseModelLombok.getId());


    }

    @Test
    void loginModelLombokCustomAllureStepTest() {
        PostLoginBodyModelLombok loginBodyModelLombok = new PostLoginBodyModelLombok();
        loginBodyModelLombok.setEmail("eve.holt@reqres.in");
        loginBodyModelLombok.setPassword("pistol");


        LoginResponseModel responseModelLombok = step("Make request", ()-> {
            return given()
                    .filter(withCustomTemplates())
                    .log().uri()
                    .log().body()
                    .log().headers()
                    .body(loginBodyModelLombok)
                    .contentType(JSON)
            .when()
                    .post("https://reqres.in/api/register")
            .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginResponseModel.class);
        });
        step("Check response", ()-> {
            assertEquals("QpwL5tke4Pnpja7X4", responseModelLombok.getToken());
            assertEquals(4, responseModelLombok.getId());
        });

    }
    @Test
    void loginModelLombokCustomAllureStepSpecsTest() {
        PostLoginBodyModelLombok loginBodyModelLombok = new PostLoginBodyModelLombok();
        loginBodyModelLombok.setEmail("eve.holt@reqres.in");
        loginBodyModelLombok.setPassword("pistol");

        LoginResponseModel responseModelLombok = step("Make request", ()-> {
            return given(loginSpec)
                    .body(loginBodyModelLombok)
            .when()
                    .post("https://reqres.in/api/register")
            .then()
                    .spec(statusCode200Spec)
                    .extract().as(LoginResponseModel.class);
        });
        step("Check response", ()-> {
            assertEquals("QpwL5tke4Pnpja7X4", responseModelLombok.getToken());
            assertEquals(4, responseModelLombok.getId());
        });

    }



}
