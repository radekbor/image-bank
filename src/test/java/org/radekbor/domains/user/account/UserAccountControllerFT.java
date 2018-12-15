package org.radekbor.domains.user.account;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.radekbor.ImgBankApp;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.File;
import java.util.Base64;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImgBankApp.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserAccountControllerFT {

    @Test
    public void registerAndGetUserDetails() {

        String userName = "Jane";
        String password = "test";
        String email = "jane@gmail.com";
        JSONObject registerJson = new JSONObject()
                .put("userName", userName)
                .put("email", email)
                .put("password", password);

        Response response = given().body(registerJson.toString())
                .contentType(ContentType.JSON)
                .post("/register")
                .andReturn();

        Integer id = response.getBody().as(Integer.class);

        String authStr = "client:secret";

        String authHeader = Base64.getEncoder().encodeToString((authStr).getBytes());

        ResponseBody loginResponse = given()
                .header("Authorization", "Basic " + authHeader)
                .param("grant_type", "password")
                .param("username", userName)
                .param("password", password)
                .post("/oauth/token")
                .andReturn().getBody();

        String token = loginResponse.jsonPath().get("access_token");

        assertThat(token).isNotEmpty();

        ResponseBody body = given()
                .param("access_token", token)
                .get("/my")
                .andReturn()
                .getBody();

        String returnedName = body.jsonPath().get("name");
        Integer returnedId = body.jsonPath().get("id");
        String returnedEmail = body.jsonPath().get("email");

        assertThat(returnedId).isEqualTo(id);
        assertThat(returnedName).isEqualTo(userName);
        assertThat(returnedEmail).isEqualTo(returnedEmail);

    }
}