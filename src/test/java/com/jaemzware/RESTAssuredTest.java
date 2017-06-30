package com.jaemzware;

import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
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
                log().body();

    }

    @Test
    public void GitUserLoginJSONTest() throws Exception{
        given().auth().preemptive().basic(githubAccessToken, githubAccessSecret).when().get("https://api.github.com/user").then().statusCode(200).log().body();
    }

    @Test
    public void GitReposJSONTest() throws Exception{
        List<String> repoUrls = when().
                get("https://api.github.com/users/facebook/repos").
                then().
                assertThat().statusCode(200).extract().path("html_url");

        for(String repo:repoUrls){
            System.out.println(repo);
        }
    }

    @Test
    public void GitPrivateReposJSONTest() throws Exception{
        given().auth().preemptive().basic(githubAccessToken, githubAccessSecret).when().get("https://api.github.com/user/repos").then().statusCode(200).log().body();

    }


    @Test
    public void GitPrivateReposExtractJSONTest() throws Exception{
        List<String> privateRepoUrls = given().auth().preemptive().basic(githubAccessToken, githubAccessSecret).when().get("https://api.github.com/user/repos").then().statusCode(200).extract().path("html_url");

        for(String repo:privateRepoUrls){
            System.out.println(repo);
        }
    }

    @Test
    public void GitPrivateReposSeleniumStandaloneCommitsLog() throws Exception{
        given().auth().preemptive().basic(githubAccessToken, githubAccessSecret).when().get("https://api.github.com/repos/jimarasim/SeleniumStandalone/commits").then().statusCode(200).log().body();

    }

    @Test
    public void GitPrivateReposSeleniumStandaloneCommitsExtractJSONTest() throws Exception{
        List<String> commitMessages = given().auth().preemptive().basic(githubAccessToken, githubAccessSecret).when().get("https://api.github.com/repos/jimarasim/SeleniumStandalone/commits").then().statusCode(200).extract().path("commit.message");

        for(String commit:commitMessages){
            System.out.println(commit);
        }
    }


    @Test
    public void GitPrivateReposBoardScratchExtractJSONTest() throws Exception{
        List<String> Xs = given().auth().preemptive().basic(githubAccessToken, githubAccessSecret).when().get("https://api.github.com/repos/jimarasim/Board/branches").then().statusCode(200).extract().path("name");

        for(String x:Xs){
            System.out.println(x);
        }
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

    String githubAccessSecret = "Git8Hub";
    String githubAccessToken = "jimarasim";

}
