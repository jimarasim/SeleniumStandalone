package com.jaemzware;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasSize;


/**
 * Created by jameskarasim on 6/16/17.
 */
public class RESTAssuredTest {

    String githubAccessSecret = "Git8Hub";
    String githubAccessToken = "jimarasim";

    @BeforeClass
    public void initPath() {

        RestAssured.baseURI = "https://api.github.com";
    }


    //POST
    @Test
    public void GitUserBlogPost() throws Exception{
        given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                body("{\"blog\":\"https://seattlerules.com\"}").
                post("/user").
                then().
                log().all();

    }


    //EQUALTO
    @Test
    public void GitUsersJSONTest() throws Exception{
        when().
                get("/users/jimarasim").
                then().
                statusCode(200).
                header("Server",equalTo("GitHub.com")).
                body("login",equalTo("jimarasim")).
                body("location",equalTo("Seattle")).
                time(lessThan(1000L)).
                log().all();

    }

    //EXTRACT
    @Test
    public void GitReposExtractAllJSONTest() throws Exception{
        List<String> repoUrls = when().
                get("/users/facebook/repos").
                then().
                statusCode(200).
                extract().path("html_url");

        for(String repo:repoUrls){
            System.out.println(repo);
        }
    }

    @Test
    public void GitReposExtractOneJSONTest() throws Exception{
        String repoUrl = when().
                get("/users/facebook/repos").
                then().
                statusCode(200).
                extract().path("html_url[0]");

        System.out.println(repoUrl);

    }

    //LOGIN
    @Test
    public void GitUserLoginJSONTest() throws Exception{
        given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/user").
                then().
                statusCode(200).
                log().body();
    }

    @Test
    public void GitPrivateReposJSONTest() throws Exception{
        given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/user/repos").
                then().
                statusCode(200).
                log().body();
    }

    @Test
    public void GitPrivateReposSeleniumStandaloneCommitsLog() throws Exception{
        given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/repos/jimarasim/SeleniumStandalone/commits").
                then().
                statusCode(200).
                log().body();
    }

    //LOGIN AND EXTRACT
    @Test
    public void GitPrivateReposExtractJSONTest() throws Exception{
        List<String> privateRepoUrls = given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/user/repos").
                then().
                statusCode(200).
                extract().path("html_url");

        for(String repo:privateRepoUrls){
            System.out.println(repo);
        }
    }

    @Test
    public void GitPrivateReposSeleniumStandaloneCommitsExtractJSONTest() throws Exception{
        List<String> commitMessages = given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/repos/jimarasim/SeleniumStandalone/commits").
                then().
                statusCode(200).
                extract().path("commit.message");

        for(String commit:commitMessages){
            System.out.println(commit);
        }
    }


    @Test
    public void GitPrivateReposBoardScratchExtractJSONTest() throws Exception{
        List<String> Xs = given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/repos/jimarasim/Board/branches").
                then().
                statusCode(200).
                extract().path("name");

        for(String x:Xs){
            System.out.println(x);
        }
    }

    //FINDALL and LENGTH()
    //it IS A RESERVED WORD IN GROOVY
    @Test
    public void GitFindAllCommitMessagesLessThan20Characters() throws Exception{
        List<String> commitMessages = given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/repos/jimarasim/SeleniumCodeBase/commits").
                then().
                extract().path("commit.message.findAll{it.length() < 20}");

        for(String commit:commitMessages){
            System.out.println(commit);
        }
    }

    //PATHPARAM WITH DATA PROVIDER
    @DataProvider(name = "repo")
    public Object[][] createRepoData() {
        return new Object[][] {
                { "Board" },
                { "SeleniumCodeBase" },
                { "SeleniumStandalone" }
        };
    }
    @Test(dataProvider = "repo")
    public void GitPrivateReposPathparamCommitsLog(String repo) throws Exception{
        given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                pathParam("repo",repo).
                when().
                get("/repos/jimarasim/{repo}").
                then().
                statusCode(200).
                log().body();
    }

    //RESPONSE TIME
    @Test
    public void GitUsersTimedJSONTest() throws Exception{
        when().
                get("/users/jimarasim").
                then().
                assertThat().statusCode(200).
                header("Server",equalTo("GitHub.com")).
                body("login",equalTo("jimarasim")).
                body("location",equalTo("Seattle")).
                time(lessThan(1500L), TimeUnit.MILLISECONDS);

    }

    //LOGIN, EXTRACT ALL REPOS, EXTRACT BRANCHES FROM EACH REPO
    @Test
    public void GitBranchesForAllRepos() throws Exception{
        //extract a list of all repo urls
        List<String> privateRepoUrls = given().
                auth().
                preemptive().
                basic(githubAccessToken, githubAccessSecret).
                when().
                get("/user/repos").
                then().
                statusCode(200).
                extract().path("html_url");

        //shorten urls to the repo name only
        List<String> privateRepoNames = new ArrayList<String>();
        for(String repo:privateRepoUrls) {
            String nameOnly = repo.substring(repo.lastIndexOf('/')+1);
            privateRepoNames.add(nameOnly);
        }

        //list the branches for each repo name
        for(String repo:privateRepoNames){
            System.out.println(repo);
            List<String> branches = given().
                    auth().
                    preemptive().
                    basic(githubAccessToken, githubAccessSecret).
                    pathParam("repoName",repo).
                    when().
                    get("/repos/jimarasim/{repoName}/branches").
                    then().
                    statusCode(200).
                    extract().path("name");
            for(String branch:branches){
                System.out.println("--"+branch);
            }
        }
    }


}
