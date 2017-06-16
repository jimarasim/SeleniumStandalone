package com.jaemzware;

import org.junit.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * Created by jameskarasim on 6/16/17.
 */
public class RESTAssuredTest {

    //THIS TEST USES RESTASSURED TO VERIFY THE RESPONSE BODY HAS VALUES EQUAL TO WHAT'S EXPECTED
    @Test
    public void GitUsersJSONTest() throws Exception{
        when().
                get("https://api.github.com/users/jimarasim").
                then().
                assertThat().statusCode(200).
                assertThat().header("Server","GitHub.com").
                body("login",equalTo("jimarasim")).
                body("location",equalTo("Seattle")).
                log().all();

    }

    //THIS TEST USES RESTASSURED TO VERIFY THE RESPONSE HAS MULTIPLIER VALUES 2, 3, 4, AND 5
    @Test
    public void LottoJSONTest() throws Exception{
        when().
                get("http://data.ny.gov/resource/d6yy-54nr.json").
                then().
                assertThat().statusCode(200).
                body("multiplier",hasItems("2","3","4","5")).
                log().body();
    }
}
